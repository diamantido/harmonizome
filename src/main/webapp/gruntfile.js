module.exports = function(grunt) {

	grunt.initConfig({
		jshint: {
			files: ['view/**/*.js', 'model/**/*.js', 'app.js', '!model/Datasets.js'],
			options: {
				globals: {
					jQuery: true
				},
				debug: true
			}
		},
		less: {
			"default": {
				options: {
					yuicompress: true
				},
				files: [
				    {
				    	expand: true,
				    	cwd: './style/less',
						src: ['*.less'],
						dest: './style/css',
						ext: '.css'
				    }
				]
			}
		},
		watch: {
			files: ['./**/*.js', './!node_modules/**/*.js', './style/less/*.less'],
			tasks: ['build']
		}
	});

	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.registerTask('build', ['jshint', 'less']);
};
