app = angular.module("app", ["ngRoute"]);

/*app.run(function($http, $rootScope){
	$http.get(`/rest/security/authentication`).then(resp => {
		if(resp.data){
			$auth = $rootScope.$auth = resp.data;
			$http.defaults.headers.common["Authorization"] = $auth.token;
		}
	});
})*/

app.config(function($routeProvider) {
	$routeProvider
		.when("/product", {
			templateUrl: "/assets/admin/product/index.html",
			controller: "product-ctrl"
		})
		.when("/authorize", {
			templateUrl: "/assets/admin/authority/index.html",
			controller: "authority-ctrl"
		})
		.when("/unauthorized", {
			templateUrl: "/assets/admin/authority/unauthorized.html",
			controller: "authority-ctrl"
		})
		.when("/accounts", {
			templateUrl: "/assets/admin/accounts/index.html",
			controller: "accounts-ctrl"
		}).when("/comments", {
			templateUrl: "/assets/admin/comment/index.html",
			controller: "comments-ctrl"
		})
		.when("/chart", {
			templateUrl: "/assets/admin/charts.html"
		})

		.otherwise({
			template: "<h1 class='text-center'>Shoe Galaxy Administration</h1>"

		});
});