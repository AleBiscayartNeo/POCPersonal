'use strict';

angular.module('app.sucursales').controller('SucursalesCtrl', SucursalesCtrl);
SucursalesCtrl.$inject = ['$routeParams', 'ProveedoresService', 'SucursalesService', '$mdDialog', 'CommonServices'];
function SucursalesCtrl($routeParams, ProveedoresService, SucursalesService, $mdDialog, CommonServices) {
  var self = this;
  var selectProvincias = null;
  var idProveedor = $routeParams.id;

  // Variables
  self.proveedor = null;
  self.sucursales = null;
  self.titulo = null;
  self.query = { order: 'calle', limit: 5, page: 1 };
  self.progress = null;

  // Funciones
  self.nuevo = nuevo;
  self.editar = editar;
  self.eliminar = eliminar;

  ProveedoresService.getProveedor(idProveedor)
    .then(function (result) {
      self.proveedor = result;
    });

  // Inicializo la tabla de sucursales
  function init() {
    self.progress = SucursalesService.getSucursales(idProveedor)
      .then(function (result) {
        self.sucursales = result;
      });
  }
  init();

  /**
   * 
   * @param {$event} event 
   * @param {Object} proveedor 
   */
  function nuevo(event) {
    showSucursalForm(event);
  }

  /**
   * 
   * @param {$event} event 
   * @param {Object} sucursal 
   */
  function editar(event, sucursal) {
    showSucursalForm(event, sucursal);
  }

  /**
   * 
   * @param {$event} event 
   * @param {Object} sucursal 
   */
  function eliminar(event, sucursal) {
    $mdDialog.show(
      $mdDialog.confirm()
        .title('Eliminar sucursal')
        .textContent('¿Desea eliminar la sucursal?')
        .ariaLabel('Eliminar sucursal')
        .targetEvent(event)
        .ok('Eliminar')
        .cancel('Cancelar')).then(function () {
          SucursalesService.deleteSucursal(sucursal.id).then(function (result) {
            init();
            console.log('Eliminar: ' + sucursal.id);
          });
        }, function () {
          console.log('Cancelado: ' + sucursal.id);
        });
  }

  /**
     * 
     * @param {$event} event 
     * @param {Object} proveedor 
     */
  function showSucursalForm(event, sucursal) {
    $mdDialog.show({
      locals: {
        proveedorId: idProveedor,
        sucursal: sucursal,
        provincias: selectProvincias
      },
      controller: 'SucursalFormCtrl as ctrl',
      templateUrl: 'app/src/sucursales/sucursalForm.html',
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

  // Cargamos los selects para el modal de alta
  CommonServices.getProvincias()
    .then(function (result) {
      selectProvincias = result;
    });

}

/**
 * SucursalFormCtrl
 */
angular.module('app.sucursales').controller('SucursalFormCtrl', SucursalFormCtrl);
SucursalFormCtrl.$inject = ['SucursalesService', 'CommonServices', '$mdDialog', 'proveedorId', 'sucursal', 'provincias'];
function SucursalFormCtrl(SucursalesService, CommonServices, $mdDialog, proveedorId, sucursal, provincias) {
  var self = this;
  var isUpdate = angular.isDefined(sucursal);

  // Variables
  self.titulo = isUpdate ? "Editar Sucursal" : "Nueva Sucursal";
  self.provincias = provincias || [];
  self.localidades = [];
  self.barrios = [];
  self.cargandoLocalidades = false;
  self.cargandoBarrios = false;

  // Funciones
  self.updateLocalidades = updateLocalidades;
  self.updateBarrio = updateBarrio;
  self.guardar = guardar;
  self.cancel = cancel;

  self.sucursal = {
    id: null,
    calle: null,
    numero: null,
    informacionAdicional: null,
    idProvincia: null,
    idLocalidad: null,
    idBarrio: null,
    idProveedor: proveedorId,
    latitud: null,
    longitud: null,
    telefono: null
  }

  if (isUpdate) {
    self.sucursal.id = sucursal.id;
    self.sucursal.calle = sucursal.calle;
    self.sucursal.numero = parseInt(sucursal.numero);
    self.sucursal.informacionAdicional = sucursal.informacionAdicional;
    self.sucursal.idProvincia = sucursal.provincia.id;
    self.sucursal.idLocalidad = sucursal.localidad.id;
    self.sucursal.idBarrio = sucursal.barrio.id;
    self.sucursal.idProveedor = sucursal.proveedor.id;
    self.sucursal.latitud = sucursal.latitud;
    self.sucursal.longitud = sucursal.longitud;
    self.sucursal.telefono = sucursal.telefono;
  }

  /**
   * 
   * @param {Integer} idProvincia 
   */
  function updateLocalidades(idProvincia) {
    self.localidades = [];
    self.barrios = [];
    self.cargandoLocalidades = true;
    CommonServices.getLocalidades(idProvincia)
      .then(function (result) {
        self.localidades = result;
        self.cargandoLocalidades = false;
      });
  }

  /**
   * 
   * @param {Integer} idLocalidad 
   */
  function updateBarrio(idLocalidad) {
    self.barrios = [];
    self.cargandoBarrios = true;
    CommonServices.getBarrios(idLocalidad)
      .then(function (result) {
        self.barrios = result;
        self.cargandoBarrios = false;
      });
  }

  /**
   * Crea o actualiza una Sucursal
   */
  function guardar() {
    if (isUpdate) {
      SucursalesService.editSucursal(self.sucursal)
        .then(function (result) {
          $rootScope.showSuccess('Sucursal editada con éxito.');
          $mdDialog.hide(true);
        });
    } else {
      SucursalesService.saveSucursal(self.sucursal)
        .then(function (result) {
          $rootScope.showSuccess('Sucursal creada con éxito.');
          $mdDialog.hide(true);
        });
    }
  }

  /**
   * 
   */
  function cancel() {
    $mdDialog.cancel();
  };

}