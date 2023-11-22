function handleFileSelect() {
    var dropArea = document.getElementById('dropArea');
    dropArea.classList.remove('drag-over');

    var fileInput = document.getElementById('fileInput');
    var files = fileInput.files;

    handleFiles(files);
}

function handleDrop(event) {
    event.preventDefault();
    var dropArea = document.getElementById('dropArea');
    dropArea.classList.remove('drag-over');

    var files = event.dataTransfer.files;
    handleFiles(files);
}

function handleDragOver(event) {
    event.preventDefault();
    var dropArea = document.getElementById('dropArea');
    dropArea.classList.add('drag-over');
}

function handleFiles(files) {
    var fileInput = document.getElementById('fileInput');
    fileInput.files = files;
}

function uploadFile() {
    var fileInput = document.getElementById('fileInput');
    var progressBar = document.getElementById('progressBar');
    var status = document.getElementById('status');

    var file = fileInput.files[0];

    if (!file) {
        alert('파일을 선택해주세요.');
        return;
    }
    console.log(file);

    var formData = new FormData();
    formData.append('file', file);

    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.status === 200) {
            progressBar.value = 100;
            status.innerText = '업로드 완료!';
        } else {
            status.innerText = '업로드 실패. 다시 시도해주세요.';
        }
    };

    xhr.open('POST', 'upload.php', true);
    xhr.send(formData);
}
