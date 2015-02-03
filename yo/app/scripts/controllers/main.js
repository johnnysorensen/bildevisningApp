'use strict';

/**
 * @ngdoc function
 * @name bildevisningApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the bildevisningApp
 */
angular.module('bildevisningApp')
  .controller('MainCtrl', function ($scope) {

    //$scope.hi = $resource('rest/sse').get();
    var currentImage = 'images/yeoman.png';
    $scope.url = currentImage;
    $scope.bilder = [];

    // handles the callback from the received event
    var handleCallback = function (msg) {
      $scope.$apply(function () {
        $scope.url = msg.data;
        $scope.bilder.unshift(currentImage);
        currentImage = msg.data;
        if ($scope.bilder.length > 4) {
          $scope.bilder.pop();
        }
      });
    };

    var source = new EventSource('rest/sse');
    source.addEventListener('image', handleCallback, false);

  });
