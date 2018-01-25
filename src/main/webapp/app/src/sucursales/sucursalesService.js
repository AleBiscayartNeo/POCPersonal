'use strict';

angular.module('app.sucursales').service('SucursalesService', SucursalesService);
SucursalesService.$inject = ['$q', '$http', 'APP_CONFIG'];
function SucursalesService($q, $http, APP_CONFIG) {
  return {
    getSucursales: getSucursales,
    // getSucursal: getSucursal,
    // saveSucursal: saveSucursal,
    // editSucursal: editSucursal,
    // deleteSucursal: deleteSucursal
  };

  function getSucursales() {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/sucursal/all'
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

}