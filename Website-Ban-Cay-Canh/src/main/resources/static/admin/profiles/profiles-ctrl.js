
app.controller("profiles-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.profiles = [];
    $scope.form = {};

    $scope.init = function () {
        $http.get("/api/profile").then(resp => {
            $scope.items = resp.data;
        })
    }
    $scope.init();
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(1)").trigger('click');
    }

    $scope.reset = function () {
        $scope.form = {}
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/api/profile/${item.id}`, item).then(resp => {
           var index = $scope.items.findIndex(p => p.id == item.id);
           $scope.items[index] = item;
           alert("oki");

        }).catch(error =>{
            alert("Lỗi");
            console.log("Error" , error);
        })
    }
    $scope.delete = function (item){
        $http.delete(`/api/profile/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.slice(index,1);
            $scope.reset();
            alert("oki");

        }).catch(error =>{
            alert("Lỗi");
            console.log("Error" , error);
        })
    }
})

