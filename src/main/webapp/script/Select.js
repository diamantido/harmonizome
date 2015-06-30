App.View.Select = Backbone.View.extend({

	tagName: 'section',
	
	className: 'col-md-4',
	
	hasBeenSelected: false,
	
	events: {
		'change': 'selectClicked',
		'mousedown': 'selectClicked',
		'click button.reset': 'resetOptions'
	},
	
	template: _.template('' +
		'<h4><%= title %></h4>' +
		'<button class="reset">Reset</button>' +
		'<select>' +
		'	<option></option>' +
		'</select>'
	),
	
	initialize: function(options) {
		this.id = options.id;
		var html = this.template({
			title: this._sentenceCase(this.id)
		});
		options.$parentEl.append(this.$el.append(html));
		this.$select = this.$el.find('select').first();
		App.EventAggregator.on('updateOptions', function(newOptions) {
			this.updateOptions(newOptions[this.id]);
		}, this);
	},
	
	getSelectedValue: function() {
		if (this.hasBeenSelected) {
			console.log(this.$select.val());
			return this.$select.val().replace(/ /g, '_');
		}
		return '';
	},

	selectClicked: function(evt) {
		if (this.hasBeenSelected && evt.type === "mousedown") {
			evt.preventDefault();
			return;
		}
		if (evt.type !== 'mousedown') {
			console.log('option changed');
			this.$select.addClass('locked');
			this.hasBeenSelected = true;
			App.EventAggregator.trigger('queryAPI');
		}
	},

	updateOptions: function(options) {
		if (this.hasBeenSelected) {
			return;
		}
		this.$select.empty();
		this.$select.append($('<option></option>'));
		_.each(options, function(obj) {
			var $option = $('<option></option>')
				.val(obj.name)
				.text(obj.name);
			this.$select.append($option);
		}, this);
	},
	
	resetOptions: function() {
		console.log('reset options');
		this.hasBeenSelected = false;
		this.$select.removeClass('locked');
		App.EventAggregator.trigger('queryAPI');
	},
	
	/*_optionAlreadySelected: function(options) {
		var optionNames = _.map(options, function(opt) {
			return opt.name;
		});
		return $.inArray(this.selectedValue, optionNames) != -1;
	},*/
	
	_sentenceCase: function(camelCase) {
		return camelCase.replace(/^[a-z]|[A-Z]/g, function(v, i) {
	        return i === 0 ? v.toUpperCase() : " " + v.toLowerCase();
	    });
	}
});