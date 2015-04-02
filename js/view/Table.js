App.View.Table = Backbone.View.extend({

    tagName: 'table',

    className: 'table display',

    initialize: function() {
        var template = App.renderTemplate('thead');
        $('#content').append(this.el);
        this.$el.append(template);
        this.drawTable(this.model.toJSON());
    },

    drawTable: function(data) {
        _.each(data, function(cell) {
            this.$el.append( App.renderTemplate('row', cell) );
        }, this);
        this.$el.DataTable();
    }
});
