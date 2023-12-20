let clickedCategoryIndex = null;
let docData = null;

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function handleTitleClick(event) {
    // Get the clicked category title element
    const clickedTitle = event.target;
    // Get the data-index attribute value
    clickedCategoryIndex = clickedTitle.getAttribute('data-index');
    console.log('Clicked category index:', clickedCategoryIndex);

    // 댓글 업데이트
    let commentList = document.querySelector('.comment_list');
    commentList.innerHTML = ''; // 기존 댓글 초기화

    // <p class="comment_title">Comment</p> 추가
    let commentTitle = document.createElement('h1');
    commentTitle.classList.add('comment_title');
    commentTitle.textContent = 'Comment';
    commentList.appendChild(commentTitle);

    docData.subDocumentList.forEach(comment => {
        if (String(clickedCategoryIndex) === String(comment.id)) {
            console.log(comment);
            let commentItem = document.createElement('div');
            commentItem.innerHTML = `
                <div class="writer">${comment.memberName}</div>
                <div class="comment">${comment.contents}</div>
            `;
            commentList.appendChild(commentItem);
        }
    });
}




const keyFromURL = getQueryParam('dockey');
console.log('dockey:', keyFromURL);

// 파일 이름으로 파일 불러와서 내용채우기
fetch(`http://localhost:8080/API/documentDetail?dockey=${keyFromURL}`, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({})
})
    .then(response => response.json())
    .then(data => {
        console.log(data);
        docData = data;
        const escapedXmlString = data.document.documentContents.replace(/&/g, '&amp;');
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(escapedXmlString, "text/xml");

        const resultContainer = document.querySelector('.result_content');

        function Extraction(node, container, includeContentItems) {
            for (let i = 0; i < node.children.length; i++) {
                const childNode = node.children[i];

                if (childNode.nodeName === "category") {
                    const titleNode = childNode.querySelector("title");
                    if (titleNode) {
                        const titleText = titleNode.textContent.trim();
                        if (titleText) {
                            const titleElement = document.createElement('h2');
                            titleElement.textContent = titleText;
                            titleElement.id = "category_title";
                            titleElement.setAttribute('data-index', i+1);
                            titleElement.addEventListener('click', handleTitleClick);
                            container.appendChild(titleElement);
                        }
                    }

                    if (includeContentItems) {
                        const contentNode = childNode.querySelector("content");
                        if (contentNode) {
                            const itemNodes = contentNode.querySelectorAll("item");
                            itemNodes.forEach((itemNode, index) => {
                                const itemText = itemNode.textContent.trim();
                                if (itemText) {
                                    const itemElement = document.createElement('p');
                                    itemElement.textContent = itemText;
                                    container.appendChild(itemElement);
                                }
                            });
                        }
                    }
                }
                Extraction(childNode, container, includeContentItems);
            }
        }

        Extraction(xmlDoc.documentElement, resultContainer, true);

    })
    .catch(error => console.error('Error:', error));

function handleSubmit() {
    // Use the clicked category index in the handleSubmit function
    console.log('Current clicked category index in handleSubmit:', clickedCategoryIndex);

    let nameInputValue = document.querySelector('.name_input').value;
    let commentInputValue = document.querySelector('.comment_input').value;

    if (nameInputValue === "" || commentInputValue === "") {
        alert("Please fill in all fields.");
    } else {
        const idFromURL = getQueryParam('dockey');
        const formData = new FormData();
        formData.append('parentId', idFromURL);
        formData.append('itemId', clickedCategoryIndex);
        formData.append('memberName', nameInputValue);
        formData.append('contents', commentInputValue);

        console.log(idFromURL);
        console.log(commentInputValue);
        console.log(nameInputValue);
        console.log(clickedCategoryIndex);

        fetch(`http://localhost:8080/API/subdocument/save`, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.status === 200) {
                    alert('Successfully commented!');
                    window.location.reload();
                } else {
                    alert('Error on Comment');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}