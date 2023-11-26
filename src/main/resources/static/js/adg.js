// 모달창 열고 닫는 함수
const setModal = function () {
	const modalOpenButton = document.getElementById('modalOpenButton');
	const modalCloseButton = document.getElementById('modalCloseButton');
	const modal = document.getElementById('modalContainer');

	modalOpenButton.addEventListener('click', () => {
		modal.classList.remove('hidden');
	});

	modalCloseButton.addEventListener('click', () => {
		modal.classList.add('hidden');
		location.reload();
	});
}

const setModal2 = function () {
	const modalOpenButton2 = document.getElementById('modalOpenButton2');
	const modalCloseButton2 = document.getElementById('modalCloseButton2');
	const modal2 = document.getElementById('modalContainer2');

	modalOpenButton2.addEventListener('click', () => {
		modal2.classList.remove('hidden');
	});

	modalCloseButton2.addEventListener('click', () => {
		modal2.classList.add('hidden');
		location.reload();
	});
}

//파일 업로드 관련 함수
const isAdvancedUpload = function () {
	var div = document.createElement('div');
	return (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
}();

let draggableFileArea = document.querySelector(".drag_file_container");
let browseFileText = document.querySelector(".browse_files");
let uploadIcon = document.querySelector(".upload_icon");
let dragDropText = document.querySelector(".dynamic_message");
let fileInput = document.querySelector(".default_file_input");
let cannotUploadMessage = document.querySelector(".cannot_upload_message");
let cancelAlertButton = document.querySelector(".cancel_alert_button");
let uploadedFile = document.querySelector(".file_block");
let fileName = document.querySelector(".file_name");
let fileSize = document.querySelector(".file_size");
let progressBar = document.querySelector(".progress_bar");
let removeFileButton = document.querySelector(".remove_file_icon");
let uploadButton = document.querySelector(".upload_button");
let fileFlag = 0;
let selectedFile = ''; // 파일 업로드 창에서 선택한 파일이 이 변수에 저장됨
let getFile = '';

// 파일 업로드 변화 감지해서 선택한 파일 변경
function fileChange(e) {
	let fileInput = document.querySelector(".default_file_input");
	selectedFile = fileInput.files[0];
	uploadIcon.innerHTML = 'check_circle';
	dragDropText.innerHTML = 'File Dropped Successfully!';
	document.querySelector(".label").innerHTML = `drag & drop or <span class="browse_files"> <input type="file" class="default_file_input" onchange="fileChange(event)"/> <span class="browse_files_text" style="top: 0;"> browse file</span></span>`;
	uploadButton.innerHTML = `Upload`;
	fileName.innerHTML = fileInput.files[0].name;
	fileSize.innerHTML = (fileInput.files[0].size / 1024).toFixed(1) + " KB";
	uploadedFile.style.cssText = "display: flex;";
	progressBar.style.width = 0;
	fileFlag = 0;
	e.target.value = ''
}

// 파일 업로드
uploadButton.addEventListener("click", () => {
	// 파일 업로드 창에서 선택한 파일이 isFileUploaded 변수에 담고
	let isFileUploaded = selectedFile;
	// 선택한 파일이 있다면
	if (isFileUploaded != '') {
		if (fileFlag == 0) {
			// 이 부분에 서버로 파일 업로드하는 함수 작성
			console.log(selectedFile); // 현재는 선택한 파일의 정보 콘솔창에 출력
			var formData = new FormData();
			formData.append("file", selectedFile);
			fetch('http://localhost:8080/upload-file', {
				method: 'POST',
				body: formData
			}).then(response => console.log(response));

			// 아래는 파일 업로드 바 애니메이션
			fileFlag = 1;
			var width = 0;
			var id = setInterval(frame, 50);
			function frame() {
				if (width >= 390) {
					clearInterval(id);
					uploadButton.innerHTML = `<span class="material-icons-outlined upload_button_icon"> check_circle </span> Uploaded`;
				} else {
					width += 5;
					progressBar.style.width = width + "px";
				}
			}
		}
	} else {
		cannotUploadMessage.style.cssText = "display: flex; animation: fadeIn linear 1.5s;";
	}
});

// 파일 선택 안했을 때 에러창 지우는 것
cancelAlertButton.addEventListener("click", () => {
	cannotUploadMessage.style.cssText = "display: none;";
});

// 파일 드래그 앤 드롭
if (isAdvancedUpload) {
	["drag", "dragstart", "dragend", "dragover", "dragenter", "dragleave", "drop"].forEach(evt =>
		draggableFileArea.addEventListener(evt, e => {
			e.preventDefault();
			e.stopPropagation();
		})
	);

	["dragover", "dragenter"].forEach(evt => {
		draggableFileArea.addEventListener(evt, e => {
			e.preventDefault();
			e.stopPropagation();
			uploadIcon.innerHTML = 'file_download';
			dragDropText.innerHTML = 'Drop your file here!';
		});
	});

	draggableFileArea.addEventListener("drop", e => {
		uploadIcon.innerHTML = 'check_circle';
		dragDropText.innerHTML = 'File Dropped Successfully!';
		document.querySelector(".label").innerHTML = `drag & drop or <span class="browse_files"> <input type="file" class="default_file_input" onchange="fileChange(event)"/> <span class="browse_files_text" style="top: 0px; left: 0px;"> browse file</span> </span>`;
		uploadButton.innerHTML = `Upload`;

		let files = e.dataTransfer.files;
		selectedFile = files[0];
		console.log(files[0].name + " " + files[0].size);
		fileName.innerHTML = files[0].name;
		fileSize.innerHTML = (files[0].size / 1024).toFixed(1) + " KB";
		uploadedFile.style.cssText = "display: flex;";
		progressBar.style.width = 0;
		fileFlag = 0;
	});
}

// 선택한 파일 삭제
removeFileButton.addEventListener("click", (e) => {
	uploadedFile.style.cssText = "display: none;";
	selectedFile = '';
	fileName.innerHTML = null;
	fileSize.innerHTML = null;
	uploadIcon.innerHTML = 'file_upload';
	dragDropText.innerHTML = 'Drag & drop any file here';
	document.querySelector(".label").innerHTML = `or <span class="browse_files"> <input type="file" class="default_file_input onchange="fileChange(event)"/> <span class="browse_files_text">browse file</span> <span>from device</span> </span>`;
	uploadButton.innerHTML = `Upload`;
	document.querySelector(".label").innerHTML = `or <span class="browse_files"> <input type="file" class="default_file_input" onchange="fileChange(event)"/> <span class="browse_files_text">browse file</span> <span>from device</span> </span>`;
});

// 파일 생성 함수
const generateFile = () => {
	// 업로드 한 파일 중에 자동문서 생성할 파일 선택
	let fileList = document.getElementById('filelist');
	let DocumentSourcekey = fileList.options[fileList.selectedIndex].value;
	// 입력받은 파일 이름
	let DocumentTitle = document.getElementById('setfilename').value;
	// 입력 받은 카테고리
	let categoryInput = document.getElementById('category');
	let DocumentCategory = categoryInput.value;

	// 파일 생성
	fetch('http://localhost:8080/document/save', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ "DocumentSourcekey": DocumentSourcekey, "DocumentTitle": DocumentTitle, "DocumentCategory": DocumentCategory })
	})
		.then(response => response.json())
		.then(data => console.log(data))
		.catch(error => console.error('Error:', error));
}

