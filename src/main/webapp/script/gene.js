function showAttributesByGroup(group) {
	console.log(group);
	var $attributes = $('.' + group).next();
	if ($attributes.hasClass('active')) {
		$attributes.fadeOut();
	} else {
		$attributes.fadeIn();
	}
	$attributes.toggleClass('active');
	/*if ($attributes.hasClass('data-hidden')) {
		$attributes.removeClass('hidden');
	} else {
		$attributes.addClass('hidden');
	}*/
}

/*$(function() {
	$('.attributes').dataTable();
});*/