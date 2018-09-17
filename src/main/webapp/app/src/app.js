angular
  .module('app', [
    'ngRoute',
    'ngMaterial',
    'ngMessages',
    'md.data.table',
    'ngFileUpload',
    'app.descuentos',
    'app.proveedores',
    'app.sucursales',
    'app.notificaciones'
  ])
  .constant('APP_CONFIG', {
    'API_URL': window.location.origin + '/app-beneficios/bo'
  })
  .config(function ($locationProvider, $routeProvider, $httpProvider, $mdThemingProvider, $mdIconProvider) {
    $httpProvider.interceptors.push('RequestsErrorHandler');

    /**
     * Routes
     */
    $locationProvider.hashPrefix('!');
    $routeProvider.when('/inicio', {
      templateUrl: 'app/src/inicio.html',
      controller: 'LoginController',
      controllerAs: 'ctrl'
    }).otherwise({ redirectTo: '/descuentos' });

    /**
     * Theme Config
     */
    $mdIconProvider
      .defaultIconSet("./assets/svg/avatars.svg", 128)
      .icon("menu", "./assets/svg/menu.svg", 24)
      .icon("share", "./assets/svg/share.svg", 24)
      .icon("google_plus", "./assets/svg/google_plus.svg", 512)
      .icon("hangouts", "./assets/svg/hangouts.svg", 512)
      .icon("twitter", "./assets/svg/twitter.svg", 512)
      .icon("phone", "./assets/svg/phone.svg", 512);

    $mdThemingProvider.theme('default')
      .primaryPalette('cyan')
      .accentPalette('blue-grey')
      .warnPalette('pink');
  })
  .run(function ($rootScope, $location, $mdSidenav, $mdToast, AuthService) {

    $rootScope.$on("$locationChangeStart", function (event, next, current) {
      $rootScope.isLoggedIn = AuthService.isAuth();
      if ($rootScope.isLoggedIn) {
        if ($location.url() == '/i') {
          $location.url('/');
        }
      } else {
        $location.url('/inicio');
      }
    });

    $rootScope.logout = AuthService.logout;

    $rootScope.toggleList = function () {
      $mdSidenav('left').toggle();
    }

    $rootScope.showSuccess = function (statusText) {
      $mdToast.show({
        hideDelay: 3000,
        position: 'top right',
        toastClass: 'toast-success',
        parent: angular.element(document.body),
        template:
          '<md-toast>' +
          ' <span class="md-toast-text" flex>' +
          statusText +
          ' </span>' +
          '</md-toast>'
      });
    }

    $rootScope.showError = function (statusText) {
      $mdToast.show({
        hideDelay: 3000,
        position: 'top right',
        toastClass: 'toast-error',
        parent: angular.element(document.body),
        template:
          '<md-toast>' +
          ' <span class="md-toast-text" flex>' +
          '   El servicio no se encuentra disponible, intente nuevamente más tarde.' +
          ' </span>' +
          '</md-toast>'
      });
    }

  })
  .factory('RequestsErrorHandler', ['$rootScope', '$q', function ($rootScope, $q) {
    return {
      // --- Response interceptor for handling errors generically ---
      responseError: function (rejection) {

        if (rejection.status === 400) {
          console.log(rejection.statusText);
          $rootScope.showError(rejection.statusText);
        }

        return $q.reject(rejection);
      }
    };
  }])
  .controller('LoginController', ['$rootScope', '$location', 'AuthService', function ($rootScope, $location, AuthService) {
    var self = this;

    self.username = null;
    self.password = null;
    self.error = null;

    self.login = function () {
      $rootScope.isLoggedIn = AuthService.login(self.username, self.password);

      if ($rootScope.isLoggedIn) {
        $location.url('/');
      } else {
        self.error = 'Usuario o Contraseña incorrecto'
      }
    }

  }]);