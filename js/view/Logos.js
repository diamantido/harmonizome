App.View.Logos = Backbone.View.extend({

    tagName: 'div',

    el: '#logos',

    template: _.template('' +
        '<a href="<%= link %>" target="_blank">' +
            '<img src="image/logo/<%= image %>.PNG" class="logo"/>' +
        '</a>'
    ),

    initialize: function(options) {
        this.table = options.table;
        /*_.each(this.model.toJSON(), function(row) {
            this.$el.append(this.template(row));
        }, this);*/
        //this.$el.hide();
        $('.logo').click(this.search);
        _.each(this.model.toJSON(), function(resource) {
            if (resource.image !== '') {
                this.$el.append(this.template(resource));
            }
        }, this);
        //this.$el.fadeIn(700);
        //this.$el.justifiedGallery();
    },

    search: function(evt) {
        var query = $(evt.target).attr('id');
        this.table.fnFilter(query);
    }
});
