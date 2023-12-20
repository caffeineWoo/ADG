let clickedCategoryIndex = null;
let docData = null;

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function handleTitleClick(event) {
    const clickedTitle = event.target;
    clickedCategoryIndex = clickedTitle.getAttribute('data-index');
    console.log('Clicked category index:', clickedCategoryIndex);

    let commentList = document.querySelector('.comment_list');
    commentList.innerHTML = '';

    docData.subDocumentList.forEach(comment => {
        if (String(clickedCategoryIndex) === String(comment.itemId)) {
            console.log(comment);

            let commentItemForCommentList = document.createElement('div');
            commentItemForCommentList.innerHTML = `
                <div class="writer">${comment.memberName}</div>
                <div class="comment">${comment.contents}</div>
            `;
            commentList.appendChild(commentItemForCommentList);
        }
    });
}




const keyFromURL = getQueryParam('dockey');
console.log('dockey:', keyFromURL);

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
        const categoryContainer = document.querySelector('.category_contents');

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
                                    itemElement.className = "hidden";
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
        Extraction(xmlDoc.documentElement, categoryContainer, false);


        if (docData != null) {
            console.log("ok");
            comments = docData.subDocumentList;
            const categoryContents = document.querySelector('.category_contents');
            comments.forEach(comment => {
                const category = categoryContents.querySelector(`[data-index="${comment.itemId}"]`);
                category.setAttribute('id', "summary_contents");

                if (category) {
                    const commentDiv = document.createElement('div');

                    const writerDiv = document.createElement('div');
                    writerDiv.className = 'writer';
                    writerDiv.textContent = comment.memberName;
                    commentDiv.appendChild(writerDiv);

                    const commentsDiv = document.createElement('div');
                    commentsDiv.className = 'comments';
                    commentsDiv.textContent = comment.contents;
                    commentDiv.appendChild(commentsDiv);
                    category.appendChild(commentDiv);
                }
            });
        }
    })
    .catch(error => console.error('Error:', error));

function handleSubmit() {
    console.log('Current clicked category:', clickedCategoryIndex);

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
                    window.location.reload();
                } else {
                    alert('Error on Comment');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}

function show_paragraph() {
    let hiddenElements = document.querySelectorAll('.hidden');

    hiddenElements.forEach(element => {
        element.style.display = (element.style.display === 'none' || element.style.display === '') ? 'block' : 'none';
    });
}
