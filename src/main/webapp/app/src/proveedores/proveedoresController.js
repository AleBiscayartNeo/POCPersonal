'use strict';

angular.module('app.proveedores')
  .controller('ProveedoresCtrl', ['$scope', 'ProveedoresService', '$mdDialog', 'CommonServices',
    function ($scope, ProveedoresService, $mdDialog, CommonServices) {
      var self = this;
      self.proveedores = [];
      self.selected = [];
      self.query = { order: 'razonSocial', limit: 5, page: 1 };
      self.progress = null;

      self.showProveedorForm = showProveedorForm;
      self.verSucursales = verSucursales;
      self.editar = editar;
      self.eliminar = eliminar;

      self.progress = ProveedoresService.getProveedores().then(function (result) {
        self.proveedores = result;
      });

      function verSucursales(proveedor) {
        console.log('Ver Sucursales: ' + proveedor.id);
      }

      function editar(proveedor) {
        console.log('Editar: ' + id);
      }

      function eliminar(event, proveedor) {
        $mdDialog.show(
          $mdDialog.confirm()
            .title('Eliminar proveedor')
            .textContent('¿Desea eliminar el proveedor ' + proveedor.razonSocial + '?')
            .ariaLabel('Eliminar proveedor')
            .targetEvent(event)
            .ok('Eliminar')
            .cancel('Cancelar')).then(function () {
              ProveedoresService.saveProveedor().then(function (result) {
                console.log('Eliminar: ' + proveedor.id);
              });
            }, function () {
              console.log('Cancelado: ' + proveedor.id);
            });
      }

      function showProveedorForm(event) {
        $mdDialog.show({
          controller: 'ProveedorFormCtrl as ctrl',
          templateUrl: 'app/src/proveedores/proveedorForm.html',
          parent: angular.element(document.body),
          targetEvent: event,
          clickOutsideToClose: false,
          fullscreen: false
        });
      }

    }])

  .controller('ProveedorFormCtrl', ['$scope', 'ProveedoresService', '$mdDialog',
    function ($scope, ProveedoresService, $mdDialog) {
      var self = this;

      self.proveedor = {
        razonSocial: null,
        horarioAtencion: null,
        logo: null
      }

      self.guardar = function () {
        console.log(self.proveedor);
        $mdDialog.hide();
      }

      self.cancel = function () {
        $mdDialog.cancel();
      };


    }]);