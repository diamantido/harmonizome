App.View.Page = Backbone.View.extend({

    tagName: 'div',

    initialize: function(options) {
        options.parent.$el.append(this.el);
    },

    renderPage: function(newPage) {
        this.$el.empty();
        _.each(this.model.toJSON(), function(row) {
            if (row.directory === newPage) {
                this.$el.append(App.renderTemplate('page', row));
            }
        }, this);
        debugger;
    }
});
