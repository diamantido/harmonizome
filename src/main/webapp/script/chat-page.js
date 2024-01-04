/* Utility functions for the chat page.
 * ----------------------------------------
 */

HARMONIZOME.setupChatBot = function(initialHistory) {

    // Create initial chat history
    var history = initialHistory;

    var chatHistory = document.getElementById("chat-history");
    const exampleCard1 = document.getElementById('example-card-1');
    const exampleCard2 = document.getElementById('example-card-2');
    const exampleCard3 = document.getElementById('example-card-3');
    var awaitingResponse = false;
    
    var form = document.getElementById("chatInput");
    showHistory(history);
    form.addEventListener("submit", submitForm);    

    exampleCard1.addEventListener('click', function() {
        if (awaitingResponse) {
            return;
        }
        insertQuery('Please tell me about the Achilles resource.');
      });
      
      exampleCard2.addEventListener('click', function() {
        if (awaitingResponse) {
            return;
        }
        insertQuery('What can you tell me about STAT3?');
    });
    
    exampleCard3.addEventListener('click', function() {
        if (awaitingResponse) {
            return;
        }
        insertQuery('What genes are associated with lung carcinoma?');
    });

    // Event listener for form submission
    async function submitForm(event) {
        event.preventDefault(); // Prevent the form from submitting
        const messageInput = document.getElementById("message");
        const submitButton = document.getElementById("submit");
        const query = messageInput.value.trim();

        if (query !== '') {
            awaitingResponse = true;
            messageInput.readOnly = true;
            submitButton.innerHTML = 'Loading...<span class="loader"></span>';
            submitButton.disabled = true;
            exampleCard1.classList.add('locked');
            exampleCard2.classList.add('locked');
            exampleCard3.classList.add('locked');
            history.push({
                "role": "user",
                "content": query
            });

            var fetchingMessage = document.createElement("div");
            fetchingMessage.classList.add("assistant-message");
            fetchingMessage.innerText = "Please wait one moment while I fetch that information from the Harmonizome database...";
            chatHistory.appendChild(fetchingMessage);
            chatHistory.scrollTop = chatHistory.scrollHeight;

        
            await $.post(
                "chat",
                {"history":JSON.stringify(history)},
                function (data, status) {
                    history = JSON.parse(data);
                    showHistory(history);
            });

            // Reset input
            awaitingResponse = false;
            messageInput.readOnly = false;
            submitButton.innerHTML = 'Send <span class="glyphicon glyphicon-send"></span>';
            submitButton.disabled = false;
            messageInput.value = "";
            exampleCard1.classList.remove('locked');
            exampleCard2.classList.remove('locked');
            exampleCard3.classList.remove('locked');
        }
    }

    // Show chat history
    function showHistory(history) {
        var i = 0;
        chatHistory.innerHTML="";
        while (i < history.length) {
            var message = history[i];
            var role = message["role"];
            var content = message["content"];
            if (role==="user"){
                var userMessage = document.createElement("div");
                userMessage.classList.add("user-message");
                userMessage.innerHTML = message["content"];
                chatHistory.appendChild(userMessage);
            } else if (role==="assistant" && content!==null) {
                var assistantMessage = document.createElement("div");
                assistantMessage.classList.add("assistant-message");
                assistantMessage.innerText = content;
                chatHistory.appendChild(assistantMessage);
            } else if (role==="function"&& content!==null) {
                var functionMessage = document.createElement("div");
                functionMessage.classList.add("function-message");
                console.log(content);
                if (content.includes("Table:")) {
                    var data = content.split("Table:")[1]
                    if (message["name"]==="getTopGenes") {
                        functionMessage.appendChild(makeTable(data, "gene"));
                    } else if (message["name"]==="getTopGeneSets") {
                        functionMessage.appendChild(makeTable(data, "geneSet"));
                    }else if (message["name"]==="getBottomGenes") {
                        functionMessage.appendChild(makeTable(data, "gene"));
                    }else if (message["name"]==="getBottomGeneSets") {
                        functionMessage.appendChild(makeTable(data, "geneSet"));
                    }
                    chatHistory.appendChild(functionMessage);
                } else if (content.includes("Options:")) {
                    var options = content.split("Options:")[1];
                    functionMessage.appendChild(makeOptions(options));
                    chatHistory.appendChild(functionMessage);
                //} else {
                    //functionMessage.innerText = content;
                }
                
            }
            i++;
        }
        chatHistory.scrollTop = chatHistory.scrollHeight;
    }
    
    // Function to insert the example query into the input box and submit the form
    function insertQuery(query) {
        const messageInput = document.getElementById('message');
        const submitButton = document.getElementById('submit');
        messageInput.value = query;
        submitButton.click();
    }

    // Utility function to display fetched options
    function makeOptions(options) {
        const buttonsContainer = document.createElement('div');
        buttonsContainer.classList.add('buttons-container');

        options = JSON.parse(options);
        
        options = options.map((option) => option["name"].split('/')[1]);
        options.sort();

        options.forEach((buttonText) => {
            const button = document.createElement('button');
            button.textContent = buttonText;
            button.disabled = false;
            button.classList.add("option-button");

            var isSubmitting = false;

            button.addEventListener('click', async (event) => {
                if (awaitingResponse) {
                    return;
                }
                insertQuery(button.innerHTML);
            });

            buttonsContainer.appendChild(button);
            buttonsContainer.appendChild(document.createElement('br'));
        });
        return buttonsContainer;
    }

    // Utility function to display fetched data
    function makeTable(data, dataType) {
        const tableContainer = document.createElement('div');
        tableContainer.classList.add('table-container');

        console.log(data);
        data = JSON.parse(data);
        console.log(data);

        const table = document.createElement('table');

        if (dataType==="gene") {

            data = data.map((result) => {
                const resultName = result["gene"]["symbol"];
                const resultURL = result["gene"]["href"].replace('/api/1.0', '/Harmonizome').replace(')', '');
                const standardizedValue = result["standardizedValue"];
                return [`<a href="${resultURL}" target="_blank">${resultName}</a>`, standardizedValue];
            });
            
            $(table).DataTable({
                data: data,
                columns: [
                    {title: "Gene",
                    render: function (data, type, row) {
                        if (type === 'display') {return data;}
                        return row[0];
                    }},
                    {title: 'Standardized Value'}
                ],
                //order: [[1, 'desc']],
                ordering: false
            });
        } else if (dataType==="geneSet") {
            data = data.map((result) => {
                const resultName = result["geneSet"]["name"].replace("/","\t");
                const resultURL = result["geneSet"]["href"].replace('/api/1.0', '/Harmonizome').replace(')', '');
                const standardizedValue = result["standardizedValue"];
                return [`<a href="${resultURL}" target="_blank">${resultName}</a>`, standardizedValue];
            });
            
            $(table).DataTable({
                data: data,
                columns: [
                    {title: "Gene Set",
                    render: function (data, type, row) {
                        if (type === 'display') {return data;}
                        return row[0];
                    }},
                    {title: 'Standardized Value'}
                ],
                //order: [[1, 'desc']],
                ordering: false
            });
        }

        table.classList.add("table");
        tableContainer.appendChild(table);
        return tableContainer;
    }
}