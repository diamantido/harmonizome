App.View.Nav = Backbone.View.extend({

    el: '#nav',

    initialize: function() {
    	debugger;
        this.$el.append(JST['template/source/nav.html']());
        _.each(this.model.toJSON(), function(company) {
            this.$el.find('#share').append(JST['template/source/share.html'](company));
        }, this);
    }
});
