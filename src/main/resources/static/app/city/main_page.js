'use strict';

angular.module('myApp.city', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/city', {
            templateUrl: 'app/city/index.html',
            controller: 'CityAllController'
        });
    }])
    
    .controller('CityAllController', ['$scope', '$rootScope', 'apiService', '$mdDialog', '$mdMedia', 'authService', 'cartService', 'logger',
        function ($scope, $rootScope, apiService, $mdDialog, $mdMedia, authService, cartService, logger) {

            $scope.allPOIs = [];

            $scope.pageChanged = function (newPage) {
                apiService.getAllCities().then(function (data) {
                    $scope.allPOIs = data;
                });
            };
            $scope.pageChanged(1);

            $scope.updatePoi = function (poi) {
                apiService.updateCity(poi).then(function (data) {
                });
            };

            $scope.addPoi = function (poi) {
                apiService.addCity({}).then(function (data) {
                    $scope.allPOIs.push(data);
                });
            };

            $scope.deletePoi = function (poi, index) {
                apiService.deleteCity(poi.id).then(function (data) {
                    $scope.allPOIs.splice(index, 1);
                });
            }

        }]);