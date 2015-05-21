App.View.Datasets = Backbone.View.extend({

    el: '#datasets',

    // See DataTables documentation for styling by class:
    // https://www.datatables.net/examples/styling/
    className: 'hover',

    initialize: function() {
        this.$table = $('<table></table>');
        this.$el.append(this.$table);
    },
    
    render: function() {
    	var datasets;
        $.ajax({
            url: 'api/metrics',
            type: 'GET',
            context: this,
            success: function(data) {
            	this.renderWithData(data);
            },
            error: function() {
            }
        });
    },
    
    renderWithData: function(data) {
    	this.$table.append(JST['template/source/head.html']());
        _.each(data, function(cell, i) {
            if (cell.status !== null) {
            	this.$table.append(JST['template/source/body.html'](cell));
            }
        }, this);
        this.$table.dataTable({
            paging: false
        });  	
    }
});

