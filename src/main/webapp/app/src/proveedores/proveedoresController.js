'use strict';

angular.module('app.proveedores')
  .controller('ProveedoresCtrl', ['$scope', 'ProveedoresService', '$mdDialog', 'CommonServices', '$location',
    function ($scope, ProveedoresService, $mdDialog, CommonServices, $location) {
      var self = this;

      // Variables
      self.proveedores = [];
      self.selected = [];
      self.query = { order: 'razonSocial', limit: 5, page: 1 };
      self.progress = null;

      // Funciones
      self.ver = ver;
      self.nuevo = nuevo;
      self.editar = editar;
      self.eliminar = eliminar;
      
      // Inicializo la tabla de sucursales
      function init() {
        self.progress = ProveedoresService.getProveedores().then(function (result) {
            self.proveedores = result;
        });
      }
      
      init();

      /**
       * 
       * @param {Object} proveedor 
       */
      function ver(proveedor) {
        var route = '/proveedor/' + proveedor.id;
        $location.url(route);
      }

      /**
       * 
       * @param {$event} event 
       * @param {Object} proveedor 
       */
      function nuevo(event) {
        showProveedorForm(event);
      }

      /**
       * 
       * @param {$event} event 
       * @param {Object} proveedor 
       */
      function editar(event, proveedor) {
        showProveedorForm(event, proveedor);
      }

      /**
       * 
       * @param {$event} event 
       * @param {Object} proveedor 
       */
      function eliminar(event, proveedor) {
        $mdDialog.show(
          $mdDialog.confirm()
            .title('Eliminar proveedor')
            .textContent('¿Desea eliminar el proveedor ' + proveedor.razonSocial + '?')
            .ariaLabel('Eliminar proveedor')
            .targetEvent(event)
            .ok('Eliminar')
            .cancel('Cancelar')).then(function () {
              ProveedoresService.deleteProveedor(proveedor.id).then(function (result) {
            	init();
                console.log('Eliminar: ' + proveedor.id);
              });
            }, function () {
              console.log('Cancelado: ' + proveedor.id);
            });
      }

      /**
       * 
       * @param {$event} event 
       * @param {Object} proveedor 
       */
      function showProveedorForm(event, proveedor) {
        $mdDialog.show({
          locals: { proveedor: proveedor },
          controller: 'ProveedorFormCtrl as ctrl',
          templateUrl: 'app/src/proveedores/proveedorForm.html',
          parent: angular.element(document.body),
          targetEvent: event,
          clickOutsideToClose: false,
          fullscreen: false
        })
        .then(function (update) {
            if (update) {
                init();
              };
            }, function () { });
      }

    }])

  /**
   * ProveedorFormCtrl
   */
  .controller('ProveedorFormCtrl', ['$rootScope', 'proveedor', 'ProveedoresService', '$mdDialog',
    function ($rootScope, proveedor, ProveedoresService, $mdDialog) {
      var self = this;
      var isUpdate = angular.isDefined(proveedor);

      self.titulo = isUpdate ? "Editar Proveedor" : "Nuevo Proveedor";

      self.proveedor = {
        razonSocial: null,
        horarioAtencion: null,
        logo: null
      }

      if (isUpdate) {
        self.proveedor = proveedor;
      }

      self.guardar = function () {
        if (isUpdate) {
          ProveedoresService.editProveedor(self.proveedor)
            .then(function (result) {
              $rootScope.showSuccess('Proveedor editado con éxito.');
              $mdDialog.hide();
            });
        } else {
          ProveedoresService.saveProveedor(self.proveedor)
            .then(function (result) {
              $rootScope.showSuccess('Proveedor creado con éxito.');
              $mdDialog.hide();
            });
        }
      }

      self.cancel = function () {
        $mdDialog.cancel();
      };


    }]);