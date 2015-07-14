$(function() {
	
	var ENTITY_NAME = $('h1').attr('data-entity-name');
	
	/* Setup datasets table if it exists.
	 */
	function setupDataTables() {
		if (!_.isUndefined($().dataTable)) {
			$('.data-table').dataTable({
				bPaginate: false
			});
		}
	};

	/* Bind events for showing genes and attributes by group.
	 */
	function setupShowByGroupFunctionality() {
		var $showHideButtons = $('.dataset-row .glyphicon');
		$showHideButtons.click(function(evt) {
			var datasetGroup = $(evt.target).parent().attr('data-dataset-group');
			showItemsByDatasetGroup(datasetGroup);
		});
	};
	
	/* Show genes or attributes by dataset group.
	 */
	function showItemsByDatasetGroup(group) {
		var $group = $('.' + group),
			$items = $group.next(),
			$plusButton = $group.find('.glyphicon.glyphicon-plus'),
			$minusButton = $group.find('.glyphicon.glyphicon-minus');
		if ($items.hasClass('active')) {
			$items.fadeOut();
		} else {
			$items.fadeIn();
		}
		$plusButton.toggleClass('hidden');
		$minusButton.toggleClass('hidden');
		$items.toggleClass('active');
	}
	
	/* Utility function for getting the list of genes from an attribute page.
	 */
	function getGeneSetFromDatasetGroupEl($el) {
		var items = $el.find('a'),
			genes = [];
		_.each(items, function(obj) {
			var symbol = $(obj).attr('data-gene');
			genes.push(symbol);
		});
		return genes;
	};
	
	/* Utility function for building names for filenames and Enrichr descriptions.
	 */
	function getDescriptionOfSelectedData($dataset) {
		return ['harmonizome', ENTITY_NAME, $dataset[0].classList[1]].join('_');
	};
	
	/* Utility function for finding which row the user clicked on.
	 */
	function getDatasetFromEvent(evt) {
		return $(evt.target).closest('.dataset-row').first();
	}
	
	/* Bind events for sending data to Enrichr.
	 */
	function setupEnrichrLink() {
		$('.dataset-row').find('.enrichr').click(function(evt) {
			var $dataset = getDatasetFromEvent(evt);
			enrich({
				description: getDescriptionOfSelectedData($dataset),
				list: getGeneSetFromDatasetGroupEl($dataset)
			})
		});
	};
	
	/* Send data to Enrichr.
	 */
	function enrich(options) {
		var defaultOptions = {
			description: ""
		};
		
		$.extend(defaultOptions, options);

		var form = document.createElement('form');
		form.setAttribute('method', 'post');
		form.setAttribute('action', 'http://amp.pharm.mssm.edu/Enrichr/enrich');
		form.setAttribute('target', '_blank');
		form.setAttribute('enctype', 'multipart/form-data');

		var listField = document.createElement('input');
		listField.setAttribute('type', 'hidden');
		listField.setAttribute('name', 'list');
		listField.setAttribute('value', options.list);
		form.appendChild(listField);

		var descField = document.createElement('input');
		descField.setAttribute('type', 'hidden');
		descField.setAttribute('name', 'description');
		descField.setAttribute('value', options.description);
		form.appendChild(descField);
		
		document.body.appendChild(form);
		form.submit();
		document.body.removeChild(form);
	};
	
	/* Utility function for a pure JavaScript download function.
	 */
	function download(filename, text) {
	    var pom = document.createElement('a');
	    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
	    pom.setAttribute('download', filename);
	    if (document.createEvent) {
	        var event = document.createEvent('MouseEvents');
	        event.initEvent('click', true, true);
	        pom.dispatchEvent(event);
	    } else {
	        pom.click();
	    }
	};
	
	/* Setup download links on gene page.
	 */
	function setupDownloadLinks() {
		$('.glyphicon-download-alt').click(function(evt) {
			var $dataset = getDatasetFromEvent(evt),
				genes = getGeneSetFromDatasetGroupEl(
					$dataset.next()
				),
				filename = getDescriptionOfSelectedData($dataset);
			download(filename + ".txt", genes.join("\n"));
		});
	};
	
	/* Explicit call stack for clarity.
	 */
	setupDataTables();
	setupEnrichrLink();
	setupShowByGroupFunctionality();
	setupDownloadLinks();
});