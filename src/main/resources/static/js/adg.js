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
let selectedFile = '';

function fileChange(e) {
	let fileInput = document.querySelector(".default_file_input");
    selectedFile = fileInput.files[0];
	uploadIcon.innerHTML = 'check_circle';
	dragDropText.innerHTML = 'File Dropped Successfully!';
	document.querySelector(".label").innerHTML = `drag & drop or <span class="browse_files"> <input type="file" class="default_file_input" onchange="fileChange(event)"/> <span class="browse_files_text" style="top: 0;"> browse file</span></span>`;
    uploadButton.innerHTML = `Upload`;
	fileName.innerHTML = fileInput.files[0].name;
	fileSize.innerHTML = (fileInput.files[0].size/1024).toFixed(1) + " KB";
	uploadedFile.style.cssText = "display: flex;";
	progressBar.style.width = 0;
	fileFlag = 0;
    e.target.value = ''
}

uploadButton.addEventListener("click", () => {
	let isFileUploaded = selectedFile;
	if(isFileUploaded != '') {
		if (fileFlag == 0) {
			console.log(selectedFile);
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

cancelAlertButton.addEventListener("click", () => {
	cannotUploadMessage.style.cssText = "display: none;";
});

if(isAdvancedUpload) {
	["drag", "dragstart", "dragend", "dragover", "dragenter", "dragleave", "drop"].forEach( evt => 
		draggableFileArea.addEventListener(evt, e => {
			e.preventDefault();
			e.stopPropagation();
		})
	);

	["dragover", "dragenter"].forEach( evt => {
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
		fileSize.innerHTML = (files[0].size/1024).toFixed(1) + " KB";
		uploadedFile.style.cssText = "display: flex;";
		progressBar.style.width = 0;
		fileFlag = 0;
	});
}

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

const generateFile = () => {
	let fileList = document.getElementById('filelist');
	let selectedValue = fileList.options[fileList.selectedIndex].value;
	let categoryInput = document.getElementById('category');
	let textarea = categoryInput.value;

	console.log(selectedValue);
	console.log(textarea);
}

const data = [
	{ category: "System Types", name: "Mainframes", content: "hardware is a very expensive experiment; no operating systems exist" },
	{ category: "System Types", name: "Servers", content: "batch processing systems" },
	{ category: "System Types", name: "Personal Computers", content: "time-sharing systems" },
	{ category: "Cost Consideration", name: "Embedded Systems", content: "hardware is very cheap, humans are expensive" },
	{ category: "System Complexity", name: "Distributed Systems", content: "are enormous, complex, and poorly understood" },
	{ category: "Historical Perspective", name: "Early Operating Systems", content: "None of these operating systems were particularly bad; each depended on tradeoffs made at that point in time" },
	{ category: "Evolution of OS", name: "Technological Advances", content: "drive OS changes" },
  ];
  
  
  function updateResultContainer() {
	const resultContainer = document.querySelector('.result_content');
	// resultContainer.innerHTML = '';
  
	// Group data by category
	const groupedData = {};
	data.forEach(item => {
	  if (!groupedData[item.category]) {
		groupedData[item.category] = [];
	  }
	  groupedData[item.category].push(item);
	});
  
	// Loop through grouped data and create HTML structure
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

  function updateKeywordContainer() {
	const resultContainer = document.querySelector('.keyword_content');
	// resultContainer.innerHTML = '';
  
	// Group data by category
	const groupedData = {};
	data.forEach(item => {
	  if (!groupedData[item.category]) {
		groupedData[item.category] = [];
	  }
	  groupedData[item.category].push(item);
	});
  
	// Loop through grouped data and create HTML structure
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
  
  updateResultContainer();
  updateKeywordContainer();