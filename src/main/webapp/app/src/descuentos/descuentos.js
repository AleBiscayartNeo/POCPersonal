'use strict';

angular.module('app.descuentos', ['ngRoute'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/descuentos', {
      templateUrl: 'app/src/descuentos/descuentos.html',
      controller: 'DescuentosCtrl'
    });
  }])

  .controller('DescuentosCtrl', [function () {
  }]);