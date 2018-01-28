/**
 * Cordova PlayStoreAvailability plugin
 * Author: Ippei Ukai <ukai@atama.plus>
 * License: Apache 2.0
 */
var exec = require('cordova/exec');

function PlayStoreAvailability() { }

PlayStoreAvailability.prototype.check = function(successCb, errorCb) {
    exec(successCb, errorCb, 'PlayStoreAvailability', 'check', []);
};

var playStoreAvailability = new PlayStoreAvailability();
module.exports = playStoreAvailability;
