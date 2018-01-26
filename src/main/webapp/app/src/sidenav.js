'use strict';

angular.module('app')
  .controller('SidenavCtrl', ['$location', function ($location) {
    var self = this;

    self.menuItems = [
      { name: 'Proveedores', url: '/proveedores', icon: 'work' },
      { name: 'Descuentos', url: '/descuentos', icon: 'loyalty' }
    ];

    self.go = function (route) {
      $location.url(route);
    }

    self.isSelected = function (route) {
      return route == $location.url();
    }

  }]);