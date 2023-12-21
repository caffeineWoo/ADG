function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

window.onload = function () {
    const idFromURL = getQueryParam('dockey');

    fetch(`http://localhost:8080/API/document/combine?Pid=${idFromURL}`, {
        method: 'GET',
    })
        .then(response => {
            console.log(response);
            if (response.status !== 200) {
                alert('Please report first!');
                window.location.href = `/ADG/documentDetail?dockey=${idFromURL}`;
            }
        })
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}