var App = App || {};
App.Collection = {};
App.Model = {};
App.View = {};
App.Data = {};
App.EventAggregator = _.extend({}, Backbone.Events);

$(function() {

    new App.View.Nav({
        model: new App.Model.Share()
    });
    new App.View.Footer();

    App.contentViews = {
        splash: new App.View.Splash(),
        pages: new App.View.Pages(),
        datasets: new App.View.Datasets(),
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
            '': 'splash',
            /* Use "data" instead of "table" so the browser doesn't shift
             * the page to #table.
             */
            //'dataset': 'datasets',
            //'dataset/(:id)': 'dataset'
        },
        splash: function() {
            App.contentViews.show(App.contentViews.splash);
        },
        datasets: function() {
        	App.contentViews.datasets.render();
            App.contentViews.show(App.contentViews.datasets);
        },
        dataset: function(name) {
            App.contentViews.pages.renderPage(name);
            App.contentViews.show(App.contentViews.pages);
        }
    });

    App.router = new App.Router();
    
    Backbone.history.start();
});
