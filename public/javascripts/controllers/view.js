(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('ViewAdCtrl', function($scope, $http, $stateParams) {
            var currentId = $stateParams.id;
            $scope.current = {};    

            $scope.getCurrent = function() {
                console.log(currentId);
                $http.get('/api/ads/' + currentId)
                    .success(function (data) {
                        $scope.current = data;
                        console.log("Ad #" + currentId + " was loaded successfully...");
                    })
                    .error(function () {
                        console.log("Coudn't get Ad #" + currentId);
                    });
            }
            $scope.getCurrent();

        })
})();