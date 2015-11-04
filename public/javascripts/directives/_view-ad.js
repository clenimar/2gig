(function() {
    'use strict';

    angular.module('2GIG')
        .directive('viewAd', function() {
            return {
                restrict: 'E',
                templateUrl: '/assets/javascripts/directives/_view-ad.html'
            };
        });
})();