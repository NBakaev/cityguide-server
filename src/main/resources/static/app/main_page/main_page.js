'use strict';

angular.module('myApp.view1', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/main_page', {
            templateUrl: 'app/main_page/index.html',
            controller: 'mainPageController'
        })
            .when('/poi/:id', {
                templateUrl: 'app/main_page/details.html',
                controller: 'PoiDetailsController'
            });
    }])

    .controller('PoiDetailsController', ['$scope', '$rootScope', 'apiService', '$mdDialog', '$mdMedia', 'authService', '$route', '$sce',
        function ($scope, $rootScope, apiService, $mdDialog, $mdMedia, authService, $route, $sce) {

            $scope.poi = {};
            $scope.editMode = false;

            $scope.data = {};
            $scope.data.pageUrl = window.location.href;

            $scope.trustSrc = function (src) {
                return $sce.trustAsResourceUrl(src);
            };

            $scope.updatePoi = function (poi) {
                apiService.updatePoi(poi).then(function (data) {
                });
            };

            apiService.getPoiById($route.current.params.id).then(function (data) {
                $scope.poi = data;
            });

            $scope.getMapUrl = function () {
                if (!$scope.poi.id) {
                    return "";
                }
                return $scope.trustSrc('https://maps.google.com/maps/api/staticmap?center=' + $scope.poi.location.latitude + ',' + $scope.poi.location.longitude + '&zoom=15&size=400x200&sensor=false&markers=' + $scope.poi.location.latitude + ',' + $scope.poi.location.longitude + '&key=AIzaSyBiL8IeEDG_k2dOp3tLCIwgR1_uY-p4osA');
            }

            $scope.getVideoUrl = function () {
                if (!$scope.poi.id || !$scope.poi.videoUrl) {
                    return "";
                }
                return $scope.poi.videoUrl.replace("/watch?v=", "/embed/");
            }

        }])
    .controller('mainPageController', ['$scope', '$rootScope', 'apiService', '$mdDialog', '$mdMedia', 'authService', 'cartService', 'logger',
        function ($scope, $rootScope, apiService, $mdDialog, $mdMedia, authService, cartService, logger) {

            $scope.allPOIs = [];
            $scope.allCities = [];

            apiService.getAllCities().then(function (data) {
                data.forEach(function (data) {
                    $scope.allCities[data.id] = data;
                });

                $scope.allPOIs = data;
            });

            $scope.resolveCityById = function (id) {
                if (isUndefinedOrNull(id)){
                    return null;
                }

                if ($scope.allCities.size == 0 || isUndefinedOrNull($scope.allCities[id])) {
                    return null;
                }

                return $scope.allCities[id];
            };

            $scope.pageChanged = function (newPage) {
                apiService.getAllPOIs().then(function (data) {
                    $scope.allPOIs = data;
                });
            };
            $scope.pageChanged(1);

            var originatorEv;

            $scope.openMenu = function ($mdOpenMenu, ev) {
                originatorEv = ev;
                $mdOpenMenu(ev);
            };

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