<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Harmonizome</title>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.5/css/jquery.dataTables.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link href='http://fonts.googleapis.com/css?family=Archivo+Narrow:400,400italic,700,700italic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="style/css/main.css">
        <link rel="shortcut icon" href="image/favicon.png">
    </head>
    <body>

        <div id="wrapper">
            <div id="nav" class="container"></div>
            <div id="content" class="container">
                <div id="splash"></div>
                <div id="datasets"></div>
                <div id="pages"></div>
            </div>
        <div id="footer"></div>

        <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.2/underscore-min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/backbone.js/1.1.2/backbone-min.js"></script>
        <script src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
        
        <script src="app.js"></script>
        <script src="template/compiled/templates.js"></script>
        <script src="model/Resource.js"></script>
        <script src="model/Resources.js"></script>
        <script src="model/Datasets.js"></script>
        <script src="model/Share.js"></script>
        <script src="view/Index.js"></script>
        <script src="view/Datasets.js"></script>
        <script src="view/Page.js"></script>
        <script src="view/Pages.js"></script>
        <script src="view/Splash.js"></script>
        <script src="view/Nav.js"></script>
        <script src="view/Footer.js"></script>
    </body>
</html>
