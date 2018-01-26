'use strict';

angular.module('app').service('AuthService', AuthService);
AuthService.$inject = ['$http', '$location'];
function AuthService($http, $location) {

  var users = [
    { username: 'user', password: 'user' },
    { username: 'admin', password: 'admin' }
  ];

  return {
    login: login,
    logout: logout,
    isAuth: isAuth
  }

  /**
   * 
   * @param {String} user 
   * @param {String} pass 
   */
  function login(user, pass) {
    for (var i in users) {
      if (users[i].username == user.toLowerCase() && users[i].password == pass.toLowerCase()) {
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

}