App.View.Table = Backbone.View.extend({

    tagName: 'table',

    // See DataTables documentation for styling by class:
    // https://www.datatables.net/examples/styling/
    className: 'hover',

    templateHead: '' +
        '<thead>' +
            '<tr>' +
                '<th>Resource</th>' +
                '<th>Data Description</th>' +
                '<th>Data Type</th>' +
                '<th>Attribute</th>' +
                '<th>Genes</th>' +
                '<th>Attributes</th>' +
                '<th>Downloads</th>' +
                '<th>Views</th>' +
            '</tr>' +
        '</thead>',

    templateRow: _.template('' +
        '<tr>' +
            '<td>' +
                '<a href="<%= resource_url %>"><%= resource_name %>:</a> <%= resource_description %>' +
                '<% var pub_url = "http://www.ncbi.nlm.nih.gov/pubmed/" + publication[0].pmid %>' +
                '(<a href="<%= pub_url %>"><%= publication[0].author %></a>)' +
            '</td>' +
            '<td><%= data_description %></td>' +
            '<td><%= data_type %></td>' +
            '<td><%= attribute %> </td>' +
            '<td><%= number_of_genes %></td>' +
            '<td><%= number_of_attributes %></td>' +
            '<td>' +
                '<% if (status === "ready") { %>' +
                    '<% var download_url = "#page/" + directory %>' +
                    '<a class="icon-link" href="<%= download_url %>">' +
                        '<span class="glyphicon glyphicon-file" aria-hidden="true"></span>' +
                    '</a>' +
                '<% } else { %>' +
                    '<em>Pending</em>' +
                '<% } %>' +
            '</td>' +
            '<td>' +
                '<%= metrics.page_views %>' +
            '</td>' +
        '</tr>'   
    ),

    initialize: function(options) {
        this.$el.append(this.templateHead);
        _.each(this.model.toJSON(), function(cell, i) {
            if (cell.status !== null) {
                this.$el.append(this.templateRow(cell));
            }
        }, this);
        options.parent.$el.append(this.el);
        this.$el.dataTable({
            paging: false
        });
    }
});
