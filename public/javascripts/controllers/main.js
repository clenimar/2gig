(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('MainCtrl', function($scope, $http) {
            $scope.getAds = function() {
                $http.get('/api/ads')
                    .success(function(data) {
                        $scope.content = data;
                    })
                    .error(function() {
                        console.log("error while trying to get content...");
                        $scope.content = "error while trying to get content...";
                    });
            };

            $scope.getAds();
        })
})();