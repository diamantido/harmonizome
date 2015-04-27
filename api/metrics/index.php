<?php


require_once 'login.php';
$db_server = mysql_connect($db_hostname, $db_username, $db_password) || die(mysql_error());
if (!$db_server) {
    die("Unable to connect to MySQL: " . mysql_error());
}
mysql_select_db($db_database) or die("Unable to select database: " . mysql_error());
header("Content-Type: application/json");


function doGet() {
    $result = mysql_query("SELECT * FROM `Metrics`");
    if (!$result) {
        die("Empty results.");
    }

    $response_data = array();
    while ($row = mysql_fetch_array($result)) {
        $row_data = array(
            "page_views" => $row[2],

            "gene_list_dl" => $row[3],
            "attribute_list_dl" => $row[4],
            "edge_list_dl" => $row[5],

            "down_gene_set_library_dl" => $row[6],
            "up_gene_set_library_dl" => $row[7],
            "gene_set_library_dl" => $row[8],
            "gene_similarity_matrix_dl" => $row[12],

            "down_attribute_set_library_dl" => $row[9],
            "up_attribute_set_library_dl" => $row[10],
            "attribute_set_library_dl" => $row[11],
            "attribute_similarity_matrix_dl" => $row[13],
            "attribute_table_dl" => $row[14],

            "script_dl" => $row[15]
        );
        $response_data[$row[1]] = $row_data;
    }
    $json = json_encode($response_data);
    echo $json;
}


function doPost() {
    $resource = $_POST["resource"];
    $counter = $_POST["counter"];
    $query = "UPDATE `Metrics` SET " . $counter . " = " . $counter . " + 1 WHERE resource = '" . $resource . "'";
    mysql_query($query) or die(mysql_error());
    echo json_encode("Success!");
}


if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    doGet();
} else if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    doPost();
}


?>
