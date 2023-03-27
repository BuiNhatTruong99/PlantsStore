
app.controller("order-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.orders = [];

    $scope.init = function() {
        $http.get("/api/order").then(resp =>{
			$scope.items = resp.data;
			$scope.orders = $scope.items.data

		})
    }

    $scope.init();

})
