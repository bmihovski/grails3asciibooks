

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.asciibooks.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.asciibooks.UserRole'
grails.plugin.springsecurity.authority.className = 'com.asciibooks.Role'
grails.plugin.springsecurity.authority.successHandler.defaultTargetUrl = '/dashboard'
grails.plugin.springsecurity.userLookup.usernamePropertyName = 'email'
grails.plugin.springsecurity.userLookup.usernameIgnoreCase = true
grails.plugin.springsecurity.rest.login.usernamePropertyName = 'email'
grails.plugin.springsecurity.rest.token.storage.jwt.secret = 'qrD6h8K6S9503Q06Y6Rfk21TErImPYqa'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	// Stateless chain
	[pattern: '/api/**',
	 	filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter, -exceptionTranslationFilter,' +
				'-authenticationProcessingFilter, -securityContextPersistenceFilter,' +
				'-rememberMeAuthenticationFilter'],
	// Traditional chain
	[pattern: '/**',
	 	filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'],
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
]