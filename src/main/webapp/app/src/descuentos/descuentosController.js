'use strict';

angular.module('app.descuentos').controller('DescuentosCtrl', DescuentosCtrl);
DescuentosCtrl.$inject = ['DescuentosService', 'ProveedoresService', '$mdDialog'];
function DescuentosCtrl(DescuentosService, ProveedoresService, $mdDialog) {
  var self = this;
  var selectProveedores;

  // Variables
  self.descuentos = null;
  self.query = { order: 'nombre', limit: 5, page: 1 };
  self.progress = null;

  // Funciones
  self.nuevo = nuevo;
  self.editar = editar;
  self.eliminar = eliminar;

  // Inicializo la tabla de descuentos
  self.progress = DescuentosService.getDescuentos()
    .then(function (result) {
      self.descuentos = result;
    });

  /**
   * 
   * @param {$event} event 
   * @param {Object} descuento 
   */
  function nuevo(event) {
    showDescuentoForm(event);
  }

  /**
   * 
   * @param {$event} event 
   * @param {Object} descuento 
   */
  function editar(event, descuento) {
    showDescuentoForm(event, descuento);
  }

  /**
   * 
   * @param {$event} event 
   * @param {Object} descuento 
   */
  function eliminar(event, descuento) {
    $mdDialog.show(
      $mdDialog.confirm()
        .title('Eliminar descuento')
        .textContent('¿Desea eliminar el descuento ' + descuento.nombre + '?')
        .ariaLabel('Eliminar descuento')
        .targetEvent(event)
        .ok('Eliminar')
        .cancel('Cancelar')).then(function () {
          DescuentosService.deleteDescuento(descuento.id).then(function (result) {
          });
        }, function () { });
  }

  /**
   * Formulario para alta y modificación de descuentos
   * @param {$event} event 
   * @param {Object} descuento 
   */
  function showDescuentoForm(event, descuento) {
    $mdDialog.show({
      locals: {
        descuento: descuento,
        proveedores: selectProveedores
      },
      controller: 'DescuentoFormCtrl as ctrl',
      templateUrl: 'app/src/descuentos/descuentoForm.html',
      parent: angular.element(document.body),
      targetEvent: event,
      clickOutsideToClose: false,
      fullscreen: false
    });
  }


  ProveedoresService.getProveedores()
    .then(function (result) {
      selectProveedores = result;
    });
}

/**
 * DescuentoFormCtrl
 */
angular.module('app.descuentos').controller('DescuentoFormCtrl', DescuentoFormCtrl);
DescuentoFormCtrl.$inject = ['descuento', 'proveedores', 'DescuentosService', '$mdDialog'];
function DescuentoFormCtrl(descuento, proveedores, DescuentosService, $mdDialog) {
  var self = this;
  var isUpdate = angular.isDefined(descuento);

  self.titulo = isUpdate ? "Editar Descuento" : "Nuevo Descuento";
  self.proveedores = proveedores || [];

  self.categorias = [
    { id: 1, descripcion: 'Categoria 01' },
    { id: 2, descripcion: 'Categoria 02' },
    { id: 3, descripcion: 'Categoria 03' }
  ];

  self.niveles = [
    { id: 1, descripcion: 'NIVEL 1' },
    { id: 2, descripcion: 'NIVEL 2' }
  ];

  self.descuento = {
    "id": null,
    "nombre": null,
    "descripcion": null,
    "descripcionCorta": null,
    "idNivel": null,
    "vigenciaDesde": null,
    "vigenciaHasta": null,
    "idProveedor": null,
    "idCategoria": null,
    "imagen": null,
    "legales": null
  }

  if (isUpdate) {
    self.descuento.id = descuento.id;
    self.descuento.nombre = descuento.nombre;
    self.descuento.descripcion = descuento.descripcion;
    self.descuento.descripcionCorta = descuento.descripcionCorta;
    self.descuento.idNivel = descuento.nivel.id;
    self.descuento.vigenciaDesde = new Date(descuento.vigenciaDesde);
    self.descuento.vigenciaHasta = new Date(descuento.vigenciaHasta);
    self.descuento.idProveedor = descuento.proveedor.id;
    self.descuento.idCategoria = descuento.categoria.id;
    self.descuento.imagen = descuento.imagen;
    self.descuento.legales = descuento.legales;
  }

  self.guardar = function () {
    var descuento = angular.copy(self.descuento);

    if (isUpdate) {
      DescuentosService.editDescuento(descuento)
        .then(function (result) {
          console.log(result);
          $mdDialog.hide();
        });
    } else {
      descuento.imagen = self.descuento.imagen.name;
      DescuentosService.saveDescuento(descuento)
        .then(function (result) {
          console.log(result);
          $mdDialog.hide();
        });
    }
  }

  self.cancel = function () {
    $mdDialog.cancel();
  };

}