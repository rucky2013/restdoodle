var app= angular.module('myApp', ['ngResource', 'ngRoute']);

// Router

app.config(function ($routeProvider){
   $routeProvider
       .when('/', {
           templateUrl: 'pages/main.html'
       })
       .when('/companies', {
           templateUrl: 'pages/companylist.html',
           controller: 'CompaniesCtrl'
       })
       .when('/companydetail', {
           templateUrl: 'pages/companydetail.html',
           controller: 'CompanyDetailCtrl'
       })
       .when('/companydetailresult', {
           templateUrl: 'pages/companydetailresult.html',
           controller: 'CompanyDetailResultCtrl'
       })
       .when('/operationresult', {
           templateUrl: 'pages/operationresult.html',
           controller: 'OperationResultCtrl'
       })
       .when('/companycreateupdate', {
           templateUrl: 'pages/companycreateupdate.html',
           controller: 'CompanyCreateUpdateCtrl'
       })
});

// Factory

app.factory("Companies", function($resource) {
   return $resource("http://localhost:4950/v1/companies/:companyId", null, {
       query: {
           headers: {'Content-type':'application/json'}
       },
       update: {
           method: 'PUT'
       }
   });
});

// Services

app.service('companyDetailService', function() {
   this.companyId = 'UUID';
});

app.service('companyService', function() {
    this.company = '';
    this.update = false;
    this.companyId = '';
});

// Controllers

app.controller('CompaniesCtrl', function($scope, Companies) {
    Companies.query(function(data) {
        $scope.resp = data;
    });
});

app.controller('CompanyDetailCtrl', ['$scope', 'companyDetailService', 'Companies', function($scope, companyDetailService, Companies) {
    $scope.companyId = companyDetailService.companyId;
    $scope.$watch('companyId', function() {
        companyDetailService.companyId = $scope.companyId;
    });
}]);

app.controller('CompanyDetailResultCtrl', ['$scope', 'companyDetailService', 'Companies', function($scope, companyDetailService, Companies) {
    $scope.companyId = companyDetailService.companyId;
    Companies.get({companyId : $scope.companyId}, function(companyId){
        $scope.data = companyId;
    });
}]);

app.controller('CompanyCreateUpdateCtrl', ['$scope', 'companyService', 'Companies', function($scope, companyService, Companies) {
    $scope.company = companyService.company;
    $scope.emps = [];

    $scope.addFormField = function() {
        $scope.emps.push({});
    };

    $scope.loadCompany = function(){
        Companies.get({companyId : $scope.companyId}, function(companyId){
            $scope.data = companyId;
            if ($scope.data.company != null) {
                $scope.company = $scope.data.company;
                $scope.emps = $scope.data.company.employees;
                companyService.update = true;
                companyService.companyId = $scope.companyId;
            }
        });
    };

    $scope.$watch('emps', function() {
        companyService.company = $scope.company;
        companyService.company.employees = $scope.emps;
    });

    $scope.$watch('company', function() {
        companyService.company = $scope.company;
        companyService.company.employees = $scope.emps;
    });
}]);

app.controller('OperationResultCtrl', ['$scope', 'companyService', 'Companies', function($scope, companyService, Companies) {

    if (!companyService.update) {
        Companies.save(angular.toJson(companyService.company), function (data) {
            $scope.data = data;
        })
    } else {
        Companies.update({companyId : companyService.companyId}, angular.toJson(companyService.company), function (data) {
            $scope.data = data;
        })
    }
    // reset the service
    companyService.company = '';
    companyService.update = false;
    companyService.companyId = 'UUID';
}]);