app.controller('wishlist-ctrl', function($scope, $http, $location) {
	$scope.items = [];
    let user_id = null;
	
	// Get all item from rest
	$scope.initialize = function() {
		// load wish list
		user_id = getUserId();
		$http.get(`/api/account/${user_id}/wishlist`).then(resp => {
			$scope.items = resp.data.data.likedProducts;
		}).catch(error => {
			console.log("Error",error);
		});
	}
	
	if (url.match("/wishlist") || url.match("/home/index") && getUserId()!=null) {
		$scope.initialize();
	}
	
	$scope.insert = function(productId) {
		user_id = getUserId();
		for(var i = 0; i < $scope.items.length; i++){
			if(productId == $scope.items[i].id)return alert("Sản phẩm này đã có trong yêu thích của bạn!");
		}
		$http.get(`/api/account/${user_id}/wishlist/insert?id=${productId}`).then(resp => {
			$scope.initialize();
			alert("Bạn vừa thêm một sản phẩm vào yêu thích!")
		}).catch(error => {
			console.log("Error",error);
		});
	}
	
	$scope.delete = function(productId) {
		$http.get(`/api/account/${user_id}/wishlist/delete?id=${productId}`).then(resp => {
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
});
function getUserId () {
	try {
		return document.getElementById('user_id').value;
	}catch {
		return null;
	}
}