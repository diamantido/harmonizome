function showAttributesByGroup(group) {
	console.log(group);
	var $group = $('.' + group);
	//$('.attributes-data').addClass('hidden');
	if ($group.hasClass('hidden')) {
		$group.removeClass('hidden');
	} else {
		$group.addClass('hidden');
	}
}