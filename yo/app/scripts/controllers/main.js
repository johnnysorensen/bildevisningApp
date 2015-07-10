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

    $scope.selectBag = function () {

    };

    //$scope.hi = $resource('rest/sse').get();
    var currentImage = 'Desert.jpg';
    $scope.url = 'currentImage';
    $scope.bilder = [];
    $scope.film = [];
    var nyttBilde, overflow;
    var i = 0;

    // handles the callback from the received event
    var handleCallback = function (msg) {
      $scope.$apply(function () {
        nyttBilde = {url: msg.data, idx: ++i};
        $scope.bilder.unshift(nyttBilde);
        if ($scope.bilder.length > 2) {
          overflow = $scope.bilder.pop();
        }

        if (overflow) {
          $scope.film.unshift(overflow);
          if ($scope.film.length > 7) {
            $scope.film.pop();
          }
        }
        console.log($scope.film.length);
      });
    };

    var source = new EventSource('rest/sse');
    source.addEventListener('image', handleCallback, false);

  });
