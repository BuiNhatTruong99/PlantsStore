var app = angular.module("admin-app", ['ngRoute'])

app.config(function ($routeProvider) {
    $routeProvider
        .when("/dashboard", {
            templateUrl: "../admin/dashboard.html",
        })
        .when("/account", {
            templateUrl: "../admin/accounts/manager-accounts.html",
            controller: "accounts-ctrl"
        })
        .when("/product", {
            templateUrl: "../admin/products/manager-products.html",
            controller: "products-ctrl"
        })
        .when("/myprofile",{
            templateUrl: "../admin/profile/manager_profile.html",
            controller: "profile-ctrl"
        })
        .otherwise({
            templateUrl: "../admin/dashboard.html"
        })
})
