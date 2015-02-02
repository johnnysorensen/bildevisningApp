'use strict';

/**
 * @ngdoc function
 * @name bildevisningApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the bildevisningApp
 */
angular.module('bildevisningApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
