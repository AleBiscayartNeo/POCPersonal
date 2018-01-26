'use strict';

angular.module('app').service('CommonServices', CommonServices);
CommonServices.$inject = ['$http', 'APP_CONFIG'];
function CommonServices($http, APP_CONFIG) {

  return {
    getProvincias: getProvincias,
    getLocalidades: getLocalidades,
    getBarrios: getBarrios,
    getCategorias: getCategorias
  };

  /**
   * 
   */
  function getProvincias() {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + '/provincias/all'
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Integer} idProvincia 
   */
  function getLocalidades(idProvincia) {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + "/localidad/" + idProvincia
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   * @param {Integer} idLocalidad 
   */
  function getBarrios(idLocalidad) {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + "/barrio/" + idLocalidad
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

  /**
   * 
   */
  function getCategorias() {
    return $http({
      method: 'GET',
      url: APP_CONFIG.API_URL + "/categorias/all"
    }).then(function (response) {
      return response.data;
    }, function (response) {
      return null;
    });
  }

}