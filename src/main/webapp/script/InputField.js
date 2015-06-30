App.View.InputField = Backbone.View.extend({

	tagName: 'section',
	
	className: 'col-md-4',
	
	hasBeenSelected: false,
	
	storedValue: '',
	
	autocompleteOptions: [],
	
	events: {
		'change input': 'inputChanged',
		'focusout': 'inputChanged',
		'click button.reset': 'resetInput'
	},
	
	template: _.template('' +
		'<h4><%= title %></h4>' +
		'<button class="reset">Reset</button>' +
		'<input type="text">' +
		'</input>'
	),
	
	initialize: function(options) {
		this.id = options.id;
		var html = this.template({
			title: this._sentenceCase(this.id)
		});
		options.$parentEl.append(this.$el.append(html));
		this.$input = this.$el.find('input').first();
		App.EventAggregator.on('updateOptions', function(newOptions) {
			this.updateAutocompleteOptions(newOptions[this.id]);
		}, this);
	},
	
	updateAutocompleteOptions: function(newOptions) {
		this.$input.autocomplete({
			source: newOptions,
			/*function(request, response) {
				response(newOptions.slice(0,10));
			},*/
			minLength: 2
		});
	},
	
	getSelectedValue: function() {
		console.log('requesting selected value');
		console.log(this.storedValue);
		if (this.hasBeenSelected) {
			console.log(this.storedValue);
			return this.storedValue;
		}
		return '';
	},

	inputChanged: function(evt) {
		if (this.storedValue === '') {
			this.resetInput();
		}
		if (this.storedValue === this.$input.val()) {
			return;
		}
		this.storedValue = this.$input.val();
		console.log('option changed');
		this.$input.addClass('locked');
		this.hasBeenSelected = true;
		App.EventAggregator.trigger('queryAPI');
	},
	
	resetInput: function() {
		console.log('reset options');
		this.hasBeenSelected = false;
		this.$input.removeClass('locked');
		App.EventAggregator.trigger('queryAPI');
	},
	
	_sentenceCase: function(camelCase) {
		return camelCase.replace(/^[a-z]|[A-Z]/g, function(v, i) {
	        return i === 0 ? v.toUpperCase() : " " + v.toLowerCase();
	    });
	}
});