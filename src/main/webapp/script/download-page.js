/* Utility functions for the download page.
 * ----------------------------------------
 */

HARMONIZOME.setupDataTable = function() {

	var $table = $('.data-table').first();
	
	var config = {
        bPaginate: true,
        bSort: true,
        columnDefs: [
            {
                targets: [6],
                visible: false,
                searchable: true
            }],
        iDisplayLength: 20,
        oLanguage: {
            sSearch: "Filter"
        },
        fnInitComplete: function() {
        	$table.fadeIn();
        }
    };

	$table.dataTable(config);

  // Set the initial state of the button and table
  var showArchived = false;
  updateTable();

  // Update the table based on the archived filter
  function updateTable() {
    $table
      .DataTable()
      .column(6)
      .search(showArchived ? '' : 'false')
      .draw();
  }

  // Handle the click event for the toggle button
  $(document).on('click', '#toggleArchived', function() {
    showArchived = !showArchived;
    $(this).button("toggle");
    updateTable();
  });
};

