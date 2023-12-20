// const login = () => {
//     event.preventDefault(); // Prevent the default form submission
//
//     // Get the form data (you may use FormData or other methods depending on your setup)
//     const formData = {
//         MemberEmail: document.getElementById('userId').value,
//         MemberPassword: document.getElementById('userPassword').value
//     };
//
//     // Perform your login logic, e.g., send a request to the server
//     // Assume a successful login for demonstration purposes
//     const loginSuccessful = true;
//
//     if (loginSuccessful) {
//         // Redirect to the desired URL
//         window.location.href = '/ADG/document';
//     } else {
//         // Handle unsuccessful login (show an error message, etc.)
//         console.log('Login failed');
//     }
// }

function login() {
    // 폼 요소에 입력된 값을 가져오기
    let email = document.getElementById('userId').value;
    let password = document.getElementById('userPassword').value;

    const formData = new FormData();
    formData.append('MemberEmail', email);
    formData.append('MemberPassword', password);
    // 서버로 POST 요청 보내기
    fetch('http://localhost:8080/login', {
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