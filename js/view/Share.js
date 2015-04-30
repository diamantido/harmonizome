App.View.Share = Backbone.View.extend({

    el: '#share',

    template: _.template('' +
        '<a class="company <%= name %>" href="<%= link %>" target="_blank">' +
            '<img src="<%= image %>">' +
        '</a>'
    ),

    initialize: function() {
        _.each(this.model.toJSON(), function(socialObj) {
            this.$el.append(this.template(socialObj));
        }, this);
    }
});
