const login = () => {
    event.preventDefault(); // Prevent the default form submission

    // Get the form data (you may use FormData or other methods depending on your setup)
    const formData = {
        MemberEmail: document.getElementById('userId').value,
        MemberPassword: document.getElementById('userPassword').value
    };

    // Perform your login logic, e.g., send a request to the server
    // Assume a successful login for demonstration purposes
    const loginSuccessful = true;

    if (loginSuccessful) {
        // Redirect to the desired URL
        window.location.href = '/ADG/document';
    } else {
        // Handle unsuccessful login (show an error message, etc.)
        console.log('Login failed');
    }
}