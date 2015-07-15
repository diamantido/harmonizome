
/* An abstraction of the parent element's dimensions.
 */
var Frame = function(d$parentEl) {
	
	var dimensions = d$parentEl.node().getBoundingClientRect();
	
	return {
		MIN: {
			HEIGHT: dimensions.width,
			WIDTH: 800
		},
		MAX: {
			HEIGHT: dimensions.height
		}
	};
}