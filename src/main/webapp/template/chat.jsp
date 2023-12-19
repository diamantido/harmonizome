<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:wrapper title="Chat">
    <div class="chat-page">
        <h1>Chat</h1>
        <p class="instruction">This is an AI chatbot powered by OpenAI's GPT-4. Input your query below, and the chatbot will process your request and retrieve the requested information.</p>
        <div name="chat" class="card">
            <div id="chat-history" class="card-body" ></div>
            <div id="chat-input" class="card-footer">
                <form id="chatInput" method="POST" action="chat" class="form-inline">
                    <input type="textarea" id="message" name="message" class="chat-input-box" placeholder="Your query">
                    <button itype="submit" id="submit" class="btn btn-primary submit-button">Send <span class="glyphicon glyphicon-send"></span></button>
                </form>
            </div>
        </div>
        <div class="example-cards-container">
            <div class="example-card" id="example-card-1">
                <p>Please tell me about the Achilles resource.</p>
            </div>
            <div class="example-card" id="example-card-2">
                <p>What can you tell me about STAT3?</p>
            </div>
            <div class="example-card" id="example-card-3">
                <p>What genes are associated with lung carcinoma?</p>
            </div>
        </div>
    </div>
</t:wrapper>

<script src="script/chat-page.js"></script>
<script>
	HARMONIZOME.setupChatBot();
</script>

