const baseUrl = 'http://localhost:8080';
function login() {
    let email = document.getElementById('userId').value;
    let password = document.getElementById('userPassword').value;

    const formData = new FormData();
    formData.append('MemberEmail', email);
    formData.append('MemberPassword', password);
    // 서버로 POST 요청 보내기
    fetch(`${baseUrl}/login`, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            console.log(response);
            // if (response.status === 200) {
            //     // 로그인 성공 시의 동작
            //     alert('Login successful!');
            //     // 필요한 추가 동작 수행
            // } else {
            //     // 로그인 실패 시의 동작
            //     alert('Login failed. Please check your credentials.');
            // }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}