function handlePost() {
    let titleValue = document.getElementById('titleInput').value;
    let nameValue = document.getElementById('nameInput').value;
    let textareaValue = document.getElementById('textareaInput').value;

    if (titleValue === "" || nameValue === "" || textareaValue === "") {
        alert("Please fill in all fields.");
    } else {
        // FormData 객체 생성
        const formData = new FormData();

        // FormData에 필드 추가
        formData.append('title', titleValue);
        formData.append('contents', textareaValue);
        formData.append('memberName', nameValue);

        fetch(`http://localhost:8080/API/board/save`, {
            method: 'POST',
            body: formData  // FormData 객체를 사용하여 데이터 전송
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
