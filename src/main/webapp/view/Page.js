App.View.Page = Backbone.View.extend({

    tagName: 'div',

    className: 'page',

    events: {
        'click .downloads .icon-link': 'countDownload'
    },

    initialize: function(options) {
        options.parent.$el.append(this.el);
        $.ajax({
        	url: 'api/metric/' + options.name,
        	method: 'GET',
        	success: this.render
        });
    },
    
    render: function(data) {
    	var json = JSON.parse(data);
        var html = JST['template/source/resource.html'](json);
        this.$el.append(html);
        //this.countPageView();
    },

    countPageView: function() {
        var resource = this.id;
        $.ajax({
            url: 'api/metrics',
            type: 'POST',
            data: {
                resource: resource,
                counter: 'page_views'
            },
            success: function(data) {
            },
            error: function(data) {
            }
        });
    },

    countDownload: function(evt) {
        var url = $(evt.target).parent().attr('href').split('/'),
            resource = url[0],
            counter = $(evt.target).parent().attr('data-db');
        $.ajax({
            url: 'api/metrics',
            type: 'POST',
            data: {
                resource: resource,
                counter: counter
            },
            success: function(data) {
            },
            error: function(data) {
            }
        });
    }
});
