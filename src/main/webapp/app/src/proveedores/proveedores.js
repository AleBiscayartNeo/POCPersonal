'use strict';

angular.module('app.proveedores', ['ngRoute'])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/proveedores', {
      templateUrl: 'app/src/proveedores/proveedores.html',
      controller: 'ProveedoresCtrl',
      controllerAs: 'ctrl'
    });
  }]);