window.onload = function () {
    const idFromURL = getQueryParam('postId');
    const formData = new FormData();
    formData.append('parentId', idFromURL);

    fetch(`http://localhost:8080/API/subboard/report`, {
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
    // 제목 업데이트
    document.querySelector('.board_view .title').textContent = data.contents.title;

    // 번호 업데이트
    document.querySelector('.board_view .info .board_num').textContent = data.contents.id;

    // 작성자 업데이트
    document.querySelector('.board_view .info .board_writer').textContent = data.contents.memberName;

    // 날짜 형식 변환 및 업데이트
    let date = new Date(data.contents.insDate);
    let formattedDate = `${date.getFullYear()}.${date.getMonth() + 1}.${date.getDate()}`;
    document.querySelector('.board_view .info .board_date').textContent = formattedDate;

    // 내용 업데이트
    document.querySelector('.board_view .cont').innerHTML = data.contents.contents.replace(/\r?\n/g, '<br>');

    // 댓글 업데이트
    let commentList = document.querySelector('.comment_list');
    commentList.innerHTML = ''; // 기존 댓글 초기화

    // <p class="comment_title">Comment</p> 추가
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
        fetch(`http://localhost:8080/API/subboard/save`, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.status === 200) {
                    alert('Successfully comment!');
                    window.location.reload();
                } else {
                    alert('Error on Comment');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}



