'use strict';

angular.module('app.sucursales').controller('SucursalesCtrl', SucursalesCtrl);
SucursalesCtrl.$inject = ['$routeParams', 'ProveedoresService', 'SucursalesService', '$mdDialog', 'CommonServices'];
function SucursalesCtrl($routeParams, ProveedoresService, SucursalesService, $mdDialog, CommonServices) {
  var self = this;

  // Variables
  self.proveedor = null;
  self.sucursales = null;
  self.titulo = null;
  self.query = { order: 'calle', limit: 5, page: 1 };
  self.progress = null;

  ProveedoresService.getProveedor($routeParams.id)
    .then(function (result) {
      self.proveedor = result;
    });

  self.progress = SucursalesService.getSucursales()
    .then(function (result) {
      self.sucursales = result;
    });



}