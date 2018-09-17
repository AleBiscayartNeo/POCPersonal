'use strict';

angular.module('app.notificaciones', ['ngRoute'])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/notificaciones', {
      templateUrl: 'app/src/notificaciones/notificaciones.html',
      controller: 'NotificacionesCtrl',
      controllerAs: 'ctrl'
    });
  }]);