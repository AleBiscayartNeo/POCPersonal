'use strict';

angular.module('app.sucursales', ['ngRoute'])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/proveedor/:id', {
      templateUrl: 'app/src/sucursales/sucursales.html',
      controller: 'SucursalesCtrl',
      controllerAs: 'ctrl'
    });
  }]);