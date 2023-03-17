app.controller("profile-ctrl", function ($scope, $http) {
    $scope.profile = [];
    $scope.form = {};
    let user_id = document.getElementById('user_id').value;


    $scope.initialize = function() {
        $http.get(`/profile/${user_id}`).then(resp => {
            $scope.profile = resp.data;
            $scope.profile.birthday = new Date($scope.profile.birthday);
        })


    }
    $scope.initialize();
    $scope.edit = function() {
        $scope.form = angular.copy($scope.profile);

    }

    $scope.save = function() {

        var item = angular.copy($scope.form);
        // console.log(item)
        $http.put(`/profile/update/${user_id}`, item).then(resp => {

            $scope.profile = item;
            // console.log(item)
            alert("Lưu thành công")
        }).catch(error => {
            console.log("Error", error);
            // console.log(item)
        })

    }

    $scope.imageChanged = function(files) {
        var data = new FormData();
        // console.log(files)
        data.append('file', files[0]);
        $http.post(`/api/upload/avatar`, data, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(resp => {
            $scope.form.avatar = resp.data.name;
            // console.log(resp.data.name)
        }).catch(error => {
            // console.log(error)
        })
    }

    /////// change password account

    $scope.accounts = [];
    $scope.items = [];
    $scope.form_account = {};

    $scope.initialize_account = function () {
        $http.get(`/api/account/${user_id}`).then(resp => {
            $scope.items = resp.data;
            $scope.accounts = $scope.items.data;
            // console.log($scope.accounts)
        })

    }
    $scope.initialize_account();
    $scope.edit_pass = function() {
        $scope.form_account = angular.copy($scope.accounts);
    }

    $scope.update = function () {
        let oldpass = document.getElementById("currentPassword").value;
        let newpass = document.getElementById("newPassword").value;
        let confirm = document.getElementById("renewPassword").value;
             if(oldpass === $scope.accounts.password)
                {
                   if(newpass === confirm)
                   {
                       $scope.form_account.password = newpass;
                       var item_acc = angular.copy($scope.form_account);
                       $http.put(`/api/account/${user_id}`, item_acc).then(resp => {
                       $scope.accounts = item_acc;
                       alert("Đổi thành công")
                       }).catch(error => {
                           alert("Lỗi cập nhật");
                           console.log("Error", error);
                       })
                   }else{
                       alert("Xác nhận mật khẩu không khớp");
                   }
                } else {
                alert("Mật khẩu cũ không khớp");
                return;
            }
    }

})