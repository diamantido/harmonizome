HARMONIZOME.cross = (function() {

    function setupDatasetCrossingPage() {

        var selectMsg = '(Please select a dataset)';

        $('select').eq(0).change(function(evt) {
            var ds1 = $(evt.target).find(':selected').text();
            if (ds1 === selectMsg) {
                $('#dataset-2').empty().parent().addClass('hidden');
                return;
            }
            $.ajax({
                url: "api/1.0/cross/util/" + encodeURIComponent(ds1),
                method: 'GET',
                success: function(data) {
                    buildRightSelect(JSON.parse(data)['rightDatasets']);
                }
            });
        });

        $('button').click(function() {
            var ds1Text = $('select#dataset-1').find(':selected').text(),
                ds2Text = $('select#dataset-2').find(':selected').text(),
                ds1Val = encodeURIComponent(ds1Text),
                ds2Val = encodeURIComponent(ds2Text);

            if (ds1Text === selectMsg) {
                alert('Please select two datasets.');
                return;
            }
            var submitButton = document.getElementById('button');
            submitButton.innerHTML = 'Loading...<span class="loader"></span>';
            
            $.ajax({
                url: "api/1.0/cross/" + ds1Val + '/' + ds2Val,
                type: 'GET',
                success: function(data) {
                    var modifiedData = JSON.parse(data);
                    setupDataTable(modifiedData);
                    submitButton.innerHTML = 'Submit';
                }
            });
        });
    }

    function buildRightSelect(datasets) {
        var $select = $('#dataset-2').empty();
        $select.append()
        $.each(datasets, function(i, obj) {
            $select.append('<option>' + obj + '</option>');
        });
        $select.parent().removeClass('hidden');
    }

    function setupDataTable(data) {
        var $table = $('.data-table').first();
        var header = createTableHeader(data);
        var tableRows = createTableRows(data);
    
        if ($.fn.DataTable.isDataTable($table)) {
            $table.DataTable().clear();
            $table.DataTable().destroy();
            
            $table.find("thead").remove();
            $table.find("tbody").remove();
        }
        
        $table.append(header);
        $table.append(tableRows);
    
        var config = {
            bPaginate: true,
            bSort: true,
            iDisplayLength: 20,
            oLanguage: {
                sSearch: "Filter"
            },
            select: true,
            order: [[2, "asc"]],
            fnInitComplete: function() {
                $table.fadeIn();
            }
        };

        $table.dataTable(config);
        $('.tooltip-text').tooltip();
    }

    function createTableHeader(data) {
        var header = document.createElement("thead");
        var headerRow = document.createElement("tr");

        var attType1 = document.createElement("th");
        attType1.classList.add("gene-set-column");
        var attType2 = document.createElement("th");
        attType2.classList.add("gene-set-column");
        var pValue = document.createElement("th");
        var jaccardIndex = document.createElement("th");
        var overlappingGenes = document.createElement("th");

        attType1.innerText = toTitle(data.attributetype1);
        attType2.innerText = toTitle(data.attributetype2);
        pValue.innerText = "P-Value";
        jaccardIndex.innerText = "Jaccard Index";
        overlappingGenes.innerText = "Overlapping Genes";

        headerRow.appendChild(attType1);
        headerRow.appendChild(attType2);
        headerRow.appendChild(pValue);
        headerRow.appendChild(jaccardIndex);
        headerRow.appendChild(overlappingGenes);
        header.appendChild(headerRow);

        return header;
    }

    function createTableRows(data) {
        var tableRows = document.createElement("tbody");
        data.gene_set_pairs.forEach(pair => {
            const row = document.createElement('tr');

            const geneSet1 = document.createElement('td');
            const geneSet2 = document.createElement('td');
            const pValue = document.createElement('td');
            const jaccardIndex = document.createElement('td');
            const overlappingGenes = document.createElement('td');
    
            const geneSet1Link = document.createElement('a');
            geneSet1Link.href = pair.geneSet1.href.replace('/api/1.0/', '');
            geneSet1Link.innerText = pair.geneSet1.name.split('/')[0]+'_'+direction(pair.geneSet1Dir);
            geneSet1.appendChild(geneSet1Link);
            geneSet1.classList.add('gene-set-column');
            geneSet1.setAttribute('data-toggle', 'tooltip');
            geneSet1.setAttribute('title', 'Gene Set Size: '+ pair.geneSet1Size);

            const geneSet2Link = document.createElement('a');
            geneSet2Link.href = pair.geneSet2.href.replace('/api/1.0/', '');
            geneSet2Link.innerText = pair.geneSet2.name.split('/')[0]+'_'+direction(pair.geneSet2Dir);
            geneSet2.appendChild(geneSet2Link);
            geneSet2.classList.add('gene-set-column');
            geneSet2.setAttribute('data-toggle', 'tooltip');
            geneSet2.setAttribute('title', 'Gene Set Size: '+ pair.geneSet2Size);
            
            pValue.innerText = pair.pValue;
            jaccardIndex.innerText = pair.jaccardIndex;

            overlappingGenes.classList.add('overlap-column');
            const overlappingGenesLink = document.createElement('a');
            overlappingGenesLink.innerText = pair.overlappingGenes;
            overlappingGenes.appendChild(overlappingGenesLink);
            overlappingGenes.addEventListener("click", function() {showOverlappingGenes(pair.geneSet1.name.split('/')[0], pair.geneSet1Dir, pair.geneSet2.name.split('/')[0], pair.geneSet2Dir)});
    
            row.appendChild(geneSet1);
            row.appendChild(geneSet2);
            row.appendChild(pValue);
            row.appendChild(jaccardIndex);
            row.append(overlappingGenes);
    
            tableRows.appendChild(row);
        });
        return tableRows;
    }

    function showOverlappingGenes(gs1, gs1dir, gs2, gs2dir) {
        $.ajax({
            url: "api/1.0/cross/" + encodeURIComponent(gs1) + "/" +gs1dir + "/" + encodeURIComponent(gs2) + "/" + gs2dir,
            method: 'GET',
            success: function(data) {
                createModal(JSON.parse(data), gs1+'_'+direction(gs1dir), gs2+'_'+direction(gs2dir));
                console.log(JSON.parse(data));
            }
        });
    }

    function createModal(genes, geneSet1, geneSet2) {
        var $modal = $('<div class="modal fade" role="dialog">');
        var $modalDialog = $('<div class="modal-dialog modal-dialog-centered" role="document">');
        var $modalContent = $('<div class="modal-content">');

        var $modalHeader = $('<div class="modal-header">');
        var $modalTitle = $('<h4 class="modal-title">').text('Overlapping Genes Between ' +geneSet1 + ' and ' + geneSet2);
        var $closeButton = $('<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>');
        $modalHeader.append($modalTitle, $closeButton);

        var $modalBody = $('<div class="modal-body">');
        genes.sort(function (a,b) {
            return a.symbol.localeCompare(b.symbol);
        });
        var geneLinks = genes.map(function (gene) {
            return $('<a>', {
                href: gene.href.replace('/api/1.0/', ''),
                text: gene.symbol
            })[0].outerHTML;
            
        }).join(', ');
        $modalBody.html(geneLinks);

        
        var $modalFooter = $('<div class="modal-footer">');
        var $clipboardButton = $('<button class="modal-button clipboard-button">Copy Genes</button>');
        $clipboardButton.on('click', function() {
            copyGenesToClipboard(genes);
        })
        var $enrichrButton = $('<button class="modal-button enrichr-button">Send to Enrichr<img src="image/tool/enrichr.png" id="enrichr-logo"></button>');
        $enrichrButton.on('click', function() {
            sendToEnrichr(genes, geneSet1, geneSet2);
        });
        var $rummageneButton = $('<button class="modal-button rummagene-button">Send to Rummagene <img src="image/tool/rummagene.webp" id="rummagene-logo"></button>');
        $rummageneButton.on('click', function() {
            sendToRummagene(genes, geneSet1, geneSet2);
        });
        $modalFooter.append($clipboardButton, $enrichrButton, $rummageneButton);

        $modalContent.append($modalHeader, $modalBody, $modalFooter);
        $modalDialog.append($modalContent);
        $modal.append($modalDialog);

        $('body').append($modal);
        $modal.modal('show');
    }

    function toTitle(phrase) {
        return phrase
        .split(' ')
        .map(word => word.charAt(0).toUpperCase() + word.slice(1))
        .join(' ');
    }

    function direction(dir) {
        if (dir == 1) {
            return 'up';
        } else if (dir == -1) {
            return 'down';
        }
    }

    function copyGenesToClipboard(genes) {
        var genesText = genes.map(gene => gene.symbol).join(' ');
        navigator.clipboard.writeText(genesText);
        alert('Copied genes to clipboard.');
    }

    function sendToEnrichr(genes, geneSet1, geneSet2) {
        var genesList = genes.map(gene => gene.symbol).join('\n');
        var $form = $('<form>', {
            'method':'post',
            'action':'https://maayanlab.cloud/enrichr/enrich',
            'target':'_blank',
            'enctype': 'multipart/form-data'
        })
        .append($('<input>', {'type': 'hidden', 'name': 'list', 'value': genesList}))
        .append($('<input>', {'type': 'hidden', 'name': 'description', 'value': 'Overlap between ' + geneSet1 + ' and ' + geneSet2 + ' from Harmonizome'}));
        $('body').append($form)
        $form.submit();
        $form.remove();
    }

    function sendToRummagene(genes, geneSet1, geneSet2) {
        const inputArray = genes.map(gene => gene.symbol);
        const description = 'Overlap between ' + geneSet1 + ' and ' + geneSet2 + ' from Harmonizome';
        const query = {
            "operationName": "AddUserGeneSet",
            "variables": {
                "description": description,
                "genes": inputArray
            },
            "query": "mutation AddUserGeneSet($genes: [String], $description: String = \"\") {\n  addUserGeneSet(input: {genes: $genes, description: $description}) {\n    userGeneSet {\n      id\n      __typename\n    }\n    __typename\n  }\n}\n"
        }
        fetch(`https://rummagene.com/graphql`, {
            method: 'POST',
            body: JSON.stringify(query),
            headers: {Accept: 'application/json', "Content-Type": "application/json"}
        })
            .then(function (res) {
                return res.json()
            })
            .then(function ({data, ...rest}) {
                console.log(data, rest)
                const userListId = (((data || {}).addUserGeneSet || {}).userGeneSet || {}).id
                const url = userListId ? `https://rummagene.com/enrich?dataset=${userListId}`: `https://rummagene.com/`
                window.open(url, '_blank');
            });
    }

    return {setupDatasetCrossingPage: setupDatasetCrossingPage};
})();