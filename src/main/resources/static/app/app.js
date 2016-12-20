'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'myApp.view1',
    'myApp.city',
    'myApp.services',
    'myApp.api',
    'myApp.good',
    'myApp.purchases',
    'myApp.account',
    'ngMaterial',
    'angularUtils.directives.dirPagination',
    'LocalStorageModule',
    'angular-loading-bar',
    'angularFileUpload'

]).config(['$routeProvider', 'paginationTemplateProvider', '$httpProvider', '$sceDelegateProvider',
    function ($routeProvider, paginationTemplateProvider, $httpProvider, $sceDelegateProvider) {
    $routeProvider.otherwise({redirectTo: '/main_page'});
    paginationTemplateProvider.setPath('bower_components/angularUtils-pagination/dirPagination.tpl.html');
    $httpProvider.interceptors.push('authHttpResponseInterceptor');

        $sceDelegateProvider.resourceUrlWhitelist([
            'self',
            'https://www.youtube.com/**',
            'http://www.youtube.com/**',
            'http://youtube.com/**',
            'http://m.youtube.com/**',
            'https://m.youtube.com/**',
        ]);
}]);
