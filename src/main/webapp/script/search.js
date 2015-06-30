var App = {
    Collection: {},
    Model: {},
    View: {},
    EventAggregator: _.extend({}, Backbone.Events)
};

$(function() {
	
	var $parentEl = $('.search').find('.row').first();

	var selects = [
		new App.View.Select({
			id: 'dataset',
			$parentEl: $parentEl
		}),
		new App.View.Select({ 
			id: 'datasetGroup',
			$parentEl: $parentEl
		}),
		new App.View.Select({ 
			id: 'datasetType',
			$parentEl: $parentEl
		}),
		new App.View.Select({ 
			id: 'attributeGroup',
			$parentEl: $parentEl
		}),
		new App.View.Select({ 
			id: 'attributeType',
			$parentEl: $parentEl
		}),
		new App.View.InputField({
			id: 'gene',
			$parentEl: $parentEl
		})
	];

	function queryAPI() {
		var queryString = buildQueryString();
		console.log('query: api/q?' + queryString);
		$.ajax({
			url: 'api/q?' + queryString,
			method: 'GET',
			success: function(data) {
				App.EventAggregator.trigger('updateOptions', JSON.parse(data));
			}
		});
	}
	
	function buildQueryString() {
		var queryStringObj = {};
		_.each(selects, function(view) {
			if (view.hasBeenSelected) {
				queryStringObj[view.id] = view.getSelectedValue();
			}
		});
		return $.param(queryStringObj);
	}
	
	$('.go').click(function() {
		window.location.href = 'page?' + buildQueryString();
	});
	
	queryAPI();
	App.EventAggregator.on('queryAPI', queryAPI);
});