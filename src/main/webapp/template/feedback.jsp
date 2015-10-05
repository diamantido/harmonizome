<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full feedback-page">
				<div class="container">
					<h1>Feedback</h1>
					<div class="col-md-6">
						<div class="row">
							<form method="POST" action="feedback">
								<label for="topic">How can we help?</label>
								<div class="form-group">
									<select name="topic" class="form-control">
										<option>I have a question about the site</option>
										<option>I would like to report a bug</option>
										<option>I have a suggestion for a feature</option>
										<option>Other</option>
									</select>
								</div>
								<div class="form-group">
									<label for="email">Your email</label>
									<input type="text" name="email" class="form-control">
								</div>
								<div class="form-group">
									<label>Please describe your issue.</label>
									<textarea name="text" class="form-control"></textarea>
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>