function showByGroup(group) {
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