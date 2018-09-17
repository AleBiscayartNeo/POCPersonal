'use strict';

angular.module('app.notificaciones')
.controller('NotificacionesCtrl', ['$rootScope', '$scope', 'NotificacionesService', '$mdDialog', 'CommonServices', '$location', 
  function ($rootScope, $scope, NotificacionesService, $mdDialog, CommonServices, $location) {
    var self = this;
    
    self.guardar = function () {
    	NotificacionesService.sendNotificacion(self.notificacion)
            .then(function (result) {
              $rootScope.showSuccess(result.entity.message);
            });
     }

     self.cancel = function () {
    	self.notificacion.titulo = '';
    	self.notificacion.descripcion = '';
      };
  }])

