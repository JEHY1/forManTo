//세정
var checkAll = document.querySelector('#checkAll');

checkAll.addEventListener('click', function () {

    var isChecked = checkAll.checked;

    if (isChecked) {
        const checkboxes = document.querySelectorAll('.chk');
        console.log('test');
        for (let i = 0; i < checkboxes.length; i++) {
            console.log('test2');
            let checkbox = checkboxes[i];
            checkbox.checked = true;
        }
    }

    else {
        const checkboxes = document.querySelectorAll('.chk');
        for (let i = 0; i < checkboxes.length; i++) {
            let checkbox = checkboxes[i]
            checkbox.checked = false;
        }
    }
});


// 하나 미체크시 전체 체크 없어짐
////////////////////////////////////////////////////////////

var checkboxes = document.querySelectorAll('.chk');
for (let i = 0; i < checkboxes.length; i++) {
    let checkbox = checkboxes[i]
    checkbox.addEventListener('click', function () {

        const totalCnt = checkboxes.length;

        const checkedCnt = document.querySelectorAll('.chk:checked').length;

        if (totalCnt == checkedCnt) {
            document.querySelector('#checkAll').checked = true;
        }
        else {
            document.querySelector('#checkAll').checked = false;

        }

    });
}


const orderButton = document.getElementById("order-btn");

if(orderButton){
    orderButton.addEventListener("click", function(){
        let body = JSON.stringify({
             totalPrice : document.getElementById("totalPriceId").value,

        });
    });
}


