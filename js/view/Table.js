App.View.Table = Backbone.View.extend({

    tagName: 'table',

    className: 'table display',

    initialize: function(options) {
        this.$parent = $('#content');
        this.$parent.append(this.el);
        var template = App.renderTemplate('thead');
        this.$el.append(template);
        _.each(this.model.toJSON(), function(cell, i) {
            if (cell.status === 'ready') {
                this.$el.append( App.renderTemplate('row', cell) );
            }
        }, this);
        var table = this.$el.dataTable({
            paging: false
        });

        var logos = new App.View.Logos({
            $parent: this.$parent,
            table: table,
            model: new App.Model.Resources()
        });
    }
});
