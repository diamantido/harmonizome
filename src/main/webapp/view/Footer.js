App.View.Footer = Backbone.View.extend({

    el: '#footer',

    initialize: function() {
        this.$el.append(JST['template/source/footer.html']());
    }
});

