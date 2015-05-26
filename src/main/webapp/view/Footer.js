App.View.Footer = Backbone.View.extend({

    el: '#footer',

    initialize: function() {
        this.$el.append(App.Template['footer.html']());
    }
});

