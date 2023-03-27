function submitSize() {
	var string = document.querySelector('input[name="size"]:checked').value;
	let arr = string.split(",");
	let priceSize = arr[1];
	return document.getElementById("price").textContent = "$ " + priceSize;
}