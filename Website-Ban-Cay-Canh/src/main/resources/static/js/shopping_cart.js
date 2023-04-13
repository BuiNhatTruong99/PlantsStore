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
			let quantity = document.querySelector("#quantity");
			let quantity2 = quantity ? parseInt(quantity.value) : 1;
			let product_size = document.querySelector('input[name="size"]:checked');
			let product_size2 = product_size ? product_size.value : '';
			let arr = product_size2.split(",");
			let idSize = arr[0] ? arr[0] : '';
			let priceSize = arr[1] ? arr[1] : 0;
            let item = this.items.find(item => item.id === id && item.idSize === idSize);
			if (quantity.value.length == 0) {
				return alert("Mời bạn vui lòng nhập số lượng!");
			}
            if (item) {
                item.qty += quantity2;
                this.saveToLocalStorage()
                alert("+"+ quantity2 +" sản phẩm [" + item.name + "] vào giỏ hàng!");
            } else {
                $http.get(`/api/dto/products/${id}`).then(resp => {
					resp.data.data.idSize = idSize;
					resp.data.data.priceSize = Math.floor(priceSize);
                    resp.data.data.qty = quantity2;
                    this.items.push(resp.data.data);
                    this.saveToLocalStorage()
                    alert("Thêm "+ quantity2 +" sản phẩm [" + resp.data.data.name + "] vào giỏ hàng thành công!");
                });
            }
        },
        remove(id, idSize) {
            let index = this.items.findIndex(item => item.id === id && item.idSize === idSize);
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
            return this.items.map(item => item.qty * item.price + item.qty * item.priceSize)
            				 .reduce((total, qty) => total += qty, 0);
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

    var salePd = document.getElementById("saleP");
    if (salePd == null)
    {
        salePd = 0;
    }else {
        var paragraphValue = parseFloat(salePd.textContent);
        $scope.CouponSale = paragraphValue;
    }
});

function getCurrentURL () {
  return window.location.pathname;
}

const url = getCurrentURL();
console.log(url);

app.filter('vndFilter', function () {
	return function (x) {
				x = x.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
				return x.toString().split('.').join(',');
		   };
});