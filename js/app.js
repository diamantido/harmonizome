var App = {
    Collection: {},
    Model: {},
    View: {},
    Data: {},
    EventAggregator: _.extend({}, Backbone.Events)
};

$(function() {

    var templateCache = {};
    App.renderTemplate = function(name, data) {
        if (!templateCache[name]) {
            var dir = 'template/',
                url = dir + name + '.html',
                string;
            $.ajax({
                url: url,
                dataType: 'html',
                method: 'GET',
                async: false,
                success: function(data) {
                    string = data;
                }
            });
            templateCache[name] = _.template(string);
        }
        return templateCache[name](data);
    };

    App.contentViews = {
        table: new App.View.Table({
            model: new App.Model.Table()
        }),
        pages: new App.View.Pages({
            model: new App.Model.Table()
        }),
        hide: function() {
            _.each(this, function(view) {
                if (view instanceof Backbone.View) {
                    view.$el.hide();
                    if (view.tagName === 'table') {
                        view.$el.parent().hide();
                    }
                }
            });
        },
        show: function(view) {
            this.hide();
            view.$el.show();
            if (view.tagName === 'table') {
                view.$el.parent().show();
            }
        }
    };

    App.Router = Backbone.Router.extend({
        routes: {
            '': 'table',
            'page/(:id)': 'page'
        },
        table: function() {
            App.contentViews.show(App.contentViews.table);
        },
        page: function(id) {
            App.contentViews.show(App.contentViews.pages);
            App.contentViews.pages.renderPage(id);
        }
    });

    App.router = new App.Router();
    
    Backbone.history.start();
});
