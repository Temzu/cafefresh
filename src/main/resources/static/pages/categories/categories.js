angular.module('cafefresh-front').controller("categoriesController",
    function ($scope, $http, $localStorage, $routeParams, $rootScope, $location) {


      // $scope.categoryTitle = $routeParams.categoryTitle;

      $scope.loadPage = function (pageIndex = 1) {
        $http({
          url: $rootScope.contextPath + '/api/v1/categories',
          method: 'GET',
          params: {
            page: pageIndex
          }
        }).then(function (response) {
          $scope.categoryPage = response.data;
          $scope.navList = $scope.generatePagesIndexes(1, $scope.categoryPage.totalPages);
        });
      };

      $scope.showCategoryPage = function (pageIndex = 1) {
        $http({
          url: $rootScope.contextPath + '/api/v1/categories',
          method: 'GET',
          params: {
            page: pageIndex
          }
        }).then(function (response) {
          $scope.categoryPage = response.data;

          let minPageIndex = pageIndex - 2;
          if (minPageIndex < 1) {
            minPageIndex = 1;
          }

          let maxPageIndex = pageIndex + 2;
          if (maxPageIndex > $scope.categoryPage.totalPages) {
            maxPageIndex = $scope.categoryPage.totalPages;
          }

          $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
          console.log(response.data);
        });
      };

      $scope.showPageByCategory = function (categoryTitle = '', pageIndex = 1) {
        $http({
          url: $rootScope.contextPath + '/api/v1/categories/' + categoryTitle,
          method: 'GET',
          params: {
            page: pageIndex
          }
        }).then(function (response) {
          $rootScope.productsPage = response.data;
          $rootScope.categoryTitle = categoryTitle;

          console.log($rootScope.productsPage.content)

          let minPageIndex = pageIndex - 2;
          if (minPageIndex < 1) {
            minPageIndex = 1;
          }

          let maxPageIndex = pageIndex + 2;
          if (maxPageIndex > $scope.productsPage.totalPages) {
            maxPageIndex = $scope.productsPage.totalPages;
          }

          $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
          console.log(response.data);
        });
      };

      $scope.addToCart = function (productId) {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/add/' + productId,
          method: 'GET'
        }).then(function successCallback(response) {
          $scope.loadCart();
          $("#ModalCategoriesForm" + productId).modal("hide");
        });
      }

      $scope.loadCart = function () {
        $http({
          url: $rootScope.contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid,
          method: 'GET'
        }).then(function (response) {
          $rootScope.cart = response.data;
        });
      }

      $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
          arr.push(i);
        }
        return arr;
      }

      $scope.showCategoryPage();
      // $scope.url = $location.absUrl().split('/')[6];
      // $scope.showPageByCategory(decodeURIComponent($scope.url));

    })