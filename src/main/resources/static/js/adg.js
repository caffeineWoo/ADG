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
	let selectedValue = fileList.options[fileList.selectedIndex].value;
	// 입력받은 파일 이름
	let nameInput = document.getElementById('setfilename').value;
	// 입력 받은 카테고리
	let categoryInput = document.getElementById('category');
	let textarea = categoryInput.value;

	// 파일 생성 함수 구현 필요

	// 현재는 입력한 정보 콘솔창에 출력
	console.log(selectedValue);
	console.log(nameInput);
	console.log(textarea);
}


// 기존에 생성한 자동문서 출력하는 부분

// 선택된 파일을 저장할 변수와 파일 목록이 data1, 2, 3이 있을 때
let data = "";
let data1 = [
	{ category: "System Types", name: "Mainframes", content: "hardware is a very expensive experiment; no operating systems exist" },
	{ category: "System Types", name: "Servers", content: "batch processing systems" },
	{ category: "System Types", name: "Personal Computers", content: "time-sharing systems" },
	{ category: "Cost Consideration", name: "Embedded Systems", content: "hardware is very cheap, humans are expensive" },
	{ category: "System Complexity", name: "Distributed Systems", content: "are enormous, complex, and poorly understood" },
	{ category: "Historical Perspective", name: "Early Operating Systems", content: "None of these operating systems were particularly bad; each depended on tradeoffs made at that point in time" },
	{ category: "Evolution of OS", name: "Technological Advances", content: "drive OS changes" },
];
let data2 = [
	{ category: "System Types", name: "Mainframes", content: "hardware is a very expensive experiment; no operating systems exist" },
	{ category: "System Types", name: "Servers", content: "batch processing systems" },
	{ category: "Historical Perspective", name: "Early Operating Systems", content: "None of these operating systems were particularly bad; each depended on tradeoffs made at that point in time" },
	{ category: "Evolution of OS", name: "Technological Advances", content: "drive OS changes" },
	{ category: "System Types", name: "Personal Computers", content: "time-sharing systems" },
	{ category: "Cost Consideration", name: "Embedded Systems", content: "hardware is very cheap, humans are expensive" },
	{ category: "System Complexity", name: "Distributed Systems", content: "are enormous, complex, and poorly understood" },
];
let data3 = [
	{ category: "Cost Consideration", name: "Embedded Systems", content: "hardware is very cheap, humans are expensive" },
	{ category: "System Complexity", name: "Distributed Systems", content: "are enormous, complex, and poorly understood" },
	{ category: "Historical Perspective", name: "Early Operating Systems", content: "None of these operating systems were particularly bad; each depended on tradeoffs made at that point in time" },
	{ category: "Evolution of OS", name: "Technological Advances", content: "drive OS changes" },
	{ category: "System Types", name: "Mainframes", content: "hardware is a very expensive experiment; no operating systems exist" },
	{ category: "System Types", name: "Servers", content: "batch processing systems" },
	{ category: "System Types", name: "Personal Computers", content: "time-sharing systems" },
];

// 결과창 화면 업데이트 함수
function updateResultContainer() {
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
function updateKeywordContainer() {
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

// 좌측에 선택한 파일에 따라 그에 맞는 데이터 변수에 넣기
const filelist = document.getElementById("filelist");
filelist.addEventListener("change", function () {
	var showselectedFile = filelist.value;
	if (showselectedFile == "Textfile1") {
		data = data1;
	}
	else if (showselectedFile == "Textfile2") {
		data = data2;
	}
	else {
		data = data3;
	}
	updateResultContainer();
	updateKeywordContainer();
});

// 업로드한 파일 불러오기
fetch('http://localhost:8080/document/report?DocumentType=CATE', {
	method: 'POST',
	headers: {
		'Content-Type': 'application/json'
	},
	body: JSON.stringify({
	})
})
	.then(response => response.json())
	// 콘솔창에 출력되는지 확인되면
	.then(data => console.log(data))
	// 파일 담아서 드롭박스에 넣기
	//   .then(data =>
	//    data.forEach(item => {
	//    var optionElement = document.getElementById('filelist');
	//    optionElement.value = item.fileSourcekey;
	//    optionElement.text = item.fileTitle;
	//    selectElement.appendChild(optionElement);
	//   }))
	.catch(error => console.error('Error:', error));

data = data1;
updateResultContainer();
updateKeywordContainer();