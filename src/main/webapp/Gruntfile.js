module.exports = function(grunt){

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		watch: {
			js: { 
				files: ['app/**/*.js'	],
				tasks: ['uglify']
			},
			less: {
				files: ['assets/less/*.less'],
				tasks: ['less:dev'],
				options: {
					livereload: false
				}
			},
			css: {
				files: ['assets/css/bootstrap.css'],
				options: {
					livereload: true
				}
			}

		},

		uglify: {
			options: {
				banner: '/*! <%= pkg.name %> <%=grunt.template.today("yyyy-mm-dd") %> */\n'
			},
			build: {
				src: [
					'app/vendor/jquery.js',
					'app/vendor/bootstrap.js',
					'app/vendor/angular.js',
					'app/vendor/angular-animate.js',
					'app/vendor/angular-resource.js',
					'app/vendor/angular-route.js',
					'app/vendor/angular-sanitize.js',
					'app/vendor/angular-strap.js',
					'app/vendor/angular-strap.tpl.js',
					'app/helpers.js',
					'app/ContactManager.js',
					'app/module.js',
					'app/test.js',
					'app/components/**/*.js',
					'app/views/**/*.js'
					],
				dest: 'assets/js/<%= pkg.name %>.js'
				}
		},

		less: {
			dev: {
				files: {
					'assets/css/bootstrap.css':	'assets/less/bootstrap.less'
				}
			},
			production: {
				options: {
					cleancss: true
				},
				files: {
					'assets/css/bootstrap.css': 'assets/less/bootstrap.less'
				}
			}
		}

	});

	grunt.loadNpmTasks('grunt-contrib-watch');

	grunt.loadNpmTasks('grunt-contrib-uglify');

	grunt.loadNpmTasks('grunt-contrib-less');

	grunt.registerTask('default', ['uglify']);
};