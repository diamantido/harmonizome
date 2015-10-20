<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Contact">

    <script src="script/contact.js"></script>

    <div class="contact-page">
        <h1>Contact</h1>
        <div class="col-md-6">
            <div class="row">
                <form method="POST" action="contact">
                    <label for="topic">How can we help? <span class="hidden">(Required)</span></label>
                    <div class="form-group">
                        <select name="topic" class="form-control">
                            <option>Please select a topic</option>
                            <option>I have a question about the site</option>
                            <option>I would like to report a bug</option>
                            <option>I have a suggestion for a feature</option>
                            <option>Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="email">Your email <span class="hidden">(Required)</span></label>
                        <input type="text" name="email" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="details">Please describe your issue <span class="hidden">(Required)</span></label>
                        <textarea name="details" class="form-control"></textarea>
                    </div>
                    <button id="contact-form-btn" type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</t:wrapper>