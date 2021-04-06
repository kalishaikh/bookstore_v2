let count = new Map([["test", "test"]]);
var cartChanges = 0;
var priceChanges = 0;
var taxChanges = 0;
var totalPriceChanges = 0;

function changeQuantity(address, quantity, bid, type, cartSize, price, subtotal, tax, total) {

	var request = new XMLHttpRequest();
	var data = "";
	data = "&" + "bid=" + bid;
	request.open("GET", (address + data), true);
	request.onreadystatechange = function() {
		handler(request, quantity,  bid, count, type, cartSize, price, subtotal, tax, total);
	};

	request.send(null);

}

function handler(request, quantity, bid, count, type, cartSize, price, subtotal, tax, total) {
	if ((request.readyState == 4) && (request.status == 200)) {
		if (type == 'increment') {
			if (!count.has(bid)){
				count.set(bid, quantity+1);
			} else {
				var tmp = count.get(bid);
				count.set(bid, (tmp+1))
			}
			cartChanges = cartChanges + 1;
			priceChanges = priceChanges + price;
			taxChanges = taxChanges + price*0.15;
			
		} else if (type == 'decrement') {
			if (!count.has(bid)){
				count.set(bid, quantity-1);
			} else {
				var tmp = count.get(bid);
				count.set(bid, (tmp-1))
			}
			cartChanges = cartChanges - 1;
			priceChanges = priceChanges - price;
			taxChanges = taxChanges - price*0.15;
		}
		
		updateCartSize(cartSize);
		updatePrice(subtotal);
		updateTax(tax);
		updateTotal(total);
		
		target = document.getElementById(bid + "quantity");
		target.innerHTML = ' ' + count.get(bid) + ' ';
		
		
	}
	return false;
}

function removeItem(address, bid){
	var request = new XMLHttpRequest();
	var data = "";
	data = "&" + "bid=" + bid;
	request.open("GET", (address + data), true);
	request.send(null);
}

function updateTotal(total){
	target = document.getElementById("total");
	var formatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',

});
	target.innerHTML = "<strong>" + formatter.format((total + priceChanges + taxChanges)) + "</strong>";
}

function updateTax(tax) {
	target = document.getElementById("tax");
	var formatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',

});
	target.innerHTML = formatter.format((tax + taxChanges));

}

function updatePrice(subtotal) {
	target = document.getElementById("subtotal");
	var formatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',

});
	target.innerHTML = formatter.format((subtotal + priceChanges));

}

function updateCartSize(cartSize){
	target1 = document.getElementById("numCartItems");
	target1.innerHTML = cartSize + cartChanges;
}