'use strict';

angular.module('app.proveedores').service('ProveedoresService', ProveedoresService);
ProveedoresService.$inject = ['$q', '$http', 'APP_CONFIG'];
function ProveedoresService($q, $http, APP_CONFIG) {
  return {
    getProveedores: getProveedores,
    saveProveedor: saveProveedor
  };

  function getProveedores() {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/proveedor/all'
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  function saveProveedor() {
    return $http({
      method: 'POST',
      url: 'http://localhost:9000'
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

}