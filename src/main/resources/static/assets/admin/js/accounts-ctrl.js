app.controller("accounts-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
    //
    $scope.previewImage = function(input) {
        var preview = document.getElementById('preview');
        var file = input.files[0];
        var reader = new FileReader();

        reader.onloadend = function () {
            preview.src = reader.result;
            preview.style.display = 'block';
        };

        if (file) {
            reader.readAsDataURL(file);
        } else {
            preview.src = '';
            preview.style.display = 'none';
        }
    };
    //
	$scope.initialize = function() {

		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
		});
		$scope.reset(); //để có hình mây lyc1 mới đầu
	}
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
/*			resp.data.createDate = new Date(resp.data.createDate)*/
			$scope.items.push(resp.data);
			$scope.reset();
			
			alert("Thêm mới tài khoản thành công!");
			
		}).catch(error => {
			alert("Lỗi thêm mới tài khoản!");
			console.log("Error", error);
		});
	}


	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		// $scope.form.expirationDate = new Date(item.expirationDate);
		$(".nav-tabs a:eq(0)").tab("show");
	}

	$scope.reset = function() {
		$scope.form = {
			available: true,
		}
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items[index] = item;
			alert("Cập nhật tài khoản thành công!");
		})
			.catch(error => {
				alert("Lỗi cập nhật tài khoản!");
				console.log("Error", error);
			});
	}

	$scope.delete = function(item) {
		if (confirm("Bạn muốn xóa tài khoản này?")) {
			$http.delete(`/rest/accounts/${item.username}`).then(resp => {
				var index = $scope.items.findIndex(p => p.username == item.username);
				$scope.items.splice(index, 1);
				$scope.reset();
				alert("Xóa tài khoản thành công!");
			}).catch(error => {
				alert("Lỗi xóa tài khoản!");
				console.log("Error", error);
			})
		}
	}
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			if (this.page < 0) {
				this.last();
			}
			if (this.page >= this.count) {
				this.first();
			}
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size)
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		last() {
			this.page = this.count - 1;
		},
		next() {
			this.page++;
		},
		prev() {
			this.page--;
		}
	}
	$scope.initialize();
});