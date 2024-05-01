function httpRequest(url, method, body, success, fail){
    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: body
    }).then(response => {
        if(response.status == 200 || response.status == 201){
            return response.json();
        }
        else{
            throw new Error('response status is not ok');
        }
    }).then(data => {
        success(data);
    }).catch(error => {
        fail();
    });
}

const issueAuthenticationKeyButton = document.getElementById('issueAuthenticationKey-btn');

if(issueAuthenticationKeyButton){
    let authenticationKey;
    issueAuthenticationKeyButton.addEventListener('click', () => {
        function issuedAuthenticationKeySuccess(data){
            authenticationKey = data.authKey;
            console.log('authenticationKey : ' + authenticationKey);
            if(document.getElementById('keyInputBox') == null){
                let keyInputBox = document.createElement('input');
                keyInputBox.setAttribute('type', 'text');
                keyInputBox.setAttribute('id', 'keyInputBox');
                keyInputBox.setAttribute('placeholder', 'input authentication key');
                document.body.append(keyInputBox);

                let signupButton = document.createElement('button');
                signupButton.setAttribute('type', 'button');
                signupButton.textContent = "signup";
                signupButton.addEventListener('click', () => {

                    if(document.getElementById('keyInputBox').value === authenticationKey){
                        function signupSuccess(){
                            alert('signup Success');
                            location.replace('/login');
                        }

                        function signupFail(){
                            alert('signup Fail');
                        }

                        let body2 = JSON.stringify({
                            username: document.getElementById('username').value,
                            password: document.getElementById('password').value,
                            email: document.getElementById('email').value,
                            phoneNum : document.getElementById('phoneNum').value,
                            adminKey: document.getElementById('adminKey').value

                        });

                        httpRequest(`/user`, 'POST', body2, signupSuccess, signupFail);
                    }
                    else{
                        alert('not matched');
                    }


                });
                document.body.appendChild(signupButton);
            }
            alert('send mail success');
        }

        function issuedAuthenticationKeyFail(){
            alert('send mail Fail');
        }

        let body = JSON.stringify({
            email: document.getElementById('email').value
        });

        httpRequest(`/authMail`, 'POST', body, issuedAuthenticationKeySuccess, issuedAuthenticationKeyFail);
    });
}


//

//const checkBoxs = document.getElementsByClassName('checkBox');
//
//if(checkBoxs){
//    Array.from(checkBoxs).forEach(checkBox => {
//        checkBox.addEventListener('change', function() {
//
//         console.log('is changed');
//            if(this.checked){
//
//
//                const label = this.previousElementSibling;
//                if(label){
//                    this.value = label.textContent.trim();
//                }
//            }
//            else{
//                this.value = null;
//            }
//        });
//    });
//}

function toList(values){
    let list = [];
    values.forEach(value => {
        if(value.value != ''){
            list.push(value.value);
        }
    });

    return list;
}
