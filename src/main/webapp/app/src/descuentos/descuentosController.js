'use strict';

angular.module('app.descuentos').controller('DescuentosCtrl', DescuentosCtrl);
DescuentosCtrl.$inject = ['DescuentosService', '$mdDialog'];
function DescuentosCtrl(DescuentosService, $mdDialog) {
  var self = this;

  // Variables
  self.descuentos = null;
  self.query = { order: 'nombre', limit: 5, page: 1 };
  self.progress = null;

  // Funciones
  self.editar = editar;
  self.eliminar = eliminar;
  self.showDescuentoForm = showDescuentoForm;

  // Inicializo la tabla de descuentos
  self.progress = DescuentosService.getDescuentos()
    .then(function (result) {
      self.descuentos = result;
    });

  function editar(descuento) {
    console.log('Editar: ' + id);
  }

  function eliminar(event, descuento) {
    console.log(descuento);
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

  function showDescuentoForm(event) {
    $mdDialog.show({
      controller: 'DescuentoFormCtrl as ctrl',
      templateUrl: 'app/src/descuentos/descuentoForm.html',
      parent: angular.element(document.body),
      targetEvent: event,
      clickOutsideToClose: false,
      fullscreen: false
    });
  }
}

angular.module('app.descuentos').controller('DescuentoFormCtrl', DescuentoFormCtrl);
DescuentoFormCtrl.$inject = ['DescuentosService', '$mdDialog'];
function DescuentoFormCtrl(DescuentosService, $mdDialog) {
  var self = this;

  self.proveedores = [
    {
      "id": 1,
      "razonSocial": "Chacras de Viña",
      "horarioAtencion": "2:00 a 10:00 p.m.",
      "logo": "amazing.png"
    }
  ];
  self.categorias = [
    { id: 1, descripcion: 'Categoria 01' },
    { id: 2, descripcion: 'Categoria 02' },
    { id: 3, descripcion: 'Categoria 03' }
  ];
  self.niveles = [
    { id: 1, descripcion: 'NIVEL 1' },
    { id: 2, descripcion: 'NIVEL 2' }
  ];

  // self.descuento = {
  //   "id": null,
  //   "nombre": null,
  //   "descripcion": null,
  //   "descripcionCorta": null,
  //   "idNivel": null,
  //   "vigenciaDesde": null,
  //   "vigenciaHasta": null,
  //   "idProveedor": null,
  //   "idCategoria": null,
  //   "imagen": null,
  //   "legales": null
  // }
  self.descuento = {
    // "id": null,
    "nombre": 'Prueba',
    "descripcion": 'Descuento de prueba',
    "descripcionCorta": 'Descuento de prueba',
    "idNivel": 1,
    "vigenciaDesde": null,
    "vigenciaHasta": null,
    "idProveedor": 1,
    "idCategoria": 2,
    "imagen": null,
    "legales": 'Legales descuento de prueba',
  }

  self.guardar = function () {
    console.log(self.descuento);
    var descuento = angular.copy(self.descuento);
    descuento.imagen = self.descuento.imagen.name;

    DescuentosService.saveDescuento(descuento)
      .then(function (result) {
        console.log(result);
        $mdDialog.hide();
      });
  }

  self.cancel = function () {
    $mdDialog.cancel();
  };

}