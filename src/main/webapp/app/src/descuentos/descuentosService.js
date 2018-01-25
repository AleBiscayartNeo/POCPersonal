'use strict';

angular.module('app.descuentos').service('DescuentosService', DescuentosService);
DescuentosService.$inject = ['$http', 'APP_CONFIG'];
function DescuentosService($http, APP_CONFIG) {
  return {
    getDescuentos: getDescuentos,
    getDescuento: getDescuento,
    saveDescuento: saveDescuento,
    editDescuento: editDescuento,
    deleteDescuento: deleteDescuento
  };

  /**
   * 
   */
  function getDescuentos() {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/descuento/all'
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Integer} idDescuento 
   */
  function getDescuento(idDescuento) {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/descuento/' + idDescuento
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Object} descuento 
   */
  function saveDescuento(descuento) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/descuento/nuevo',
      data: descuento
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Object} descuento 
   */
  function editDescuento(descuento) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/descuento/editar',
      data: descuento
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Integer} idDescuento 
   */
  function deleteDescuento(idDescuento) {
    return $http({
      method: 'POST',
      url: APP_CONFIG.API_URL + '/descuento/eliminar',
      params: { idDescuento: idDescuento }
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

}