// DB에 저장된 파일 목록 불러오기
fetch('http://localhost:8080/file/report', {
	method: 'GET',
	headers: {
		'Content-Type': 'application/json',
	},
	// body: JSON.stringify({})
})
	.then(response => response.json())
	.then(data => {
		data.forEach(item => {
			var optionElement = document.getElementById('filelist');
			var filelistContainer = document.querySelector('.dblist_content');
			var fileTitle = document.createElement('h2');
			fileTitle.textContent = item.fileTitle;
			fileTitle.id = 'adglist';
			filelistContainer.appendChild(fileTitle);
			var option = document.createElement('option');
			option.value = item.fileSourcekey;
			option.text = item.fileTitle;
			optionElement.add(option);
		});
	})
	.catch(error => console.error('Error:', error));


// ADG로 생성한 파일 목록 불러오기
fetch('http://localhost:8080/document/report?DocumentType=CATE', {
	method: 'POST',
	headers: {
		'Content-Type': 'application/json',
	},
	body: JSON.stringify({})
})
	.then(response => response.json())
	.then(data => {
		data.items.forEach(item => {
			var filelistContainer = document.querySelector('.adglist_content');
			var link = document.createElement('a');
			link.href = 'adgresult.html?fileTitle=' + encodeURIComponent(item.title);
			link.id = 'adglista';
			var fileTitle = document.createElement('h2');
			fileTitle.textContent = item.title;
			fileTitle.id = 'adglist';
			link.appendChild(fileTitle);
			filelistContainer.appendChild(link);
		});
	})
	.catch(error => console.error('Error:', error));