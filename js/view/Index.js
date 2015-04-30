App.View.Index = Backbone.View.extend({

    el: '#index',

    initialize: function(options) {
        var share = new App.View.Share({
            model: new App.Model.Share()
        });
        var intro = new App.View.Intro({
            parent: this
        });
        /*var table = new App.View.Table({
            parent: this,
            model: this.model
        });*/
    }
});
