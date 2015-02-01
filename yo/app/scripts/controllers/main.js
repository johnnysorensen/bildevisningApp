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

    //$scope.hi = $resource('rest/sse').get();
    $scope.url = 'images/yeoman.png';

    // handles the callback from the received event
    var handleCallback = function (msg) {
      $scope.$apply(function () {
        $scope.url = msg.data;
      });
    };

    var source = new EventSource('rest/sse');
    source.addEventListener('image', handleCallback, false);

  });
