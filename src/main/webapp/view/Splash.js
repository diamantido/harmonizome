App.View.Splash = Backbone.View.extend({

    el: '#splash',

    initialize: function() {
        var html = App.Template['splash.html']({
            logos: new App.Model.Resources().toJSON()
        });
        this.$el.append(html);
        this.$el.append(this.el);
    }
});
