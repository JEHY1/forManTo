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
                count.setAttribute('class', 'btn btn-outline-secontary disabled');
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
                    console.log(this.previousElementSibling.firstElementChild.firstElementChild.nextElementSibling.value * this.nextElementSibling.value);

                    //여기부터

                    totalPrice.setAttribute('value',  parseInt(totalPrice.value) - parseInt(this.previousElementSibling.firstElementChild.firstElementChild.nextElementSibling.value * this.nextElementSibling.value));
                    totalPriceText.textContent = totalPrice.value + '원';
                    this.parentNode.remove();
                });

                space.appendChild(productInfo);
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