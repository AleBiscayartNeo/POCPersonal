'use strict';

angular.module('app').service('AuthService', AuthService);
AuthService.$inject = ['$http', '$location'];
function AuthService($http, $location) {

  var users = [
    { username: 'user', password: 'user', rol: 'user' },
    { username: 'admin', password: 'admin', rol: 'admin' }
  ];

  return {
    login: login,
    logout: logout,
    isAuth: isAuth,
    guard: guard
  }

  /**
   * 
   * @param {String} user 
   * @param {String} pass 
   */
  function login(user, pass) {
    for (var i in users) {
      if (users[i].username.toLowerCase() == user.toLowerCase() && users[i].password == pass) {
        sessionStorage.setItem("auth", JSON.stringify(users[i]));
        return true;
        break;
      }
    }

    return false;
  }

  /**
   * 
   */
  function logout() {
    sessionStorage.removeItem("auth");
    $location.url('/');
  }

  /**
   * 
   */
  function isAuth() {
    return JSON.parse(sessionStorage.getItem('auth')) != null;
  }

  /**
   * 
   */
  function guard() {
    var user = JSON.parse(sessionStorage.getItem('auth'));
    return user.rol == 'admin';
  }
}