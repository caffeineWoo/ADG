// Assuming you have a function called register() for validation
function register() {
    // Your validation logic goes here
    // Example: Check if the email is valid
    const email = document.getElementById('userId').value;
    const emailRegex = /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
    const isUserValid = emailRegex.test(email);

    // Enable or disable the button based on the validation result
    const registerButton = document.getElementById('register_btn');
    registerButton.disabled = !isUserValid;

    // Change style based on validation result
    if (!isUserValid) {
        registerButton.style.backgroundColor = '#999';
        registerButton.style.color = 'white';
        // You can add more styling properties here
    } else {
        // Reset styles to default when the input is valid
        registerButton.style.backgroundColor = '';
        registerButton.style.color = '';
    }
}

// Attach the register() function to the input elements' events for real-time validation
document.getElementById('userId').addEventListener('input', register);
