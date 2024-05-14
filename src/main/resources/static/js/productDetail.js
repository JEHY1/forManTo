//재현

moveTopButton = document.getElementById('move-top-btn');

if(moveTopButton){
    moveTopButton.addEventListener('click', () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });
}

const itemButtons = document.getElementsByClassName('item-btns');

if(itemButtons){

    let productNameValues = Array.from(document.getElementsByClassName('productNames'));
    let productIdValues = Array.from(document.getElementsByClassName('productIds'));
    let productPriceValues = Array.from(document.getElementsByClassName('productPrices'));
    let totalPrice = document.getElementById('totalPrice');
    let totalPriceText =  document.getElementById('totalPriceText');

    Array.from(itemButtons).forEach((button, index) => {
        button.addEventListener('click', () => {

            if(document.getElementById('productId' + productIdValues[index].value) == null){
                let space = document.getElementById('productSpace');
                let productInfo = document.createElement('div');
                productInfo.setAttribute('class', 'row');
                productInfo.setAttribute('id', 'productId' + productIdValues[index].value);
                let productName = document.createElement('div');
                productName.setAttribute('class', 'col-8 d-flex align-items-center');



                let selectedInfo = document.createElement('input');
                selectedInfo.setAttribute('type', 'hidden');
                selectedInfo.setAttribute('class', 'selectedProductIds');
                selectedInfo.setAttribute('value', productIdValues[index].value);



                productName.setAttribute('text', productNameValues[index].value);
                productName.textContent = productNameValues[index].value;
                let buttonCol = document.createElement('div');
                buttonCol.setAttribute('class', 'col-3');

                let buttonGroup = document.createElement('div');
                buttonGroup.setAttribute('class', 'btn-group');
                buttonGroup.setAttribute('role', 'group');
                buttonGroup.setAttribute('aria-label', 'basic outlined example');

                let decButton = document.createElement('button');
                decButton.setAttribute('type', 'button');
                decButton.setAttribute('class', 'btn btn-outline-secondary');
                decButton.textContent = '-';

                let count = document.createElement('button');
                count.setAttribute('type', 'button');
                count.setAttribute('class', 'btn btn-outline-secondary disabled orderQuantitys');
                count.setAttribute('value', 1);
                count.setAttribute('id', 'productId' + productIdValues[index].value + 'count');
                count.textContent = "1";

                let incButton = document.createElement('button');
                incButton.setAttribute('type', 'button');
                incButton.setAttribute('class', 'btn btn-outline-secondary');
                incButton.textContent = '+';

                let productPrice = document.createElement('input');
                productPrice.setAttribute('type', 'hidden');
                productPrice.setAttribute('value', productPriceValues[index].value);


                totalPrice.setAttribute('value',  parseInt(productPriceValues[index].value) + parseInt(totalPrice.value));
                totalPriceText.textContent = totalPrice.value + '원';

                decButton.addEventListener('click', function() {
                    if(this.nextElementSibling.value - 1 > 0){
                        this.nextElementSibling.setAttribute('value', this.nextElementSibling.value - 1);
                        this.nextElementSibling.textContent = this.nextElementSibling.value;

                        //최종 가격 변경
                        totalPrice.setAttribute('value',  parseInt(totalPrice.value) - parseInt(productPriceValues[index].value));
                        totalPriceText.textContent = totalPrice.value + '원';

                    }

                    else{
                        alert('1개 이하 구매 불가');
                    }
                });

                incButton.addEventListener('click', function() {
                    if(parseInt(this.previousElementSibling.value) + 1 < 11){
                        this.previousElementSibling.setAttribute('value', parseInt(this.previousElementSibling.value) + 1);
                        this.previousElementSibling.textContent = this.previousElementSibling.value;
                        totalPrice.setAttribute('value',  parseInt(productPriceValues[index].value) + parseInt(totalPrice.value));
                        totalPriceText.textContent = totalPrice.value + '원';
                    }
                    else{
                        alert('각 상품의 최대 구매 수량 : 10');
                    }
                });

                let deleteButton = document.createElement('button');
                deleteButton.setAttribute('type', 'button');
                deleteButton.setAttribute('class', 'btn-close col');
                deleteButton.setAttribute('aria-label', 'Close');

                deleteButton.addEventListener('click', function(){

                    totalPrice.setAttribute('value',  parseInt(totalPrice.value) - parseInt(this.previousElementSibling.firstElementChild.firstElementChild.nextElementSibling.value * this.nextElementSibling.value));
                    totalPriceText.textContent = totalPrice.value + '원';
                    this.parentNode.remove();
                });

                space.appendChild(productInfo);
                productInfo.appendChild(selectedInfo);
                productInfo.appendChild(productName);
                productInfo.appendChild(buttonCol);
                buttonCol.appendChild(buttonGroup);
                buttonGroup.appendChild(decButton);
                buttonGroup.appendChild(count);
                buttonGroup.appendChild(incButton);
                productInfo.appendChild(deleteButton);
                productInfo.appendChild(productPrice);
            }
            else{
                if(parseInt(document.getElementById('productId' + productIdValues[index].value + 'count').value) + 1 < 11){
                    document.getElementById('productId' + productIdValues[index].value + 'count').setAttribute('value', parseInt(document.getElementById('productId' + productIdValues[index].value + 'count').value) + 1);
                    document.getElementById('productId' + productIdValues[index].value + 'count').textContent = document.getElementById('productId' + productIdValues[index].value + 'count').value;

                    totalPrice.setAttribute('value',  parseInt(totalPrice.value) + parseInt(productPriceValues[index].value));
                    totalPriceText.textContent = totalPrice.value + '원';
                }
                else{
                    alert('각 상품의 최대 구매 수량 : 10');
                }
            }
        });
    });
}

