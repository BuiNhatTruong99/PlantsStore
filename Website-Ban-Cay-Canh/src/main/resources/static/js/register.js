var app = angular.module('register-app', []);
app.controller('register-ctrl', function($scope, $http) {
    $scope.accounts = [];
    $scope.form = {};
    $scope.items = [];
    $scope.message = "";

    $scope.initialize = function () {
        $http.get("/api/account").then(resp => {
            $scope.items = resp.data;
            $scope.accounts = $scope.items.data;
        })
    }

    $scope.initialize();

    $scope.create = function() {
        var item = angular.copy($scope.form);
        let today = new Date();
        $scope.form.create_date = today;
        $scope.form.update_date = today;
        let confirm = document.getElementById("re_pass").value;
        if(document.getElementById("agree").checked) {
            if (item.password != confirm) {
                $scope.message = "Mật khẩu không khớp";
                return;
            } else {
                var index = $scope.accounts.findIndex(a => a.username == $scope.form.username);
                if(index > 0) {
                    $scope.message = "Username này đã tồn tại";
                    return;
                } else {
                    $http.post(`/api/account/registered`, item).then(resp => {
                        $scope.message = "Đăng ký thành công";
                    }).catch(error => {
                        $scope.message = "Lỗi đăng ký";
                    })
                }

            }
        } else {
            $scope.message = "Bạn chưa đồng ý điều khoản";
            console.log("not check")
        }


    }

});