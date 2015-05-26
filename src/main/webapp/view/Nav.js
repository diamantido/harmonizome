App.View.Nav = Backbone.View.extend({

    el: '#nav',

    initialize: function() {
        this.$el.append(App.Template['nav.html']());
        _.each(this.model.toJSON(), function(company) {
            this.$el.find('#share').append(App.Template['share.html'](company));
        }, this);
    }
});