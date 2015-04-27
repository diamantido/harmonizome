var App = {
    Collection: {},
    Model: {},
    View: {},
    Data: {},
    EventAggregator: _.extend({}, Backbone.Events)
};

$(function() {

    $.ajax({
        url: '/harmonizome/api/metrics',
        type: 'GET',
        context: this,
        success: function(data) {
            load(data);
        },
        error: function() {
            console.log("error loading metrics");
        }
    });

    function load(serverData) {
        
        var tableModel = new App.Model.Table({}, { serverData: serverData });
        App.contentViews = {
            index: new App.View.Index({
                model: tableModel
            }),
            pages: new App.View.Pages({
                model: tableModel
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
                '': 'index',
                'page/(:id)': 'page'
            },
            index: function() {
                App.contentViews.show(App.contentViews.index);
                $('#logos').show();
            },
            page: function(id) {
                App.contentViews.pages.renderPage(id);;
                App.contentViews.show(App.contentViews.pages);
            }
        });

        App.router = new App.Router();
        
        Backbone.history.start();
    }
});
