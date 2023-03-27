app.controller('feedback-ctrl', function($scope, $http) {
	$scope.items = [];
	$scope.itemRates = [];
	$scope.form = {};
    let user_id = document.getElementById('user_id').value;
    
	// Get all item from rest
	$scope.initialize = function() {
		// load productRates
		$http.get('/api/products/feedbacks').then(resp => {
			$scope.items = resp.data.data;
			$scope.listRates($scope.items);
		}).catch(error => {
			console.log("Error",error);
		});
	}
	
	$scope.listRates = function(items) {
		for (let i = 0; i < $scope.items.length; i++) {
	        var totalRate = $scope.productRate($scope.items[i].productRates);
	        $scope.itemRates.push({ "item": $scope.items[i], "totalRate": totalRate });
	    }
	}
	
	$scope.productRate = function(item) {
		$scope.arrayRate = item.map(i => {return i.rate});
		return $scope.calculateAverageRate($scope.arrayRate);
	}
	
	$scope.find = function() {
        var kw = document.getElementById("key__word").value;
        //check empty
        if (kw == "") {
            $http.get('/api/products/feedbacks').then(resp => {
                $scope.items = resp.data;
                $scope.listRates($scope.items);
            })
        } else {
            kw = kw;
            $http.get(`/api/products/search/${kw}`).then(resp => {
                $scope.items = resp.data;
                $scope.listRates($scope.items);
            })
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
	
	$scope.generateRange = function(start, stop, increment) {
	    let a = [];
	    for(; start < stop; ) {
	        a[start] = start;
	        start += increment;
	    }
	    return a;
	}
	
	$scope.pager = {
		page: 0,
		size: 7,
		get itemRates(){
			var start = this.page * this.size;
			return $scope.itemRates.slice(start, start + this.size);
		},
		get count()
		{
			return Math.ceil(1.0 * $scope.itemRates.length /this.size);
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