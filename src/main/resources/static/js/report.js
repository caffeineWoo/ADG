const baseUrl = 'http://localhost:8080';
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

window.onload = function () {
    const idFromURL = getQueryParam('dockey');
    const formData = new FormData();
    formData.append('parentId', idFromURL);

    fetch(`${baseUrl}/API/document/review`, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            // Assuming 'data' is an array of objects with 'gptSummary' property
            const reportContentDiv = document.querySelector('.report_content');

            data.forEach(item => {
                const summaryParagraph = document.createElement('li');
                summaryParagraph.setAttribute('id', 'summary_paragraph')
                summaryParagraph.textContent = item.gptSummary;
                reportContentDiv.appendChild(summaryParagraph);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

