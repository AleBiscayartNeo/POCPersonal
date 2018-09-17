'use strict';

angular.module('app.notificaciones').service('NotificacionesService', NotificacionesService);
NotificacionesService.$inject = ['$q', '$http', 'APP_CONFIG'];
function NotificacionesService($q, $http, APP_CONFIG) {
  return {
    sendNotificacion: sendNotificacion
  };

  /**
   * 
   * @param {Object} Proveedor 
   */
  function sendNotificacion(notificacion) {
   return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/notification',
      data: notificacion
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }


}