App.View.Index = Backbone.View.extend({

    el: '#index',

    initialize: function(options) {
        var intro = new App.View.Intro({
            parent: this
        });
        var table = new App.View.Table({
            parent: this,
            model: this.model
        })
    }
});
