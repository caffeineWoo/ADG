function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

window.onload = function () {
    const idFromURL = getQueryParam('dockey');
    const formData = new FormData();
    formData.append('parentId', idFromURL);

    fetch(`http://localhost:8080/API/document/review`, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            console.log(response);
            // if (response.status !== 200) {
            //     alert('Please report first!');
            //     window.location.href = `/ADG/documentDetail?dockey=${idFromURL}`;
            // }
        })
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}