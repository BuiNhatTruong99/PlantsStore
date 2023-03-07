app.controller('wishlist-ctrl', function($scope, $http) {
	$scope.items = [];
    
	// Get all item from rest
	$scope.initialize = function() {
		// load wish list
		const userId = 40;
		$http.get(`/api/account/${userId}/wishlist`).then(resp => {
			$scope.items = resp.data.data.likedProducts;
			console.log($scope.items);
		}).catch(error => {
			console.log("Error",error);
		});
	}
	
	$scope.insert = function(productId) {
		const userId = 40;
		console.log('id ' +productId);
		console.log($scope.items);
		for(var i = 0; i < $scope.items.length; i++){
			console.log($scope.items[i].id)
			if(productId == $scope.items[i].id)return alert("Sản phẩm này đã có trong yêu thích của bạn!");
		}
		$http.get(`/api/account/${userId}/wishlist/insert?id=${productId}`).then(resp => {
			$scope.initialize();
			alert("Bạn vừa thêm một sản phẩm vào yêu thích!")
		}).catch(error => {
			console.log("Error",error);
		});
	}
	
	$scope.delete = function(productId) {
		const userId = 40;
		$http.get(`/api/account/${userId}/wishlist/delete?id=${productId}`).then(resp => {
			$scope.initialize();
			alert("Bạn vừa xóa một sản phẩm ra khỏi yêu thích!")
		}).catch(error => {
			console.log("Error",error);
		});
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