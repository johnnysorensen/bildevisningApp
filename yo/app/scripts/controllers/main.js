'use strict';

/**
 * @ngdoc function
 * @name yoJaxrsTutorialApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the yoJaxrsTutorialApp
 */
angular.module('yoJaxrsTutorialApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
