var app= angular.module('myApp', ['ngResource', 'ngRoute']);

app.config(function ($routeProvider){
   $routeProvider
       .when('/', {
           templateUrl: 'pages/main.html'
       })
       .when('/companies', {
           templateUrl: 'pages/companylist.html',
           controller: 'CompaniesCtrl'
       })
       .when('/companyDetail', {
           templateUrl: 'pages/companydetail.html',
           controller: 'CompaniesCtrl'
       })
});

app.factory("Companies", function($resource) {
   return $resource("http://localhost:4950/v1/companies/:id", null, {
       query: {
           headers: {'Content-type':'application/json'}
       }
   });
});

app.controller("CompaniesCtrl", function($scope, Companies) {
   Companies.query(function(data) {
      $scope.resp = data;
   });
});