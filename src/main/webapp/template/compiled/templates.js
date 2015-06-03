this["App"] = this["App"] || {};
this["App"]["Template"] = this["App"]["Template"] || {};

this["App"]["Template"]["body.html"] = function(obj) {
obj || (obj = {});
var __t, __p = '', __e = _.escape, __j = Array.prototype.join;
function print() { __p += __j.call(arguments, '') }
with (obj) {
__p += '<tr>\n    <td>\n        <a href="' +
((__t = ( resource_url )) == null ? '' : __t) +
'">' +
((__t = ( resource_name )) == null ? '' : __t) +
':</a> ' +
((__t = ( resource_description )) == null ? '' : __t) +
'\n        ';
 var pub_url = "http://www.ncbi.nlm.nih.gov/pubmed/" + publication[0].pmid ;
__p += '\n        <a href="' +
((__t = ( pub_url )) == null ? '' : __t) +
'">' +
((__t = ( publication[0].author )) == null ? '' : __t) +
'</a>\n    </td>\n    <td>' +
((__t = ( data_description )) == null ? '' : __t) +
'</td>\n    <td>' +
((__t = ( data_type )) == null ? '' : __t) +
'</td>\n    <td>' +
((__t = ( attribute )) == null ? '' : __t) +
' </td>\n    <td>' +
((__t = ( number_of_genes )) == null ? '' : __t) +
'</td>\n    <td>' +
((__t = ( number_of_attributes )) == null ? '' : __t) +
'</td>\n    <td>\n        ';
 if (status === "ready") { ;
__p += '\n            ';
 var download_url = "#page/" + directory ;
__p += '\n            <a class="icon-link" href="' +
((__t = ( download_url )) == null ? '' : __t) +
'">\n                <span class="glyphicon glyphicon-file" aria-hidden="true"></span>\n            </a>\n        ';
 } else { ;
__p += '\n            <em>Pending</em>\n        ';
 } ;
__p += '\n    </td>\n    <td>\n        ' +
((__t = ( metrics.page_views )) == null ? '' : __t) +
'\n    </td>\n</tr>   \n';

}
return __p
};

this["App"]["Template"]["resource.html"] = function(obj) {
obj || (obj = {});
var __t, __p = '', __e = _.escape, __j = Array.prototype.join;
function print() { __p += __j.call(arguments, '') }
with (obj) {


    var base_url = "http://www.ncbi.nlm.nih.gov/pubmed/",
        file2glpyh = {
            gene_list: "glyphicon-align-justify",
            attribute_list: "glyphicon-align-justify",
            edge_list: "glyphicon-align-justify",
            down_gene_set_library: "glyphicon-th-list",
            up_gene_set_library: "glyphicon-th-list",
            gene_set_library: "glyphicon-th-list",
            gene_similarity_matrix: "glyphicon-th",
            down_attribute_set_library: "glyphicon-th-list",
            up_attribute_set_library: "glyphicon-th-list",
            attribute_set_library: "glyphicon-th-list",
            attribute_similarity_matrix: "glyphicon-th",
            attribute_table: "glyphicon-th",
            script: "glyphicon-wrench"
        };
;
__p += '\n\n<h2>' +
((__t = ( resource_name )) == null ? '' : __t) +
'</h2>\n<section>\n    <h4>Resource</h4>\n    <p>' +
((__t = ( resource_description )) == null ? '' : __t) +
'</p>\n</section>\n<section>\n    <h4>Data Description</h4>\n    <p>' +
((__t = ( data_description )) == null ? '' : __t) +
'</p>\n</section>\n<section>\n    <h4>Association</h4>\n    <p>' +
((__t = ( association )) == null ? '' : __t) +
'</p>\n</section>\n<section>\n    <h4>Attribute</h4>\n    <p>' +
((__t = ( attribute )) == null ? '' : __t) +
'</p>\n</section>\n<section>\n    <h4>Citation(s)</h4>\n    <ul>\n        ';
 _.each(publication, function(pub) { ;
__p += '\n            <li>\n                <a href="' +
((__t = ( base_url )) == null ? '' : __t) +
'' +
((__t = ( pub.pmid )) == null ? '' : __t) +
'" target="_blank">' +
((__t = ( pub.citation )) == null ? '' : __t) +
'</a>\n            </li>\n        ';
 }); ;
__p += '\n    </ul>\n</section>\n<section>\n    <h4>Counts</h4>\n    <ul>\n        <li>Number of genes: ' +
((__t = ( number_of_genes )) == null ? '' : __t) +
'</li>\n        <li>Number of attributes: ' +
((__t = ( number_of_attributes )) == null ? '' : __t) +
'</li>\n    </ul>\n</section>\n<section class="downloads">\n    <h4>Downloads</h4>\n    <table class="table">\n        <thead>\n            <tr>\n                <th>Content</th>\n                <th>File</th>\n                <th>Downloads</th>\n            </tr>\n        </thead>\n        <tbody>\n            ';
 for (var file in files) {
                    var glyphicon_image = file2glpyh[file],
                        count = metrics[file + "_dl"],
                        title = file.split("_"),
                        file_url = "data/" + directory + "/" + files[file];
                    for (var i = 0; i < title.length; i++) {
                        title[i] = title[i].charAt(0).toUpperCase();
                        title[i].slice(1);
                    }
                    title = title.join(" ");
            ;
__p += '\n                <tr>\n                    <td>' +
((__t = ( title )) == null ? '' : __t) +
'</td>\n                    <td>\n                        <a data-db="genes_dl" class="icon-link" href="' +
((__t = ( file_url )) == null ? '' : __t) +
'" target="_blank">\n                            <span class="glyphicon ' +
((__t = ( glyphicon_image )) == null ? '' : __t) +
'" aria-hidden="true"></span>\n                        </a>\n                    </td>\n                    <td>\n                        ' +
((__t = ( count )) == null ? '' : __t) +
'\n                    </td>\n                </tr>\n            ';
 } ;
__p += '\n        </tbody>\n    </table>\n</section>\n<section>\n    <h4>Created</h4>\n    ';
 var date = download_date.year + "/" + download_date.month + "/" + download_date.day; ;
__p += '\n    <p>' +
((__t = ( date )) == null ? '' : __t) +
'</p>\n</section>\n';

}
return __p
};