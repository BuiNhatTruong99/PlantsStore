app.controller('products-ctrl', function($scope, $http) {
    $scope.items = [];
    $scope.products = [];
    $scope.form = {};
    $scope.cates = [];
    $scope.message = "";
    $scope.status = "";

    $scope.initialize = function() {
        $http.get('/api/dto/products').then(resp => {
            $scope.items = resp.data;
            $scope.products = $scope.items.data;
        })
        $http.get('/api/category').then(resp => {
            $scope.cates = resp.data;
        })
    }

    $scope.initialize();

    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        let today = new Date();
        $scope.form.createdDate = new Date($scope.form.createdDate);
        $scope.form.updatedDate = today;
        $('.nav-tabs a:eq(1)').trigger("click");
        document.getElementById("create").disabled = true;
    }


    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post(`/api/upload/product`, data, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(resp => {
            $scope.form.image = resp.data.name;
            console.log(resp.data.name)
        }).catch(error => {
            $scope.message = "Lỗi upload hình ảnh";
            $scope.status = "Warning";
            $scope.toats();
        })
    }

    $scope.find = function() {
        var kw = document.getElementById("key__word").value;
        //check empty
        if (kw == "") {
            $http.get('/api/dto/products').then(resp => {
                $scope.items = resp.data;
                $scope.products = $scope.items.data;
            })
        } else {
            kw = kw;
            $http.get(`/api/dto/products/search/${kw}`).then(resp => {
                $scope.items = resp.data;
                $scope.products = $scope.items.data;
            })
        }
    }

    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post(`/api/dto/products`, item).then(resp => {
            $scope.products.push(resp.data);
            $scope.message = "Thêm sản phẩm thành công";
            $scope.status = "Success";
            $scope.clear();
        }).catch(error => {
            $scope.message = "Lỗi thêm sản phẩm";
            $scope.status = "Warning";
            console.log("Error", error);
        })
        $scope.toats();
        document.getElementById("create").disabled = true;
    }

    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/api/dto/products/${item.id}`, item).then(resp => {
            var index = $scope.products.findIndex(p => p.id == item.id);
            $scope.products[index] = item;
            $scope.message = "Cập nhật sản phẩm thành công";
            $scope.status = "Success";
            $scope.clear();
        }).catch(error => {
            $scope.message = "Lỗi cập nhật sản phẩm";
            $scope.status = "Warning";
            console.log("Error", error);
        })
        $scope.toats();
    }

    $scope.delete = function(item) {
        $http.delete(`/api/dto/products/${item.id}`).then(resp => {
            var index = $scope.products.findIndex(p => p.id == item.id);
            $scope.products.splice(index, 1);
            $scope.message = "Xóa sản phẩm thành công";
            $scope.status = "Success";
            $scope.clear();
        }).catch(error => {
            $scope.message = "Lỗi xóa sản phẩm";
            $scope.status = "Warning";
            console.log("Error", error);
        })
        $scope.toats();
    }

    $scope.clear = function() {
        $scope.form = {
            createdDate: new Date(),
            updatedDate: new Date(),
            status: true
        };
        document.getElementById("create").disabled = false;
    }

    $scope.toats = function () {
        $('.toast').toast('show');
        setTimeout(function () {
            $('.toast').toast('hide');
        }, 2000);

    }

    $scope.hideToast = function () {
        $('.toast').toast('hide');
    }


    $scope.pager = {
        page: 0,
        size: 7,
        get products() {
            var start = this.page * this.size;
            return $scope.products.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.products.length / this.size);
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
});