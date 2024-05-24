function payment(){
    console.log('click');
    let body = JSON.stringify({
        paymentPrice : parseInt(document.getElementById('paymentPrice').textContent),
        paymentType : document.getElementById('paymentType').value,
        address : document.getElementById('sample6_address').value + ' ' + document.getElementById('sample6_detailAddress').value,
        deliveryFee : parseInt(document.getElementById('deliveryFee').textContent),
        productIds : toList(Array.from(document.getElementsByClassName('productIds'))),
        productCounts : toList(Array.from(document.getElementsByClassName('productCounts'))),
        receiver : document.getElementById('receiver').value,
        receiverPhone : document.getElementById('receiverPhone').value,
        cartIds : toList(Array.from(document.getElementsByClassName('cartIds')))
    });

    console.log(body);

    httpRequest(`/api/payment`, 'POST', body).then(response => {
        if(response.ok){
            alert('주문성공');
        }
        else{
            alert('주문 오류');
        }
    })
}


function checkBox(thisCheckBox){
    const checkboxes = Array.from(document.getElementsByClassName('payType'));
    document.getElementById('paymentType').value = thisCheckBox.parentElement.textContent;
    checkboxes.forEach(checkbox => {
        if (checkbox !== thisCheckBox) {
            checkbox.checked = false;
        }
    });
}