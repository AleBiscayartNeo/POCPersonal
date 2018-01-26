'use strict';

angular.module('app.sucursales').service('SucursalesService', SucursalesService);
SucursalesService.$inject = ['$q', '$http', 'APP_CONFIG'];
function SucursalesService($q, $http, APP_CONFIG) {
  return {
    getSucursales: getSucursales,
    getSucursal: getSucursal,
    saveSucursal: saveSucursal,
    editSucursal: editSucursal,
    deleteSucursal: deleteSucursal
  };

  /**
   * 
   */
  function getSucursales(idProveedor) {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/sucursal/proveedor/' + idProveedor
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Integer} idSucursal 
   */
  function getSucursal(idSucursal) {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/sucursal/' + idSucursal
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Object} sucursal 
   */
  function saveSucursal(sucursal) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/sucursal/nuevo',
      data: sucursal
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Object} sucursal 
   */
  function editSucursal(sucursal) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/sucursal/editar',
      data: sucursal
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Integer} idSucursal 
   */
  function deleteSucursal(idSucursal) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/sucursal/eliminar',
      params: { idSucursal: idSucursal }
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

}