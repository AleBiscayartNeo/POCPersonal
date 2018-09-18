'use strict';

angular.module('app').service('AuthService', AuthService);
AuthService.$inject = ['$http', '$location'];
function AuthService($http, $location) {

  return {
    logout: logout,
    isAuth: isAuth
  }


  /**
   * 
   */
  function logout() {
    sessionStorage.removeItem("auth");
  }

  /**
   * 
   */
  function isAuth() {
	var isAuth = sessionStorage.getItem('auth');
    return (isAuth != null && isAuth != 'null' && isAuth != '');
  }
}