const orderButton = document.getElementById('order-btn');

if (orderButton) {
    orderButton.addEventListener('click', () => {
        console.log("clicked");
        let body = JSON.stringify({
            productIds: toList(Array.from(document.getElementsByClassName('selectedProductIds'))),
            counts: toList(Array.from(document.getElementsByClassName('orderQuantitys'))),
            totalPrice : document.getElementById('totalPrice').value
        });

        httpRequest(`/order`, 'POST', body)
            .then(response => {
                if (response.ok) {
                    // 응답이 성공했을 때
                    alert('success');
                    return response.text(); // HTML 내용을 텍스트로 반환
                } else {
                    // 응답이 실패했을 때
                    alert('fail');
                    location.replace('/order');
                    throw new Error('Failed to fetch HTML');
                }
            })
            .then(html => {
                // 받아온 HTML을 현재 페이지에 적용
                document.documentElement.innerHTML = html;
//                window.history.pushState({productIds : toList(Array.from(document.getElementsByClassName('selectedProductIds'))), counts : toList(Array.from(document.getElementsByClassName('orderQuantitys')))}, '', '/order');
            })
            .catch(error => {
                console.error(error);
            });
    });
}


const questionButton = document.getElementById('question-btn');

if(questionButton){
    questionButton.addEventListener('click', () => {
        console.log('click');
        let body = JSON.stringify({
           question : document.getElementById('question').value,
           productGroupId : document.getElementById('productGroupId').value
        });

        httpRequest(`/api/QnAQuestion`, 'POST', body)
        .then(response => {
            if (response.ok) {
                // 응답이 성공했을 때
                alert('success');
                location.replace('/productDetail/' + document.getElementById('productGroupId').value);
            } else {
                alert('fail');
            }
        });
    });
}

const paymentButton =document.getElementById('payment-btn');

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
        receiverPhone : document.getElementById('receiverPhone').value
    });

    console.log(body);

    httpRequest(`/api/payment`, 'POST', body);
}


function httpRequest(url, method, body) {
    return fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: body
    });
}

function toList(elements){
    let list = [];
    elements.forEach(element => {
        if(element.value != ''){
            list.push(element.value);
        }
    });
    return list;
}

function test(thisCheckBox){
    const checkboxes = Array.from(document.getElementsByClassName('payType'));
    document.getElementById('paymentType').value = thisCheckBox.parentElement.textContent;
    checkboxes.forEach(checkbox => {
        if (checkbox !== thisCheckBox) {
            checkbox.checked = false;
        }
    });
}