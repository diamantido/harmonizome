App.View.Intro = Backbone.View.extend({

    id: 'intro',

    tagName: 'div',

    template: _.template('' +
        '<div id="stats" class="left">' +
            '<ul>' +
                '<li><span>141,588</span> attributes of</li>' +
                '<li><span>32,119</span> genes from</li>' +
                '<li><span>118</span> datasets provided by</li>' +
                '<li><span>75</span> resources covering</span></li>' +
                '<li><span>14</span> data types</span></li>' +
            '</ul>' +
        '</div>' +
        '<div id="abstract" class="right">Thanks to technological advances in genomics, transcriptomics, proteomics, metabolomics, and related fields, projects that generate a large number of measurements of the properties of cells, tissues, model organisms, and patients are becoming commonplace in biomedical research.  In addition, curation projects are making great progress mining biomedical literature to extract and aggregate decades worth of research findings into online databases.  Such projects are generating a wealth of information that potentially can guide research toward novel biomedical discoveries and advances in healthcare.  To facilitate access to and learning from biomedical Big Data, we created the Harmonizome: a collection of information about genes and proteins from over 100 datasets provided by over 50 online resources.</div>' +
        '<div class="clear"></div>' +
        '<div id="logos">' +
            '<% _.each(logos, function(logo) { %>' +
                '<% if (logo.image !== "") { %>' +
                    '<a href="<%= logo.link %>" target="_blank">' +
                        '<img src="image/logo/<%= logo.image %>.PNG" class="logo"/>' +
                    '</a>' +
                '<% } %>' +
            '<% }); %>' +
        '</div>' +
        '<div>' +
            '<p>To create the Harmonizome, we distilled information from original datasets into attribute tables that define significant associations between genes and attributes, where attributes could be genes, proteins, cell lines, tissues, experimental perturbations, diseases, phenotypes, or drugs, depending on the dataset.  Gene and protein identifiers were mapped to NCBI Entrez Gene Symbols and attributes were mapped to appropriate ontologies.  We also computed gene-gene and attribute-attribute similarity networks from the attribute tables.  These attribute tables and similarity networks can be integrated to perform many types of computational analyses for knowledge discovery and hypothesis generation.</p>' +
            '<p>To navigate the Harmonizome, sort the table below by resource, data type or attribute, or type in a search term.  Each row of the table corresponds to a processed dataset.  Gene-attribute associations are provided in set library, edge list, and matrix formats.  Gene-gene and attribute-attribute similarity scores are provided in matrix format.  To access these files for a particular dataset, click the Downloads link at the right-hand side of the table.  A commented script is provided with each collection of files to document all processing steps applied to the original data.</p>' +
        '</div>'
    ),

    initialize: function(options) {
        var html = this.template({
            logos: new App.Model.Resources().toJSON()
        });
        this.$el.append(html);
        options.parent.$el.append(this.el);
    }
});
