module.exports = function(grunt) {

    var CSS = './style/css/main.css',
        LESS = './style/less/main.less',
        TEMPLATES_SRC = 'template/source/*.html'
        TEMPLATES_CMP = 'template/compiled/templates.js'
        jst_files = {};

    jst_files[TEMPLATES_CMP] = [TEMPLATES_SRC];

    grunt.initConfig({
        jshint: {
            files: ['app/**/*.js', '!app/model/Table.js'],
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
                    CSS : LESS
                }
            }
        },
        watch: {
            files: ['app/**/*.js', 'style/less/*.less', 'template/source/*.html'],
            tasks: ['build']
        },
        //https://github.com/gruntjs/grunt-contrib-jst
        jst: {
            compile: {
                files: jst_files
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-jst');
    grunt.registerTask('build', ['jshint', 'jst', 'less']);
};
