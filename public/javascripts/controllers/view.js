(function() {
    'use strict';

    angular
        .module('2GIG')
        .controller('ViewAdCtrl', function() {
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