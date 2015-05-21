App.View.Pages = Backbone.View.extend({

    el: '#pages',

    initialize: function() {
        this.$el.append(this.el);
    },

    renderPage: function(name) {
        $('.page').remove();
        var page = new App.View.Page({
        	name: name,
        	parent: this
        });       
    }
});
