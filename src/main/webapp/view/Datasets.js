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
            url: 'api/metric',
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
    	var json = JSON.parse(data);
    	this.$table.append(App.Template['head.html']());
        _.each(json, function(cell, i) {
            if (cell.status !== null) {
            	this.$table.append(App.Template['body.html'](cell));
            }
        }, this);
        this.$table.dataTable({
            paging: false
        });  	
    }
});

