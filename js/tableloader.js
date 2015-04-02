$(function() {

    $.getJSON('data/resource_table_test.json', drawTable);

    function drawTable(data) {
        $table = $('table').first();
        for (var i = 0; i < data.length; i++) {
            $table.append( rowTemplate(data[i]) );
        }
        $table.DataTable();
    }

    var DOWNLOAD_IMAGE = '<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>';

    var rowTemplate = _.template('' +
        '<tr>' +
        '   <td>' +
        '       <a href="<%= resource_url %>"><%= resource_name %>:</a> <%= resource_description %>' +
        '   </td>' +
        '   <td>' +
        '      <%= data_description %>' + 
        '   </td>' +
        '   <td>' +
        '      <%= association %>' + 
        '   </td>' +
        '   <td>' +
        '      <%= attribute %>' + 
        '   </td>' +
        '   <td>' +
        '       <% var pub_url = "http://www.ncbi.nlm.nih.gov/pubmed/" + publication[0].pmid %>' +
        '       <a href="<%= pub_url %>">' +
        '           <%= publication[0].author %>' +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var gene_list_url = directory + "/" + gene_list %>' +
        '       <a href="<%= gene_list_url %>">' +
        '           <%= number_of_genes %>' +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var attribute_list_url = directory + "/" + attribute_list %>' +
        '       <a href="<%= attribute_list_url %>">' +
        '           <%= number_of_attributes %>' +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var attribute_table_url = directory + "/" + attribute_table %>' +
        '       <a class="download-link" href="<%= attribute_table_url %>">' +
                    DOWNLOAD_IMAGE +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var edge_list_url = directory + "/" + edge_list %>' +
        '       <a class="download-link" href="<%= edge_list_url %>">' +
                   DOWNLOAD_IMAGE +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var gene_set_library_url = directory + "/" + gene_set_library %>' +
        '       <a class="download-link" href="<%= gene_set_library_url %>">' +
                    DOWNLOAD_IMAGE +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var attribute_set_library_url = directory + "/" + attribute_set_library %>' +
        '       <a class="download-link" href="<%= attribute_set_library_url %>">' +
                    DOWNLOAD_IMAGE +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var gene_similarity_matrix_url = directory + "/" + gene_similarity_matrix %>' +
        '       <a class="download-link" href="<%= gene_similarity_matrix_url %>">' +
                   DOWNLOAD_IMAGE +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var attribute_similarity_matrix_url = directory + "/" + attribute_similarity_matrix %>' +
        '       <a class="download-link" href="<%= attribute_similarity_matrix_url %>">' +
                   DOWNLOAD_IMAGE +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var script_url = directory + "/" + script %>' +
        '       <a class="download-link" href="<%= script_url %>">' +
                   DOWNLOAD_IMAGE +
        '       </a>' +
        '   </td>' +
        '   <td>' +
        '       <% var date = download_date.month + "/" + download_date.day + "/" + download_date.year; %>' +
        '       <%= date %>' +
        '   </td>' +
        '</tr>'
    );
});
