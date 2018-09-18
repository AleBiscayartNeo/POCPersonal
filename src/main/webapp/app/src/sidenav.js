'use strict';

angular.module('app')
  .controller('SidenavCtrl', ['$location', 'AuthService', function ($location, AuthService) {
    var self = this;

    self.menuItems = [
      { name: 'Oficinas', url: '/proveedores', icon: 'work', private: false },
      { name: 'Beneficios', url: '/descuentos', icon: 'loyalty', private: false },
      { name: 'Notificaciones', url: '/notificaciones', icon: 'add_alert', private: false }
    ];

    self.go = function (route) {
      $location.url(route);
    }

    self.isSelected = function (route) {
      return route == $location.url();
    }

    self.canUse = function (item) {
      return !item.private;
    }

  }]);