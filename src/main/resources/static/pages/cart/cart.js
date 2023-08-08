angular.module('cafefresh-front').controller('cartController',
    function ($scope, $http, $localStorage, $rootScope) {

      $scope.loadCart = function () {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid,
          method: 'GET'
        }).then(function (response) {
          $rootScope.cart = response.data;
        });
      }

      $scope.addToCart = function (productId) {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/add/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        }, function errorCallback(response) {
          console.log($localStorage.guestCartUuid);
        });
      }

      $scope.incrementCartPosition = function (productId) {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/add/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        });
      }

      $scope.decrementCartPosition = function (productId) {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/decrement/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        });
      }

      $scope.clearCart = function () {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/clear',
          method: 'GET'
        }).then(function (response) {
          $rootScope.cart = null;
        });
      }

      $scope.removeItemFromCart = function (productId) {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/remove/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        });
      }

      $scope.createOrder = function () {
        $http.post($rootScope.contextPath + '/api/v1/orders', $scope.order_info)
        .then(function (response) {
          alert('Заказ создан');
          $scope.loadCart();
        }, function errorCallback(response) {
          alert(response.data.messages);
        });
      }

      $scope.filterMyData = function (input, search_param) {
        if (input === search_param) {
          return true;
        }
      }

      $http.get($rootScope.contextPath + '/api/v1/products/list')
      .then(function successCallback(response) {
        $rootScope.allProducts = response.data;
        console.log($rootScope.allProducts);
      });

      $scope.loadCart();
    });