//세정
var checkAll = document.querySelector('#checkAll');

checkAll.addEventListener('click', function () {

    var totalPriceResults = document.querySelectorAll(".totalPriceResult");
    var isChecked = checkAll.checked;

    if (isChecked) {
        const checkboxes = document.querySelectorAll('.chk');

        for (let i = 0; i < checkboxes.length; i++) {
            let checkbox = checkboxes[i];
            checkbox.checked = true;
            // 전체 체크시 총 판매가에 나타남
            document.getElementById("totalPriceId").innerText = parseInt(totalPriceResults[i].innerText)
                + parseInt(totalPriceResults[i+1].innerText);

        }
    }

    else {
        const checkboxes = document.querySelectorAll('.chk');
        for (let i = 0; i < checkboxes.length; i++) {
            let checkbox = checkboxes[i]
            checkbox.checked = false;
            document.getElementById("totalPriceId").innerText = 0;

        }
    }
})


// 하나 미체크시 전체 체크 없어짐
////////////////////////////////////////////////////////////


var totalPriceResults = document.querySelectorAll(".totalPriceResult");
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
        // 체크박스 하나 클릭시 총 판매가 플러스
        if(checkboxes[i].checked){
            document.getElementById("totalPriceId").innerText =parseInt(document.getElementById("totalPriceId").innerText)
            +parseInt(totalPriceResults[i].innerText);
        }
        // 체크박스 하나 클릭시 총 판매가 빼기
        if(!(checkboxes[i].checked)){
            document.getElementById("totalPriceId").innerText =  document.getElementById("totalPriceId").innerText
            - totalPriceResults[i].innerText;
        }
    });
}




//  <!-- 수량 변경시 구매가 바뀜 -->

function changeCount() {
    var priceCounts = document.querySelectorAll(".priceCount");
    var totalPriceResults = document.querySelectorAll(".totalPriceResult");
    var originalPrice = document.querySelectorAll(".originalPrice");
    var chk = document.querySelectorAll(".chk");
    // var isChecked = checkAll.checked;
    var checkSum = 0;


    //querySelectorAll은 배열로 반환
    for (let i = 0; i < priceCounts.length; i++) {
        let x = priceCounts[i].value;
        let unitPrice = parseInt(originalPrice[i].innerText); // totalPriceResult 클래스 안의 텍스트 값을 정수로 파싱하여 추출
        totalPriceResults[i].innerHTML = x * unitPrice;

        // 체크시 총 판매가에 최종 바뀜
        if (chk[i].checked) {
            checkSum = parseInt(totalPriceResults[i].innerText) + checkSum;
            document.getElementById("totalPriceId").innerText = checkSum;
        }



    }
}
