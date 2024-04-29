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

    let values = Array.from(document.getElementsByClassName('productNames'));

    Array.from(itemButtons).forEach((button, index) => {
        button.addEventListener('click', () => {
            let space = document.getElementById('productSpace');
            let productInfo = document.createElement('div');
            productInfo.setAttribute('class', 'row');
            let productName = document.createElement('div');
            productName.setAttribute('class', 'col-8');
            productName.setAttribute('text', '상품이름');
            productName.textContent = '상품 이름';
            let buttonCol = document.createElement('div');
            buttonCol.setAttribute('class', 'col-4');


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
            count.textContent = "1";
            let incButton = document.createElement('button');
            incButton.setAttribute('type', 'button');
            incButton.setAttribute('class', 'btn btn-outline-secondary');
            incButton.textContent = '+';

            decButton.addEventListener('click', function() {
                if(this.nextElementSibling.value - 1 > 0){
                    this.nextElementSibling.setAttribute('value', this.nextElementSibling.value - 1);
                    this.nextElementSibling.textContent = this.nextElementSibling.value;
                }
            });

            incButton.addEventListener('click', function() {

                this.previousElementSibling.setAttribute('value', parseInt(this.previousElementSibling.value) + 1);
                this.previousElementSibling.textContent = this.previousElementSibling.value;

            });



            space.appendChild(productInfo);
            productInfo.appendChild(productName);
            productInfo.appendChild(buttonCol);
            buttonCol.appendChild(buttonGroup);
            buttonGroup.appendChild(decButton);
            buttonGroup.appendChild(count);
            buttonGroup.appendChild(incButton);
        });

    });

}