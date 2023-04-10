function submitSize() {
	var string = document.querySelector('input[name="size"]:checked').value;
	let arr = string.split(",");
	let priceSize = arr[1];
	return document.getElementById("price").textContent = formatCurrency(priceSize) + ' VND';
}

function formatCurrency(price) {
	const format = price.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	return format.replace('.0','');
}