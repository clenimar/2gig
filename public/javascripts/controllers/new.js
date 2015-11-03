(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('NewAdCtrl', function($scope, $http) {
            $scope.new = {
                "author":"",
                "title":"",
                "description":"",
                "street":"",
                "number":"",
                "neighbourhood":"",
                "city":"",
                "state":"",
                "country":"",
                "email":"",
                "phone1":"",
                "instrument":"",
                "desired_styles":"",
                "undesired_styles":"",
                "interest":"",
                "passwd":""
            };

            $scope.newAds = function() {
                $http.post('/api/ads', $scope.new)
                    .success(function() {
                        console.log("deu certo, moral!");
                    })
                    .error(function() {
                        console.log("error while trying to post content...");
                    });
            };
        })
})();