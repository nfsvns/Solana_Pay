
  document.addEventListener("DOMContentLoaded",function(){
  var ctx = document.getElementById("myAreaChart").getContext("2d");

  // Dữ liệu và cấu hình cho biểu đồ
  var data = {
    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
    datasets: [
      {
        label: "Sample Data",
        data: [10, 20, 15, 25, 30, 35],
        fill: true,
        borderColor: "rgb(75, 192, 192)",
        tension: 0.1
      }
    ]
  };

  var config = {
    type: "line",
    data: data,
    options: {
      responsive: true,
      scales: {
        x: {
          beginAtZero: true
        },
        y: {
          beginAtZero: true
        }
      }
    }
  };
  var myLineChart = new Chart(ctx, config);


  })
  
  // Lấy tham chiếu đến phần tử canvas
 