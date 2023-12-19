window.onload = function() {
    let currentURL = window.location.href;
    let number = currentURL.match(/\d+$/);
    console.log(number[0]);

}


function handleSubmit() {
    var nameInputValue = document.querySelector('.name_input').value;
    var commentInputValue = document.querySelector('.comment_input').value;

    if (nameInputValue === "" || commentInputValue === "") {
        alert("Please fill in all fields.");
    }
    else {
        const data = {
            parentId: id,
            contents: commentInputValues,
            memberName: nameInputValue
        }
        fetch(`http://localhost:8080/API/subboard/save`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .catch(error => console.error('Error:', error));

    }

    console.log('Name:', nameInputValue);
    console.log('Comment:', commentInputValue);
}



