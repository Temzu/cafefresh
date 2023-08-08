angular.module('cafefresh-front').controller('accountController',
    function ($scope, $http, $localStorage, $rootScope) {

      $scope.loadAccountInfo = function () {
        $http({
          url: $rootScope.contextPath + '/api/v1/accounts/current',
          method: 'GET'
        }).then(function (response) {
          $rootScope.currentUserInfo = response.data;
        });
      }

      $scope.changeFields = function () {
        $('#loginField').prop('disabled', false);
        $('#emailField').prop('disabled', false);
        $('#changeButton').prop('disabled', true);
        $('#saveButton').prop('disabled', false);
      }

      $scope.tryToSaveInfo = function () {
        $('#loginField').prop('disabled', false);
        $('#emailField').prop('disabled', false);
        $('#changeButton').prop('disabled', true);
        $('#saveButton').prop('disabled', false);
      }

      $scope.showMyOrders = function (pageIndex = 1) {
        $http({
          url: $rootScope.contextPath + '/api/v1/orders',
          method: 'GET',
          params: {
            page: pageIndex
          }
        }).then(function (response) {
          $scope.orderPage = response.data;
          console.log($scope.orderPage.content)
          $scope.navList = $scope.generatePagesIndexes(1, $scope.orderPage.totalPages);
        });
      };

      $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
          arr.push(i);
        }
        return arr;
      }

      $scope.changePass = function () {
        $http.post($rootScope.contextPath + '/api/v1/accounts/change_pass', $scope.newPass)
        .then(function successCallback(response) {
          $http.defaults.headers.common.Authorization = response.data.token;
          $localStorage.currentUser.token = response.data.token;
          $("#ModalPasswordChangeForm").modal("hide");
        }, function errorCallback(response) {
          alert(response.data.messages);
        });
      }


      $scope.showMyOrders();

      $scope.loadAccountInfo();
    });