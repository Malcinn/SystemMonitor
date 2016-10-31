var socketInfoList = "http://localhost:8080/manager/myresource/socketInfoList";

var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.getSocketInfoList = function() {
		$http.get(socketInfoList).then(function(response) {
			$scope.sockets = response.data;
		});
	};

	$scope.getSystemInfo = function() {
		systemInfoURL = getSystemInfoURL("127.0.0.1", 2223);
		$http.get(systemInfoURL).then(function(response) {
			$scope.responseData = response.data;
			
		});
	};
	
	function getSystemInfoURL(host, port) {
		return "http://localhost:8080/manager/myresource/systeminfo?host=" + host
				+ "&port=" + port;
	}
});

