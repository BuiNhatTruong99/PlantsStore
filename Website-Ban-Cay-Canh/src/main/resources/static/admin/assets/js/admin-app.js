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
        .when("/coupon", {
            templateUrl: "../admin/coupon/manager-coupon.html",
            controller: "coupon-ctrl"
        })
        .when("/myprofile",{
            templateUrl: "../admin/profile/manager_profile.html",
            controller: "profile-ctrl"
        })

        .when("/order", {
            templateUrl: "../admin/tables-data.html",
            controller: "order-ctrl"
        })
        .when("/profile", {
            templateUrl: "../admin/profiles/index-profile.html",
            controller: "profiles-ctrl"
        })
        .when("/feedback",{
            templateUrl: "../admin/feedbacks/manager-feedbacks.html",
            controller: "feedback-ctrl"
        })
        .when("/discount",{
            templateUrl: "../admin/discounts/manager-discounts.html",
            controller: "discount-ctrl"
        })
        .otherwise({
            templateUrl: "../admin/dashboard.html"
        })
})
