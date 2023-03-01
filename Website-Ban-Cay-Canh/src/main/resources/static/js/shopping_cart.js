const app = angular.module('shoppingCart', ['ngSanitize']);
app.controller('shoppingCart-ctrl', function($scope, $http) {
    $scope.products = [];
    $scope.form = {};
    $scope.cates = function (){
        $http.get('/api/category').then(resp => {
            $scope.cates = resp.data;
        })
    };
    $scope.cates();

    $scope.cart = {
        items: [],
        add(id) {
            let item = this.items.find(item => item.id === id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage()
            } else {
                $http.get(`/api/dto/products/${id}`).then(resp => {
                    resp.data.data.qty = 1;
                    this.items.push(resp.data.data);
                    this.saveToLocalStorage()
                });
            }
        },
        remove(id) {
            let index = this.items.findIndex(item => item.id === id);
            if (index !== -1) {
                this.items.splice(index, 1);
                this.saveToLocalStorage()

            }
        },

        clear() {
            this.items = [];
            this.saveToLocalStorage()
        },

        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        get amount() {
            return this.items.map(item => item.qty * item.price).reduce((total, qty) => total += qty, 0);
        },

        // save cart to localstorage
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        // read cart from localstoge
        loadFromLocalStorage() {
            let json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },


    }
    $scope.cart.loadFromLocalStorage();

});