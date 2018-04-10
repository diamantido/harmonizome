module.exports = function(grunt) {
	grunt.initConfig({
		less: {
			dist: {
				options: {
					yuicompress: true,
					sourceMap: true
				},
				files: [{
					expand: true,
					cwd: '.',
					src: [
						'**/*.css',
						'**/*.less',
						'!dist/**',
						'!lib/**',
                        '!**/*.min.css',
						'!node_modules/**',
					],
					dest: 'dist',
					ext: '.css'
				}]
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.registerTask('build', ['less']);
};