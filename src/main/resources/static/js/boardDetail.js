const baseUrl = 'http://localhost:8080';

window.onload = function () {
    const idFromURL = getQueryParam('postId');
    const formData = new FormData();
    formData.append('parentId', idFromURL);

    fetch(`${baseUrl}/API/subboard/report`, {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            updatePageContent(data);
            console.log(data);
        })
        .catch(error => console.error('Error:', error));
}

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function updatePageContent(data) {
    document.querySelector('.board_view .title').textContent = data.contents.title;
    document.querySelector('.board_view .info .board_num').textContent = data.contents.id;
    document.querySelector('.board_view .info .board_writer').textContent = data.contents.memberName;

    let date = new Date(data.contents.insDate);
    let formattedDate = `${date.getFullYear()}.${date.getMonth() + 1}.${date.getDate()}`;
    document.querySelector('.board_view .info .board_date').textContent = formattedDate;
    document.querySelector('.board_view .cont').innerHTML = data.contents.contents.replace(/\r?\n/g, '<br>');

    let commentList = document.querySelector('.comment_list');
    commentList.innerHTML = '';

    let commentTitle = document.createElement('p');
    commentTitle.classList.add('comment_title');
    commentTitle.textContent = 'Comment';
    commentList.appendChild(commentTitle);

    data.subContents.forEach(comment => {
        let commentItem = document.createElement('div');
        commentItem.innerHTML = `
            <div class="writer">${comment.memberName}</div>
            <div class="comment">${comment.contents}</div>
        `;
        commentList.appendChild(commentItem);
    });
}

function handleSubmit() {
    let nameInputValue = document.querySelector('.name_input').value;
    let commentInputValue = document.querySelector('.comment_input').value;

    if (nameInputValue === "" || commentInputValue === "") {
        alert("Please fill in all fields.");
    } else {
        const idFromURL = getQueryParam('postId');
        const formData = new FormData();
        formData.append('parentId', idFromURL);
        formData.append('contents', commentInputValue);
        formData.append('memberName', nameInputValue);
        fetch(`${baseUrl}/API/subboard/save`, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.status === 200) {
                    window.location.reload();
                } else {
                    window.location.reload();
                }
            })
            .catch(error => console.error('Error:', error));
    }
}



