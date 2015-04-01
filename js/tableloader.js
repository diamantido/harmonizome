$.getJSON( "Data/resource_table_20150331_test.json", function( data ) {
  // var items = [];
  // $.each( data, function( key, val ) {
  //   items.push( "<li id='" + key + "'>" + val + "</li>" );
  // });
 
  // $( "<ul/>", {
  //   "class": "my-new-list",
  //   html: items.join( "" )
  // }).appendTo( "body" );
	drawTable(data);
});

function drawTable(data) {
	for (var i = 0; i < data.length; i++) {
		drawRow(data[i]);
	}
}

function drawRow(rowData) {
	var row = $("<tr />")
	$("#ResourceTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
	row.append($("<td>" + '<a href="' + rowData['Resource URL'] + '">' + rowData['Resource Name'] + "</a>" + ": " + rowData['Resource Description'] + "</td>"));
	row.append($("<td>" + rowData['Data Description'] + "</td>"));
	row.append($("<td>" + rowData['Association'] + "</td>"));
	row.append($("<td>" + rowData['Attribute'] + "</td>"));
	row.append($("<td>" + '<a href="http://www.ncbi.nlm.nih.gov/pubmed/' + rowData['PMID'].split(';')[0] + '">' + rowData['Citation'].split(',')[0] + " et al.</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Gene List'] + '">' + rowData['Number of Genes'] + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Attribute List'] + '">' + rowData['Number of Attributes'] + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Attribute Table'] + '">' + '<img src="Data/downloadicon.png" alt="Attribute Table" style="width:30px;height:30px">' + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Edge List'] + '">' + '<img src="Data/downloadicon.png" alt="Edge List" style="width:30px;height:30px">' + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Gene-set Library'] + '">' + '<img src="Data/downloadicon.png" alt="Gene-set Library" style="width:30px;height:30px">' + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Attribute-set Library'] + '">' + '<img src="Data/downloadicon.png" alt="Attribute-set Library" style="width:30px;height:30px">' + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Gene Similarity Matrix'] + '">' + '<img src="Data/downloadicon.png" alt="Gene Similarity Matrix" style="width:30px;height:30px">' + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Attribute Similarity Matrix'] + '">' + '<img src="Data/downloadicon.png" alt="Attribute Similarity Matrix" style="width:30px;height:30px">' + "</a></td>"));
	row.append($("<td>" + '<a href="Data/' + rowData['Directory'] + '/' + rowData['Script'] + '">' + '<img src="Data/downloadicon.png" alt="Script" style="width:30px;height:30px">' + "</a></td>"));
	// row.append($("<td>" + '<a href="' + rowData['Data URL'].split(';')[0] + '">' + '<img src="Data/hyperlinkicon.png" alt="Data URL" style="width:20px;height:20px">' + "</a></td>"));
	// row.append($("<td>" + '<a href="' + rowData['Readme URL'].split(';')[0] + '">' + '<img src="Data/hyperlinkicon.png" alt="Readme URL" style="width:20px;height:20px">' + "</a></td>"));
	row.append($("<td>" + rowData['Download Date (YYYYMMDD)'].substring(4,6) + "/" + rowData['Download Date (YYYYMMDD)'].substring(6,8) + "/" + rowData['Download Date (YYYYMMDD)'].substring(0,4) + "</td>"));
}

