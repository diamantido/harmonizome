App.View.Pages = Backbone.View.extend({

    tagName: 'div',

    className: 'content',

    initialize: function() {
        $('#content').append(this.el);
    },

    renderPage: function(page) {
        this.$el.empty();
        _.each(this.model.toJSON(), function(row) {
            if (row.directory === page) {
                this.$el.append(App.renderTemplate('page', row));
            }
        }, this);
    }
});
