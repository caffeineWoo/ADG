const baseUrl = 'http://localhost:8080';

function handlePost() {
    let titleValue = document.getElementById('titleInput').value;
    let nameValue = document.getElementById('nameInput').value;
    let textareaValue = document.getElementById('textareaInput').value;

    if (titleValue === "" || nameValue === "" || textareaValue === "") {
        alert("Please fill in all fields.");
    } else {
        const formData = new FormData();

        formData.append('title', titleValue);
        formData.append('contents', textareaValue);
        formData.append('memberName', nameValue);

        fetch(`${baseUrl}/API/board/save`, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.status === 200) {
                    alert('Successfully post!');
                    window.location.href = '/ADG/board';
                } else {
                    alert('Error on POST');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}
