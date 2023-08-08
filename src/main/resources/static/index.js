(function ($localStorage) {
  'use strict';

  angular
  .module('cafefresh-front', ['ngRoute', 'ngStorage'])
  .config(config)
  .run(run);

  function config($routeProvider) {

    $routeProvider
    .when('/', {
      templateUrl: 'pages/home/home.html',
      controller: 'homeController'
    })
    .when('/products', {
      templateUrl: 'pages/menu/categories.html',
      controller: 'productsController'
    })
    .when('/categories', {
      templateUrl: 'pages/categories/categories.html',
      controller: 'categoriesController'
    })
    .when('/categories/:categoryTitle', {
      templateUrl: 'pages/categories/categories_item.html',
      controller: 'categoriesController'
    })
    .when('/registration', {
      templateUrl: 'pages/registration/registration.html',
      controller: 'registrationController'
    })
    .when('/orders', {
      templateUrl: 'pages/order/order.html',
      controller: 'ordersController'
    })
    .when('/cart', {
      templateUrl: 'pages/cart/cart.html',
      controller: 'cartController'
    })
    .when('/account', {
      templateUrl: 'pages/account/account.html',
      controller: 'accountController'
    })
    .when('/manager', {
      templateUrl: 'pages/manager/manager.html',
      controller: 'managerController'
    })
    .when('/manager/order_report', {
      templateUrl: 'pages/manager/report/order_report.html',
      controller: 'orderReportController'
    })
    .when('/admin', {
      templateUrl: 'pages/admin_panel/admin-control-panel.html',
      controller: 'adminController'
    })
    .when('/test', {
      templateUrl: 'pages/test/test.html',
      controller: 'testController'
    })
    .otherwise({
      redirectTo: '/'
    });
  }
  const contextPath = "https://localhost:8189/freshcafe";

  function run($rootScope, $http, $localStorage) {
//
    $("#reg").click(function(){
      $("#ModalForm").modal("hide");
    });

    if ($localStorage.currentUser) {
      $http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
    }

    if (!$localStorage.guestCartUuid) {
      $http.get(contextPath + '/api/v1/cart/generate')
      .then(function successCallback(response) {
        $localStorage.guestCartUuid = response.data.value;
        console.log($localStorage.guestCartUuid);
      }, function errorCallback(response) {
        alert(response.data.messages);
      });
    } else {
      $http({
        url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid,
        method: 'GET'
      }).then(function (response) {
        $rootScope.cart = response.data;
      }, function errorCallback(response) {
        console.log("err")
        $http.defaults.headers.common.Authorization = null;

        delete $localStorage.currentUser;

      });
    }

  }
})();

angular.module('cafefresh-front').controller('indexController',
    function ($rootScope, $scope, $http, $localStorage, $location) {

      $rootScope.contextPath = "https://localhost:8189/freshcafe";

      let myModal = document.getElementById('ModalForm');

      $scope.tryToAuth = function () {

        $http.post($rootScope.contextPath + '/api/v1/auth/login', $scope.user)
        .then(function successCallback(response) {
          if (response.data.token) {
            $http.defaults.headers.common.Authorization = response.data.token;
            $localStorage.currentUser = {
              email: $scope.user.email,
              token: response.data.token
            };

            $scope.currentUserName = $scope.user.email;

            $scope.user.email = null;
            $scope.user.password = null;

            $("#ModalForm").modal("hide");
            $location.path('/categories');

            $http.get(
                $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/merge')
            .then(function successCallback(response) {
            });

            $http({
              url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid,
              method: 'GET'
            }).then(function (response) {
              $rootScope.cart = response.data;
            });
          }
        }, function errorCallback(response) {
          alert(response.data.messages);
        });

      };

      $scope.tryToLogout = function () {
        $scope.clearUser();

        $http.defaults.headers.common.Authorization = null;

        if ($scope.user.email) {
          $scope.user.email = null;
        }
        if ($scope.user.password) {
          $scope.user.password = null;
        }

      };

      $scope.clearUser = function () {

        $http.post($rootScope.contextPath + '/api/v1/auth/logout')
        .then(function successCallback(response) {
          //--//
        },function errorCallback(response) {
          alert(response.data.messages);
        });

        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
      };

      $scope.toRegistration = function () {
        $location.path('/registration');
      };

      $scope.toAccount = function () {
        $location.path('/account');
      };



      $rootScope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
          return true;
        } else {
          return false;
        }
      };

      $scope.isCartEmpty = function () {
        if ($rootScope.cart == null || $rootScope.cart.items.length === 0) {
          return true;
        } else {
          return false;
        }
      }
    });