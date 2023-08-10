angular.module('cafefresh-front').controller('orderReportController', function ($scope, $http, $rootScope) {

  $scope.reportDate = '';

  $scope.showDate = function () {
    let date = $('#datepicker').datepicker('getDate');

    $http.post($rootScope.contextPath + '/api/v1/reports/order_report_date', date)
    .then(function successCallback(response) {
      console.log(response.data);
      $scope.reportDate = date;
      $scope.orderReportByDate = response.data;
    }, function errorCallback(response) {
      alert(response.data.messages);
    });
  }
});