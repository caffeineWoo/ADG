const setModal = function () {
    const modalOpenButton = document.getElementById('modalOpenButton');
    const modalCloseButton = document.getElementById('modalCloseButton');
    const modal = document.getElementById('modalContainer');

    modalOpenButton.addEventListener('click', () => {
        modal.classList.remove('hidden');
    });

    modalCloseButton.addEventListener('click', () => {
        console.log(selectedFile);
        // modal.classList.add('hidden');
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

// fileInput.addEventListener("click", () => {
// 	let fileInput = document.querySelector(".default_file_input");
// 	fileInput.click();
// });

// fileInput.addEventListener("change", e => {
// 	let fileInput = document.querySelector(".default_file_input");
// 	console.log(" > " + fileInput.value)
//     selectedFile = fileInput.files[0];
// 	uploadIcon.innerHTML = 'check_circle';
// 	dragDropText.innerHTML = 'File Dropped Successfully!';
// 	document.querySelector(".label").innerHTML = `drag & drop or <span class="browse_files"> <input type="file" class="default_file_input" style=""/> <span class="browse_files_text" style="top: 0;"> browse file</span></span>`;
//     uploadButton.innerHTML = `Upload`;
// 	fileName.innerHTML = fileInput.files[0].name;
// 	fileSize.innerHTML = (fileInput.files[0].size/1024).toFixed(1) + " KB";
// 	uploadedFile.style.cssText = "display: flex;";
// 	progressBar.style.width = 0;
// 	fileFlag = 0;
// });

uploadButton.addEventListener("click", () => {
	let isFileUploaded = fileInput.value;
	if(isFileUploaded != '') {
		if (fileFlag == 0) {
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
		document.querySelector(".label").innerHTML = `drag & drop or <span class="browse_files"> <input type="file" class="default_file_input" style=""/> <span class="browse_files_text" style="top: 0px; left: 0px;"> browse file</span> </span>`;
		uploadButton.innerHTML = `Upload`;
		
		let files = e.dataTransfer.files;
		fileInput.files = files;
		console.log(files[0].name + " " + files[0].size);
		console.log(document.querySelector(".default_file_input").value);
		fileName.innerHTML = files[0].name;
		fileSize.innerHTML = (files[0].size/1024).toFixed(1) + " KB";
		uploadedFile.style.cssText = "display: flex;";
		progressBar.style.width = 0;
		fileFlag = 0;
	});
}

removeFileButton.addEventListener("click", () => {
	uploadedFile.style.cssText = "display: none;";
	selectedFile = '';
    fileName.innerHTML = null;
    fileSize.innerHTML = null;
	uploadIcon.innerHTML = 'file_upload';
	dragDropText.innerHTML = 'Drag & drop any file here';
	document.querySelector(".label").innerHTML = `or <span class="browse_files"> <input type="file" class="default_file_input onchange="conChange()"/> <span class="browse_files_text">browse file</span> <span>from device</span> </span>`;
	uploadButton.innerHTML = `Upload`;
});

function conChange(event) {
	let fileInput = document.querySelector(".default_file_input");
	console.log(" > " + fileInput.value)
    selectedFile = fileInput.files[0];
	uploadIcon.innerHTML = 'check_circle';
	dragDropText.innerHTML = 'File Dropped Successfully!';
	document.querySelector(".label").innerHTML = `drag & drop or <span class="browse_files"> <input type="file" class="default_file_input" onchange="conChange()/> <span class="browse_files_text" style="top: 0;"> browse file</span></span>`;
    uploadButton.innerHTML = `Upload`;
	fileName.innerHTML = fileInput.files[0].name;
	fileSize.innerHTML = (fileInput.files[0].size/1024).toFixed(1) + " KB";
	uploadedFile.style.cssText = "display: flex;";
	progressBar.style.width = 0;
	fileFlag = 0;

    // your logic here on what to do with files (maybe fetch the preview or something)

    // this line right below will reset the 
    // input field so if you removed it you can re-add the same file
    event.target.value = ''
}

function conClick() {
	console.log('conClick');
}