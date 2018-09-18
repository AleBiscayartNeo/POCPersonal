<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es" ng-app="app">
<script language="javascript"> 

   var s="<%=request.getSession().getAttribute("accessToken")%>"; 
   sessionStorage.setItem("auth", s);

</script> 
<head>	
  <title>Club Personal | Beneficios</title>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no" />

  <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,400italic'>
  <link rel="stylesheet" href="./node_modules/angular-material/angular-material.css" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="./node_modules/angular-material-data-table/dist/md-data-table.css">
  <link rel="stylesheet" href="./app/assets/app.css" />

  <style type="text/css">
    /**
     * Hide when Angular is not yet loaded and initialized
     */

    [ng\:cloak],
    [ng-cloak],
    [data-ng-cloak],
    [x-ng-cloak],
    .ng-cloak,
    .x-ng-cloak {
      display: none !important;
    }
  </style>

</head>

<body layout="column" layout-fill ng-cloak>

  <md-toolbar md-whiteframe="5" ng-if="isLoggedIn">
    <div class="md-toolbar-tools">
      <md-button class="md-icon-button md-primary md-hue-2" aria-label="Settings" ng-click="toggleList()" hide-gt-sm>
        <i class="material-icons">menu</i>
      </md-button>

      <img src="./app/assets/img/logo-club-personal-grey.png" alt="">

      <div flex></div>

      <md-menu md-position-mode="target-right target">
        <md-button ng-click="$mdMenu.open($event)" class="md-icon-button md-primary md-hue-2" aria-label="Open menu">
          <i class="material-icons">account_circle</i>
        </md-button>
        <md-menu-content width="3">
          <md-menu-item>
            <md-button ng-click="doSomething()">Mi Perfil</md-button>
          </md-menu-item>
          <md-menu-divider></md-menu-divider>
          <md-menu-item>
            <md-button href="/app-beneficios/bo/logout">Salir</md-button>
          </md-menu-item>
        </md-menu-content>
      </md-menu>
    </div>
  </md-toolbar>

  <div flex layout="row" ng-controller="SidenavCtrl as ctrl">

    <md-sidenav ng-if="isLoggedIn" ng-click="toggleList()" md-is-locked-open="$mdMedia('gt-sm')" md-component-id="left" md-whiteframe="3">
      <md-list>
        <md-list-item ng-repeat="item in ctrl.menuItems" ng-if="ctrl.canUse(item)">
          <md-button ng-click="ctrl.go(item.url)" ng-class="{'selected' : ctrl.isSelected(item.url) }">
            <i class="material-icons">{{ item.icon }}</i>
            {{ item.name }}
          </md-button>
          <md-divider ng-if="!$last"></md-divider>
        </md-list-item>
      </md-list>
    </md-sidenav>

    <md-content flex id="content">
      <div ng-view></div>
    </md-content>

  </div>

  <!-- Angular -->
  <script src="./node_modules/angular/angular.js"></script>
  <script src="./node_modules/angular-route/angular-route.js"></script>
  <script src="./node_modules/angular-animate/angular-animate.js"></script>
  <script src="./node_modules/angular-aria/angular-aria.js"></script>
  <script src="./node_modules/angular-messages/angular-messages.js"></script>
  <script src="./node_modules/angular-material/angular-material.js"></script>

  <!-- Ng-file-upload -->
  <script src="./node_modules/ng-file-upload/dist/ng-file-upload.min.js"></script>

  <!-- App  -->
  <script src="./app/src/app.js"></script>
  <script src="./app/src/sidenav.js"></script>
  <script src="./app/src/commonServices.js"></script>
  <script src="./app/src/authService.js"></script>

  <!-- Proveedores -->
  <script src="./app/src/proveedores/proveedores.js"></script>
  <script src="./app/src/proveedores/proveedoresController.js"></script>
  <script src="./app/src/proveedores/proveedoresService.js"></script>

  <!-- Descuentos -->
  <script src="./app/src/descuentos/descuentos.js"></script>
  <script src="./app/src/descuentos/descuentosController.js"></script>
  <script src="./app/src/descuentos/descuentosService.js"></script>

  <!-- Sucursales -->
  <script src="./app/src/sucursales/sucursales.js"></script>
  <script src="./app/src/sucursales/sucursalesController.js"></script>
  <script src="./app/src/sucursales/sucursalesService.js"></script>
  
  <!-- Notificaciones -->
  <script src="./app/src/notificaciones/notificaciones.js"></script>
  <script src="./app/src/notificaciones/notificacionesController.js"></script>
  <script src="./app/src/notificaciones/notificacionesServices.js"></script>


  <!-- angular-material-data-table -->
  <script src="./node_modules/angular-material-data-table/dist/md-data-table.js"></script>

<% out.print(request.getSession().getAttribute("accessToken"));%>

</body>

</html>
