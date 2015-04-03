App.View.Page = Backbone.View.extend({

    tagName: 'div',

    className: 'page',

    events: {
        'click .downloads .icon-link': 'countDownload'
    },

    initialize: function(options) {
        options.parent.$el.append(this.el);
        var data = this.model.toJSON();
        this.$el.append(App.renderTemplate('page', data));
        this.countPageView();
    },

    countPageView: function() {
        var resource = this.id;
        $.ajax({
            url: 'http://maayanlab.net/harmonizome/api/metrics/index.php',
            type: 'POST',
            data: {
                resource: resource,
                counter: 'page_views'
            },
            success: function(data) {
                debugger;
            },
            error: function(data) {
                debugger;
            }
        });
    },

    countDownload: function(evt) {
        evt.preventDefault();
        var url = $(evt.target).parent().attr('href');
    }
});
