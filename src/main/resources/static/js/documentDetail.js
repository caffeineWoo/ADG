// 기존에 생성한 자동문서 출력하는 부분

// 결과창 화면 업데이트 함수
function updateResultContainer(data) {
    const resultContainer = document.querySelector('.result_content');
    resultContainer.innerHTML = '';

    const groupedData = {};
    data.forEach(item => {
        if (!groupedData[item.category]) {
            groupedData[item.category] = [];
        }
        groupedData[item.category].push(item);
    });

    for (const category in groupedData) {
        const categoryContainer = document.createElement('div');
        categoryContainer.classList.add('category_container');

        const categoryHeading = document.createElement('h2');
        categoryHeading.textContent = category;
        categoryContainer.appendChild(categoryHeading);

        groupedData[category].forEach(item => {
            const resultItem = document.createElement('div');
            resultItem.classList.add('result_item');

            const nameHeading = document.createElement('h3');
            nameHeading.textContent = item.name;

            const contentParagraph = document.createElement('p');
            contentParagraph.textContent = item.content;

            resultItem.appendChild(nameHeading);
            resultItem.appendChild(contentParagraph);
            categoryContainer.appendChild(resultItem);
        });

        resultContainer.appendChild(categoryContainer);
    }
}

// 키워드창 화면 업데이트 함수
function updateKeywordContainer(data) {
    const resultContainer = document.querySelector('.keyword_content');
    resultContainer.innerHTML = '';

    const groupedData = {};
    data.forEach(item => {
        if (!groupedData[item.category]) {
            groupedData[item.category] = [];
        }
        groupedData[item.category].push(item);
    });

    for (const category in groupedData) {
        const categoryContainer = document.createElement('div');
        categoryContainer.classList.add('category_container');

        const categoryHeading = document.createElement('h2');
        categoryHeading.textContent = category;
        categoryContainer.appendChild(categoryHeading);

        groupedData[category].forEach(item => {
            const resultItem = document.createElement('div');
            resultItem.classList.add('result_item');

            const nameHeading = document.createElement('h3');
            nameHeading.textContent = item.name;

            resultItem.appendChild(nameHeading);
            categoryContainer.appendChild(resultItem);
        });

        resultContainer.appendChild(categoryContainer);
    }
}

// 선택한 파일 이름 URL에서 불러오기
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
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
        data.items.forEach(item => {
            if (item.title === fileTitleFromURL) {
                // Parse the XML string
                const parser = new DOMParser();
                const xmlDoc = parser.parseFromString(item.contents, 'text/xml');
                const getdata = [];
                const categories = xmlDoc.querySelectorAll('category');
                categories.forEach((category) => {
                    const categoryName = category.getAttribute('name');

                    // Iterate over content elements
                    const contentElements = category.querySelectorAll('content > *');
                    contentElements.forEach((contentElement) => {
                        const name = contentElement.tagName.toLowerCase();
                        const content = contentElement.textContent;

                        // Push to the result array
                        getdata.push({ category: categoryName, name, content });
                    });
                });
                updateResultContainer(getdata);
                updateKeywordContainer(getdata);

            }
        });
    })
    .catch(error => console.error('Error:', error));
