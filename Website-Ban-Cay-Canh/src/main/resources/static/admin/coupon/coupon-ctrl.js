app.controller("coupon-ctrl", function ($scope, $http) {
    $scope.coupons = [];
    $scope.form = {};
    $scope.initialize = function () {
        $http.get("/api/coupon").then(resp => {
            $scope.coupons = resp.data;
            // console.log($scope.coupons)
            $scope.coupons.forEach(coupons => {
                coupons.createDate = new Date(coupons.createDate);
                coupons.endDate = new Date(coupons.endDate);
            })
        })
        $scope.isDisabled = true;

    }

    $scope.edit = function (coupons) {
        $scope.form = angular.copy(coupons);
        let today = new Date();
        $scope.form.endDate = today;
        $(".nav-tabs a:eq(1)").trigger('click');
        $scope.isDisabled = false;
        $scope.CrDisabled = true;
    }

    $scope.create = function ()
    {
        var item = angular.copy($scope.form);
        $http.post(`/api/coupon`,item).then(resp =>
        {
            $scope.coupons.push(resp.data);
            $scope.message = "Tạo thành công";

            $scope.clear();
        }).catch(error => {
            $scope.message = "Tạo thất bại";
            console.log("Error", error);
        })
    }

    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/api/coupon/${item.id}`, item).then(resp => {
            var index = $scope.coupons.findIndex(p => p.id == item.id);
            $scope.coupons[index] = item;
            $scope.isDisabled = true;
            $scope.CrDisabled = false;
            $scope.message = "Cập nhập thành công";
            $scope.clear();
        }).catch(error => {
            $scope.message = "Cập nhập thất bại";
            console.log("Error", error);
        })

    }

    $scope.delete = function(item) {
        $http.delete(`/api/coupon/${item.id}`).then(resp => {
            var index = $scope.coupons.findIndex(c => c.id == item.id);
            $scope.coupons.splice(index, 1);
            $scope.message = "Xoá thành công";
            $scope.clear();
        }).catch(error => {
            $scope.message = "Xoá thất bại";
            console.log("Error", error);
        })

    }

    $scope.clear = function() {
        $scope.form = {
            createDate: new Date(),
            endDate: new Date()
        };
    }



    $scope.initialize();



    $scope.pager = {
        page: 0,
        size: 7,
        get coupons() {
            var start = this.page * this.size;
            return $scope.coupons.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.coupons.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
})