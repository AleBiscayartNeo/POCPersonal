'use strict';

angular.module('app.proveedores').service('ProveedoresService', ProveedoresService);
ProveedoresService.$inject = ['$q', '$http', 'APP_CONFIG'];
function ProveedoresService($q, $http, APP_CONFIG) {
  return {
    getProveedores: getProveedores,
    getProveedor: getProveedor,
    saveProveedor: saveProveedor,
    editProveedor: editProveedor,
    deleteProveedor: deleteProveedor
  };

  /**
   * 
   */
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

  /**
   * 
   * @param {Integer} idProveedor 
   */
  function getProveedor(idProveedor) {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/proveedor/' + idProveedor
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Object} Proveedor 
   */
  function saveProveedor(Proveedor) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/proveedor/nuevo',
      data: Proveedor
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Object} Proveedor 
   */
  function editProveedor(Proveedor) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/proveedor/editar',
      data: Proveedor
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Integer} idProveedor 
   */
  function deleteProveedor(idProveedor) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/proveedor/eliminar',
      params: { idProveedor: idProveedor }
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

}