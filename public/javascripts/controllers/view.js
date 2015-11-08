(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('ViewAdCtrl', function($scope, $http, $stateParams, $window) {
            var currentId = $stateParams.id;
            $scope.current = {};  
            $scope.noCommentMessage = "Sem comentários para exibir.";
            $scope.noVideoMessage = "Sem vídeos para exibir.";
            $scope.showForm = false;
            $scope.data = {
                "pass": "",
                "feedback": ""
            };

            $scope.show = function() {
                $scope.showForm = true;
            }

            $scope.getCurrent = function() {
                console.log(currentId);
                $http.get('/api/ads/' + currentId)
                    .success(function (data) {
                        $scope.current = data[0];
                        console.log("Ad #" + currentId + " was loaded successfully...");
                    })
                    .error(function () {
                        console.log("Coudn't get Ad #" + currentId);
                    });
            }
            $scope.getCurrent();

            $scope.closeAds = function() {
                $http.post('/api/ads/closed/' + currentId, $scope.data)
                    .success(function() {
                        console.log("Ad #" + currentId + " closed.");
                        Materialize.toast("Anúncio finalizado com sucesso.", 4000);
                        $window.location = "#/";
                    })
                    .error(function() {
                        console.log("Error.");
                        Materialize.toast("Palavra-passe incorreta.", 4000);
                        $scope.data = {};
                    });
            };

            $scope.calculateTimeString = function(timestamp) {
                moment.locale('pt-BR');
                return moment(timestamp).fromNow();
            };
        })
})();