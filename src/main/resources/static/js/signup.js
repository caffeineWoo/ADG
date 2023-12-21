function register() {
    const email = document.getElementById('userId').value;
    const emailRegex = /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
    const isEmailValid = emailRegex.test(email);

    const password = document.getElementById('userPassword').value;
    const isPasswordValid = password.length >= 8;

    const name = document.getElementById('userName').value;
    const isNameValid = name.length >= 2;

    const registerButton = document.getElementById('register_btn');
    registerButton.disabled = !(isEmailValid && isPasswordValid && isNameValid);

    if (!(isEmailValid && isPasswordValid && isNameValid)) {
        registerButton.style.backgroundColor = '#999';
        registerButton.style.borderColor = '#999';
        registerButton.style.color = 'white';
    } else {
        registerButton.style.backgroundColor = '';
        registerButton.style.color = '';
    }
}

document.getElementById('userId').addEventListener('input', register);
document.getElementById('userPassword').addEventListener('input', register);
document.getElementById('userName').addEventListener('input', register);