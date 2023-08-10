angular.module('cafefresh-front').controller('testController',
    function ($scope, $http, $localStorage, $rootScope) {

      const contextPath = "http://localhost:8189/freshcafe";



      $scope.tryUploadImage = function () {
        $http.post(contextPath + '/api/v1/categories/upload', $scope.user)
        .then(function successCallback(response) {

          if (response.data.token) {
            $http.defaults.headers.common.Authorization = response.data.token;

            $localStorage.currentUser = {
              login: $scope.user.login,
              token: response.data.token
            };

            $scope.currentUserName = $scope.user.login;

            $scope.user.login = null;
            $scope.user.password = null;
            $scope.user.email = null;

            $location.path('/categories');
          }
        }, function errorCallback(response) {
          alert(response.data.messages);
        });
      };

      $scope.uploadCategoryImage = function () {
        let file1 = $('#fileinput').prop('files')[0];
        $scope.uploadFileToUrl(file1, '/api/v1/categories/upload')
      }


      $scope.uploadFileToUrl = function(file1, url){
        let file = new FormData();

        file.append('file', file1);

        return $http({
          url: contextPath + url,
          method: 'POST',
          data: file,
          //assign content-type as undefined, the browser
          //will assign the correct boundary for us
          headers: { 'Content-Type': undefined},
          //prevents serializing payload.  don't do it.
          transformRequest: angular.identity
        });
      }
    });