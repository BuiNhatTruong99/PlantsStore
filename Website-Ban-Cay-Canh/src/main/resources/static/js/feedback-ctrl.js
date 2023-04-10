app.controller('feedback-ctrl', function($scope, $rootScope, $http) {
	$scope.items = [];
	$scope.form = {};
	let user_id = null;
	// Get all item from rest
	$scope.initialize = function() {
		// load productRates
		$http.get(`/api/productRates/${productId}`).then(resp => {
			$scope.items = resp.data.data;
			$scope.productRate($scope.items);
			$scope.reset();
		}).catch(error => {
			console.log("Error",error);
		});
	}
	
	$scope.productRate = function(items) {
		$scope.arrayRate = items.map(i => {return i.rate});
		$scope.totalRate = $scope.calculateAverageRate($scope.arrayRate);
	}
	
	// lấy thông tin user, lấy id sp
	$scope.create = function() {
		user_id = getUserId();
		$scope.form.user = {'id': user_id};
		$scope.form.product_id = productId;
		var item = angular.copy($scope.form);
		if(item.rate == 0) return $scope.message = 'Vui lòng chọn mức sao tương ứng với độ hài lòng của bạn!';
		if(item.comment == '') return $scope.message = 'Vui lòng nhập đánh giá của bạn về sản phẩm!';
		if($scope.checkComment()){
			$http.post('/api/productRates', item).then(resp => {
				$scope.initialize();
				alert("Thêm đánh giá sản phẩm thành công")
			}).catch(error => {
				console.log("Error", error);
			})
			$scope.pager.first();
		}
	}
	
	$scope.rateFilter = 0;
	
	$scope.commentFilter = function() {
		user_id = getUserId();
		const comment = $scope.selectCommentFilter;
		const rate = $scope.rateFilter;
		const userId = (comment == 0)? 0 : user_id;
		if(comment == 0 && rate == 0) {
			$http.get(`/api/productRates/${productId}`).then(resp => {
				$scope.items = resp.data.data;
			}).catch(error => {
				console.log("Error",error);
			});
		} else {
			$http.get(`/api/productRates?productId=${productId}&userId=${userId}&rate=${rate}`).then(resp => {
				$scope.items = resp.data.data;
			}).catch(error => {
				console.log("Error",error);
			});
		}
		$scope.pager.first();
	}
	
	$scope.reset = function() {
		$scope.message = '';
		$scope.form = {
			rate: 0,
			comment: '',
		}
	}
	
	$scope.checkComment = function() {
	  var badWords = ["cc", "dmm", "dm", "cl"]; // danh sách từ ngữ xấu
	  var words = $scope.form.comment.split(" "); // tách comment thành các từ
	
	  for (var i = 0; i < words.length; i++) {
	    if (badWords.indexOf(words[i]) >= 0) { // kiểm tra từng từ trong comment có nằm trong danh sách từ ngữ xấu không
	      $scope.message = "Bình luận không được chứa từ ngữ xấu: <span class='bad-word'>" + words[i] + "</span>";
	      return false;
	    }
	  }
	
	  $scope.message = "";
	  return true;
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
function getUserId () {
	return document.getElementById('user_id').value;
}