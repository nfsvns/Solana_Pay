const app = angular.module("app", [])
app.controller("cart-ctrl", function($scope, $http, $window) {
	$scope.cart = [];
	$scope.publicKey = '';
	const SHYFT_API_KEY = "bjF5eRvXmibGXZkw";
	$scope.balance = 0.0;

	var $cart = $scope.cart = {
		items: [],
		add(id) {


			// Lấy giá trị từ biến totalAmount và sử dụng nó khi thêm sản phẩm
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
			} else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					resp.data.sizes = $scope.selectedSize;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				});
			}
		},
		remove(id) { // xóa sản phẩm khỏi giỏ hàng
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear() { // Xóa sạch các mặt hàng trong giỏ
			this.items = []
			this.saveToLocalStorage();
		},
		price_product(item) {
			if (item.discount_sale == null) {
				return item.price
			} else {
				return (item.price - (item.price * item.discount_sale.percentage / 100))
			}
		},
		amtt_of(item) {
			var itemPrice = this.price_product(item); // Gọi hàm price_product(item) từ cùng đối tượng
			return itemPrice * item.qty; // Tính giá tiền của sản phẩm với số lượng
		},

		amt_of(item) { // tính thành tiền của 1 sản phẩm
			return item.price * item.qty;
		},
		get count() { // tính tổng số lượng các mặt hàng trong giỏ
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		get amount() { // tổng thành tiền các mặt hàng trong giỏ
			return this.items
				.map(item => this.amtt_of(item))
				.reduce((total, amt) => total += amt, 0);
		},
		saveToLocalStorage() { // lưu giỏ hàng vào local storage
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage() { // đọc giỏ hàng từ local storage
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}

	$cart.loadFromLocalStorage();

	// Đặt hàng
	$scope.order = {
		get account() {
			return { username: $auth.user.username }
		},
		createDate: new Date(),
		address: "",
		get orderDetails() {
			return $cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			});
		},
		purchase() {
			var order = angular.copy(this);
			// Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công!");
				$cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng lỗi!")
				console.log(error)
			})
		}
	}


	// Định nghĩa hàm connectWallet
	$scope.connectWallet = async function() {
		await window.phantom.solana.connect();
		$scope.publicKey = window.phantom.solana.publicKey.toBase58();
		var spanElement = document.getElementById('remoteU');
		var spanText = spanElement !== null ? spanElement.innerText : null;

		if (spanText) {
			$http.get(`/rest/accounts/${spanText}`).then(function(response) {
				if (response.data) {
					var data = response.data;
					data.wallet_key = $scope.publicKey;
					$http.put(`/rest/accounts/${spanText}`, data)
						.then(function(response) {
							console.log("Dữ liệu đã được cập nhật trong cơ sở dữ liệu", response.data);
							alert('Kết nối ví thành công');
						})
						.catch(function(error) {
							console.error("Lỗi khi cập nhật dữ liệu trong cơ sở dữ liệu: ", error);
						});
				}
			}).catch(function(error) {
				console.error("Lỗi khi thực hiện yêu cầu GET: ", error);
			});
		}
	};

	$scope.getBalance = async function() {
		var myHeaders = new Headers();
		myHeaders.append("x-api-key", SHYFT_API_KEY);
		var spanElement = document.getElementById('remoteU');
		var spanText = spanElement !== null ? spanElement.innerText : null;

		await $http.get(`/rest/accounts/${spanText}`).then(function(response) {
			if (response.data) {
				var requestOptions = {
					method: 'GET',
					headers: myHeaders,
					redirect: 'follow'
				};

				fetch("https://api.shyft.to/sol/v1/wallet/balance?network=devnet&wallet=" + response.data.wallet_key, requestOptions)
					.then(async response => {
						let res = await response.json();
						$scope.$apply(function() {
							$scope.balance = res.result.balance;
							console.log("Balance: " + $scope.balance);
						});
					})
					.catch(error => console.log('lỗi', error));
			}
		}).catch(function(error) {
			console.error("Lỗi khi thực hiện yêu cầu GET: ", error);
		});

	}

	$scope.requestData = {
		network: 'devnet',
		success_url: 'http://localhost:8080/success',
		cancel_url: 'http://localhost:8080/cancel',
		tokens: ['dust', 'samo'],

		shipping_fees: 0.1,
		config: {
			collect_shipping_address: true
		}
	};


	$scope.initiateCheckout = function() {
		$scope.requestData.items = $cart.items.map(function(item) {
			return {
				name: item.name,
				price: $cart.price_product(item),
				image: 'https://imgur.com/FOFmXwO.png',
				quantity: item.qty,

			};
		});
		$http.post('/api/checkout/session', $scope.requestData)
			.then(function(response) {
				var sessionId = response.data.session_id;
				$scope.generatePaymentURL(sessionId);
			})
			.catch(function(error) {
				console.error(error);
			});
	};

	$scope.generatePaymentURL = function(sessionId) {
		const apiUrl = 'https://checkout-api.candypay.fun/api/v1/session/payment_url?session_id=' + sessionId;
		// Thêm header Authorization vào yêu cầu
		const headers = {

			'Authorization': 'Bearer ' + 'cp_public_ecHjg7Uc_6gwDsez4EPi4QT6nHeGxdJwB' // Thay yourPublicApiKey bằng giá trị Public API key của bạn
		};

		$http.get(apiUrl, { headers: headers })
			.then(function(response) {
				var paymentUrl = response.data.payment_url;
				// Redirect đến trang thanh toán
				$window.location.href = paymentUrl;
			})
			.catch(function(error) {
				console.error(error);
			});
	};





})