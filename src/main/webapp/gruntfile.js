module.exports = function(grunt) {

	var CSS = './style/css/main.css',
		LESS = './style/less/main.less',
		TEMPLATES_SRC = './template/source/*.html';
	
	TEMPLATES_CMP = './template/compiled/templates.js';
	jst_files = {};

	jst_files[TEMPLATES_CMP] = [TEMPLATES_SRC];

	grunt.initConfig({
		jshint: {
			files: ['./view/**/*.js', './model/**/*.js', 'app.js', '!./model/Datasets.js'],
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
				files: {
					CSS: LESS
				}
			}
		},
		watch: {
			files: ['**/*.js', '!node_modules/**/*.js', './style/less/*.less', './template/source/*.html'],
			tasks: ['build']
		},
		// https://github.com/gruntjs/grunt-contrib-jst
		jst: {
			compile: {
				files: jst_files,
				options: {
					namespace: 'App.Template',
					processName : function(filepath) {
						console.log(filepath);
						return filepath.replace('./template/source/', '');
					}
				}
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-jst');
	grunt.registerTask('build', ['jshint', 'jst', 'less']);
};
