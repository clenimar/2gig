(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('MainCtrl', function($scope, $http) {
            $scope.noAdFoundMessage = "Nenhum anúncio para exibir.";
            $scope.noAd = false;
            $scope.query = "";

            var noAdCheck = function() {
                $scope.noAd = ($scope.content).isEmpty;
            };

            $scope.countSuccesses = function() {
                $http.get('/api/ads/closed')
                    .success(function(data) {
                        $scope.successCount = data.count;
                    })
                    .error(function() {
                        console.log("Coudn't retrieve success count...")
                    })
            };
            $scope.countSuccesses();

            $scope.getAds = function() {
                $http.get('/api/ads')
                    .success(function(data) {
                        $scope.content = data;
                        noAdCheck();
                    })
                    .error(function() {
                        console.log("error while trying to get content...");
                        $scope.content = "error while trying to get content...";
                    });
            };
            $scope.getAds();

            $scope.calculateTimeString = function(timestamp) {
                moment.locale('pt-BR');
                return moment(timestamp).fromNow();
            };

            var self = this;

            self.ad = {};
            self.setAd = function(ad) {
                self.ad = ad;
            };
            self.cleanAd = function() {
                self.ad = {};
            }
        })
})();