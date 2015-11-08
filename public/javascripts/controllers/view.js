(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('ViewAdCtrl', function($scope, $http, $stateParams) {
            var currentId = $stateParams.id;
            $scope.current = {};  
            $scope.noCommentMessage = "Sem comentários para exibir.";
            $scope.noVideoMessage = "Sem vídeos para exibir.";  

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

            $scope.calculateTimeString = function(timestamp) {
                moment.locale('pt-BR');
                return moment(timestamp).fromNow();
            };
        })
})();