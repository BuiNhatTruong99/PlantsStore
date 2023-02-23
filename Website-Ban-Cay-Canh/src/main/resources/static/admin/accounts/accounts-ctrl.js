app.controller("accounts-ctrl", function ($scope, $http) {
    $scope.accounts = [];
    $scope.items = [];
    $scope.form = [];

    $scope.initialize = function () {
        $http.get("/api/account").then(resp => {
            $scope.items = resp.data;
            $scope.accounts = $scope.items.data;
            $scope.accounts.forEach(account => {
                account.create_date = new Date(account.create_date);
            })
        })


    }

    $scope.initialize();

    $scope.edit = function (account) {
        $scope.form = angular.copy(account);
        let today = new Date();
        $scope.form.update_date = today;
        $(".nav-tabs a:eq(1)").trigger('click');

    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/api/account/${item.id}`, item).then(resp => {
            var index = $scope.accounts.findIndex(a => a.id == item.id);
            let confirm = document.getElementById("confirm").value;
            if (confirm == item.password) {
                $scope.accounts[index] = item;
                console.log($scope.accounts[index]);
            } else {
                console.log("Mật khẩu không khớp");
                return;
            }
        }).catch(error => {
            alert("Lỗi cập nhật");
            console.log("Error", error);
        })
        $scope.toats();
    }

    $scope.toats = function () {
            $('.toast').toast('show');
            setTimeout(function () {
            $('.toast').toast('hide');
         }, 1000);

    }

    $scope.hideToast = function () {
            $('.toast').toast('hide');
    }

    $scope.pager = {
        page: 0,
        size: 7,
        get accounts() {
            var start = this.page * this.size;
            return $scope.accounts.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.accounts.length / this.size);
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