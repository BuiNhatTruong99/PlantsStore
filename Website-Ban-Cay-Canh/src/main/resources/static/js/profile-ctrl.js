app.controller("profile-ctrl", function($scope, $http) {
	$scope.profile = [];
	$scope.form = {};
	let user_id = document.getElementById('user_id').value;

	$scope.initialize = function() {
		$http.get(`/profile/${user_id}`).then(resp => {
			$scope.profile = resp.data;
			$scope.profile.birthday = new Date($scope.profile.birthday);
			console.log($scope.profile)
		})


	}
	$scope.initialize();
	$scope.edit = function() {
		$scope.form = angular.copy($scope.profile);

	}

	$scope.imageChanged = function(files) {
		var data = new FormData();
		console.log(files)
		data.append('file', files[0]);
		$http.post(`/api/upload/avatar`, data, {
			transformRequest: angular.identity,
			headers: {
				'Content-Type': undefined
			}
		}).then(resp => {
			$scope.form.avatar = resp.data.name;
			console.log(resp.data.name)
		}).catch(error => {
			// console.log(error)
		})
	}

	$scope.save = function() {

		var item = angular.copy($scope.form);
		console.log(item)
		$http.put(`/profile/update/${user_id}`, item).then(resp => {
				// var index = $scope.profile.indexOf(p => p.id === item.id);
				$scope.profile = item;
				console.log(item)
			alert("Lưu thành công")
			}).catch(error => {
			console.log("Error", error);
			console.log(item)
		})

	}
})