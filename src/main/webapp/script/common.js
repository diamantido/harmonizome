$(function() {

	var API_URL = 'api/1.0/';
	
	/* Code executes here
	 * --------------------------------------------------------------------- */	
	var $dataTables = $('.data-table');
	if ($dataTables.length && typeof $().dataTable !== 'undefined') {
		setupDataTables($dataTables);
	}
	var $searchEl = $('.search');
	if ($searchEl.length) {
		setupSearch($searchEl);
	}	
	setupTooltips();
	setupShowByGroupFunctionality();
	
	var $downloadsTools = $('.gene-set-page .downloads-tools');
	if ($downloadsTools.length) {
		//setupDownloadLinks($downloadsTools);
		setupEnrichrLink();
	}
	
	/* --------------------------------------------------------------------- */
	
	/* Bind events for showing genes and attributes by group.
	 */
	function setupShowByGroupFunctionality() {
		var $showHideButtons = $('.dataset-row .glyphicon');
		$showHideButtons.click(function(evt) {
			var datasetGroup = $(evt.target).parent().attr('data-dataset-group');
			showItemsByDatasetGroup(datasetGroup);
		});
	}
	
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

	/* Setup datasets table if it exists.
	 */
	function setupDataTables($dataTables) {
		$dataTables.dataTable({
			bPaginate: true,
			iDisplayLength: 20,
			oLanguage: {
				sSearch: "Filter"
			},
			fnInitComplete: function() {
				$dataTables.fadeIn();
			},
			responsive: true
		});
	}

	/* Gets the attribute and dataset from the URL.
	 */
	 function buildApiUrl() {
		 try {
			 var urlParts = window.location.pathname.split('/');
			 urlParts = urlParts.splice(2);
			 return API_URL + urlParts.join('/');
		 } catch(err) {
			 return undefined;
		 }
	 }
	
	/* Utility function for building names for filenames and Enrichr descriptions.
	 */
	function getDescription() {
		try {
			var desc,
			 	urlParts = window.location.pathname.split('/');
		urlParts = urlParts.splice(3);
		desc = ['harmonizome'].concat(urlParts);
		return desc.join('_');
		} catch(err) {
			return 'harmonizome';
		}
	}

	/* Bind events for sending data to Enrichr.
	 */
	function setupEnrichrLink() {
		var url = buildApiUrl();
		if (typeof url != undefined) {
			$.get(url, function(data) {
				success(JSON.parse(data));
			});
		}
	
		function success(data) {
			var geneSymbols = [];
			data.features.forEach(function(feature, i) {
				geneSymbols.push(feature.gene.symbol);
			});

			$('.enrichr').click(function() {
				enrich({
					description: getDescription(),
					list: geneSymbols
				});
			});
		}
	}
	
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
	}
	
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
	}
	
	/* Setup download links on gene page.
	 */
	function setupDownloadLinks(genes) {
		$('.glyphicon-download-alt').click(function(evt) {
			download(getDescription() + ".txt", genes.join("\n"));
		});
	}

	/* Setups search bar.
	 */
	function setupSearch($parentEl) {
		$.ajax({
			method: 'GET',
			url: API_URL + 'gene',
			success: function(geneDictionary) {
				var genes = new Bloodhound({
					datumTokenizer: function (datum) {
						return Bloodhound.tokenizers.whitespace(datum.value);
				    },
					queryTokenizer: Bloodhound.tokenizers.whitespace,
					remote: {
						url: API_URL + 'suggest?q=%QUERY',
						wildcard: '%QUERY'
					}
				});
				var $input = $parentEl.find('input')
					.typeahead({
						hint: true,
						highlight: true,
						minLength: 1
					},
					{
						name: 'genes',
						source: genes.ttAdapter()
					});
			}
		});
	}
	
	/* Setup Twitter Bootstrap JS tooltips.
	 */
	function setupTooltips() {
		$('[data-toggle="tooltip"]').tooltip();
	};
});