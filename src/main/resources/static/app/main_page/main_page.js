'use strict';

angular.module('myApp.view1', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/main_page', {
            templateUrl: 'app/main_page/index.html',
            controller: 'mainPageController'
        });
    }])
    
    .controller('mainPageController', ['$scope', '$rootScope', 'apiService', '$mdDialog', '$mdMedia', 'authService', 'cartService', 'logger',
        function ($scope, $rootScope, apiService, $mdDialog, $mdMedia, authService, cartService, logger) {

            $scope.allPOIs = [];

            $scope.pageChanged = function (newPage) {
                apiService.getAllPOIs().then(function (data) {
                    $scope.allPOIs = data;
                });
            };
            $scope.pageChanged(1);

            $scope.searchRequest = {};
            $scope.searchRequest.radius = 0;

            $scope.updatePoi = function (poi) {
                apiService.updatePoi(poi).then(function (data) {
                });
            };

            $scope.addPoi = function (poi) {
                apiService.addPoi({}).then(function (data) {
                    $scope.allPOIs.push(data);
                });
            };

            $scope.searchPOIsInRadius = function () {
                apiService.searchPOIsInRadius($scope.searchRequest).then(function (data) {
                    $scope.allPOIs = data;
                });
            };

            $scope.deletePoi = function (poi, index) {
                apiService.deletePoi(poi.id).then(function (data) {
                    $scope.allPOIs.splice(index, 1);
                });
            }

        }]);