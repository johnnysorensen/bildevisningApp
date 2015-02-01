'use strict';

/**
 * @ngdoc overview
 * @name yoJaxrsTutorialApp
 * @description
 * # yoJaxrsTutorialApp
 *
 * Main module of the application.
 */
angular
  .module('yoJaxrsTutorialApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
