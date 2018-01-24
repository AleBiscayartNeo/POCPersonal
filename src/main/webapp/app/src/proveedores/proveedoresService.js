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
      url: 'http://www.mocky.io/v2/5a67ced82d0000f330becfe9?mocky-delay=200ms' //200
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
    // return $q.when(proveedores);
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