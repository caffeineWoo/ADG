window.onload = function() {
    fetch(`http://localhost:8080/API/board/report`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({})
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            function formatDate(dateString) {
                const date = new Date(dateString);
                return `${date.getFullYear()}.${(date.getMonth() + 1).toString().padStart(2, '0')}.${date.getDate().toString().padStart(2, '0')}`;
            }

            // Get the container element
            const boardListContainer = document.getElementsByClassName('board_list')[0];

            // Iterate through the data in reverse order and generate HTML for each entry
            for (let index = data.length - 1; index >= 0; index--) {
                // Create a new div element for each entry
                const entryElement = document.createElement('div');
                entryElement.innerHTML = `
                    <div class="num">${index + 1}</div>
                    <div class="title"><a class="title_board" href='/ADG/boardDetail?postId=${data[index].id}'>${data[index].title}</a></div>
                    <div class="writer">${data[index].memberName}</div>
                    <div class="date">${formatDate(data[index].insDate)}</div>
                `;

                // Append the new div element to the container
                boardListContainer.appendChild(entryElement);
            }
        })
        .catch(error => console.error('Error:', error));
};
