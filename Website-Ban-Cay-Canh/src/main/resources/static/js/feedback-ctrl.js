app.controller('feedback-ctrl', function($scope, $rootScope, $http) {
	$scope.items = [];
	$scope.form = {};
	
    //alert(idProduct);
    
	// Get all item from rest
	$scope.initialize = function() {
		// load productRates
		$http.get(`/api/productRates/${idProduct}`).then(resp => {
			$scope.items = resp.data.data;
			$scope.productRate($scope.items);
			$scope.reset();
		}).catch(error => {
			console.log("Error",error);
		});
	}
	
	$scope.productRate = function(items) {
		$scope.arrayRate = items.map(i => {return i.rate});
		console.log($scope.arrayRate);
		$scope.totalRate = $scope.calculateAverageRate($scope.arrayRate);
		console.log($scope.totalRate);
	}
	
	// lấy thông tin user, lấy id sp
	$scope.create = function(items) {
		$scope.form.user = {
			"id": 6,
			"fullname": "Đặng Hoàng Thái",
			"avatar": "cust1.png",
			"gender": true,
			"birthday": "1992-03-10T17:00:00.000+00:00",
			"email": "cust1@gmail.com",
			"phone": "0984265835",
			"address": "Lý Thường Kiệt, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh"
		};
		$scope.form.product = {'id': idProduct};
		var item = angular.copy($scope.form);
		if(item.rate == 0) return $scope.message = 'Vui lòng chọn mức sao tương ứng với độ hài lòng của bạn!';
		if(item.comment == '') return $scope.message = 'Vui lòng nhập đánh giá!';
		$http.post('/api/productRates', item).then(resp => {
			$scope.items.push(resp.data.data);
			$scope.initialize();
		}).catch(error => {
			console.log("Error", error);
		})
		$scope.pager.first();
	}
	
	$scope.reset = function() {
		$scope.message = '';
		$scope.form = {
			rate: 0,
			comment: '',
		}
	}
	
	$scope.calculateAverageRate = function(array) {
	  if(array.length == 0) return 0;
	  var total = array.reduce(function(sum, value) {
	    return sum + value;
	  }, 0);
	  var avg = total / array.length;
	  return avg.toFixed(1); // Làm tròn đến 1 chữ số thập phân
	}
	
	$rootScope.generateRange = function(start, stop, increment) {
	    let a = [];
	    for(; start < stop; ) {
	        a[start] = start;
	        start += increment;
	    }
	    return a;
	}
	
	$scope.pager = {
		page: 0,
		size: 5,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count()
		{
			return Math.ceil(1.0 * $scope.items.length /this.size);
		},
		first()
		{
			this.page = 0;
		},
		prev()
		{
			this.page--;
			if(this.page < 0)
			{
				this.last()
			}
		},
		next()
		{
			this.page++;
			if(this.page >= this.count)
			{
				this.first();
			}
		},
		last()
		{
			this.page = this.count - 1;
		}
	}
	
	$scope.initialize();
});