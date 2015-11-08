(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('NewAdCtrl', function($scope, $http, $window) {
            $scope.pageClass = 'new';
            $scope.new = {};

            $scope.newAds = function() {
                $http.post('/api/ads', $scope.new)
                    .success(function() {
                        Materialize.toast("Anúncio criado com sucesso :)", 4000);
                        console.log("deu certo, moral!");
                        $scope.new = {};
                        $window.location = '#/';
                    })
                    .error(function() {
                        console.log("error while trying to post content...");
                        Materialize.toast("Ops! Algo deu errado. Verifique as informações e tente novamente. :(", 4000);
                    });
            };
        })
})();