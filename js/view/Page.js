App.View.Page = Backbone.View.extend({

    tagName: 'div',

    className: 'page',

    events: {
        'click .downloads .icon-link': 'countDownload'
    },

    template: _.template(
        '<%' +
            'var base_url = "http://www.ncbi.nlm.nih.gov/pubmed/",' +
                'file2glpyh = {' +
                    'gene_list: "glyphicon-align-justify",' +
                    'attribute_list: "glyphicon-align-justify",' +
                    'edge_list: "glyphicon-align-justify",' +
                    'down_gene_set_library: "glyphicon-th-list",' +
                    'up_gene_set_library: "glyphicon-th-list",' +
                    'gene_set_library: "glyphicon-th-list",' +
                    'gene_similarity_matrix: "glyphicon-th",' +
                    'down_attribute_set_library: "glyphicon-th-list",' +
                    'up_attribute_set_library: "glyphicon-th-list",' +
                    'attribute_set_library: "glyphicon-th-list",' +
                    'attribute_similarity_matrix: "glyphicon-th",' +
                    'attribute_table: "glyphicon-th",' +
                    'script: "glyphicon-wrench"' +
                '};' +
        '%>' +

        '<h2><%= resource_name %></h2>' +
        '<section>' +
            '<h4>Resource</h4>' +
            '<p><%= resource_description %></p>' +
        '</section>' +
        '<section>' +
            '<h4>Data Description</h4>' +
            '<p><%= data_description %></p>' +
        '</section>' +
        '<section>' +
            '<h4>Association</h4>' +
            '<p><%= association %></p>' +
        '</section>' +
        '<section>' +
            '<h4>Attribute</h4>' +
            '<p><%= attribute %></p>' +
        '</section>' +
        '<section>' +
            '<h4>Citation(s)</h4>' +
            '<ul>' +
                '<% _.each(publication, function(pub) { %>' +
                    '<li>' +
                        '<a href="<%= base_url %><%= pub.pmid %>" target="_blank"><%= pub.citation %></a>' +
                    '</li>' +
                '<% }); %>' +
            '</ul>' +
        '</section>' +
        '<section>' +
            '<h4>Counts</h4>' +
            '<ul>' +
                '<li>Number of genes: <%= number_of_genes %></li>' +
                '<li>Number of attributes: <%= number_of_attributes %></li>' +
            '</ul>' +
        '</section>' +
        '<section class="downloads">' +
            '<h4>Downloads</h4>' +
            '<table class="table">' +
                '<thead>' +
                    '<tr>' +
                        '<th>Content</th>' +
                        '<th>File</th>' +
                        '<th>Downloads</th>' +
                    '</tr>' +
                '</thead>' +
                '<tbody>' +
                    '<% for (var file in files) { %>' +
                        '<%' +
                            'var glyphicon_image = file2glpyh[file],' +
                                'count = metrics[file + "_dl"],' +
                                'title = file.split("_"),' +
                                'file_url = "data/" + directory + "/" + files[file];' +
                            'for (var i = 0; i < title.length; i++) {' +
                                'title[i] = title[i].charAt(0).toUpperCase() + title[i].slice(1);' +
                            '}' +
                            'title = title.join(" ");' +
                        '%>' +
                        '<tr>' +
                            '<td><%= title %></td>' +
                            '<td>' +
                                '<a data-db="genes_dl" class="icon-link" href="<%= file_url %>" target="_blank">' +
                                    '<span class="glyphicon <%= glyphicon_image %>" aria-hidden="true"></span>' +
                                '</a>' +
                            '</td>' +
                            '<td>' +
                                '<%= count %>' +
                            '</td>' +
                        '</tr>' +
                    '<% } %>' +
                '</tbody>' +
            '</table>' +
        '</section>' +
        '<section>' +
            '<h4>Created</h4>' +
            '<% var date = download_date.year + "/" + download_date.month + "/" + download_date.day; %>' +
            '<p><%= date %></p>' +
        '</section>'
    ),

    initialize: function(options) {
        options.parent.$el.append(this.el);
        var html = this.template(this.model.toJSON());
        this.$el.append(html);
        this.countPageView();
    },

    countPageView: function() {
        var resource = this.id;
        $.ajax({
            url: '/harmonizome/api/metrics/index.php',
            type: 'POST',
            data: {
                resource: resource,
                counter: 'page_views'
            },
            success: function(data) {
            },
            error: function(data) {
            }
        });
    },

    countDownload: function(evt) {
        var url = $(evt.target).parent().attr('href').split('/'),
            resource = url[0],
            counter = $(evt.target).parent().attr('data-db');
        $.ajax({
            url: '/harmonizome/api/metrics/index.php',
            type: 'POST',
            data: {
                resource: resource,
                counter: counter
            },
            success: function(data) {
            },
            error: function(data) {
            }
        });
    }
});
