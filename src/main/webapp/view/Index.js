App.View.Index = Backbone.View.extend({

    el: '#index',

    initialize: function(options) {
        new App.View.Nav({
            model: new App.Model.Share()
        });
        new App.View.Intro({
            parent: this
        });
        new App.View.Footer();
    }
});
