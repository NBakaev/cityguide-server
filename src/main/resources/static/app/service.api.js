angular.module('myApp.api', ['ngRoute', 'myApp.services'])
    .service('apiService', ['serverRequestService', function (serverRequestService) {

        // category
        this.getAllPOIs = function () {
            return serverRequestService.get('poi');
        };

        this.getPoiById = function (id) {
            return serverRequestService.get('poi/id/'+id);
        };

        this.addPoi = function (obj) {
            return serverRequestService.post('poi', obj);
        };

        this.updatePoi = function (obj) {
            return serverRequestService.put('poi', obj);
        };

        this.deletePoi = function (obj) {
            return serverRequestService.delete('poi/' + obj);
        };

        this.searchPOIsInRadius = function (obj) {
            return serverRequestService.post('poi/search', obj);
        };

        ///////////////////////////////////////////////////////////////

        // category
        this.getAllCities = function () {
            return serverRequestService.get('city');
        };

        this.addCity = function (obj) {
            return serverRequestService.post('city', obj);
        };

        this.updateCity = function (obj) {
            return serverRequestService.put('city', obj);
        };

        this.deleteCity = function (obj) {
            return serverRequestService.delete('city/' + obj);
        };

    }])
    
    .factory('authHttpResponseInterceptor', ['$q', '$location', 'logger', 'localStorageService', function ($q, $location, logger, localStorageService) {
        return {

            response: function (response) {
                return response || $q.when(response);
            },
            responseError: function (rejection) {
                console.warn(rejection);

                if (rejection.status === 401) {
                    console.log("Response Error 401", rejection);

                    logger.logError('Ошибка авторизации!');
                    // TODO: login form
                    localStorageService.remove("token");
                    // $location.path('login/login').search('returnTo', $location.path());
                }

                if (rejection.status === 403) {
                    console.log("Response Error 403", rejection);
                    logger.logError("Доступ запрещен к данному объекту или действию Вам!")
                }

                if (rejection.status === 503 || rejection.status === -1) {
                    console.log("Response Error 503", rejection);
                    // $location.path('pages/503').search('returnTo', $location.path());
                }

                if (rejection.status === 500) {
                    // logger.logError("Ошибка запроса к серверу!" + "<br>" + rejection.data.exception + "<br>" + rejection.data.message + "<br>" + rejection.data.path)
                    logger.logError("Ошибка запроса к серверу!" + "<br>" + rejection.data.message + "<br>" + rejection.data.path)
                }

                if (rejection.status === 404) {
                    // logger.logWarning("Ошибка запроса к серверу!");
                }

                return $q.reject(rejection);
            }
        }
    }])
