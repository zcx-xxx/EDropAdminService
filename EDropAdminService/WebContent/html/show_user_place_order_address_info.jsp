<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="http://localhost:8080/EDropAdminService/" />
<meta charset="utf-8">
<title>Insert title here</title>
<script src="html/scripts/echarts/echarts.min.js"></script>
<script type="text/javascript" src="html/scripts/jquery/jquery-1.7.1.js"></script>
<!--引入百度地图-->
<script  type="text/javascript" 
src="http://api.map.baidu.com/api?v=2.0&ak=DDLwA2CBFGHRpxFzFx3K5KnBQtHP4hte" ></script>
<!--引入百度热力图-->
<script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type: "POST",
			url: "user_place_order_address_info/get_order_address_info",
			data: "",
			dataType: "json",
			success: function(msg){
				data = msg;
				var myChart = echarts.init(document.getElementById('main'));
				
				var points = [].concat.apply([], data.map(function (track) {
		            return track.map(function (seg) {
		                return seg.coord.concat([1]);
		            });
		        }));
		        myChart.setOption(option = {
		            animation: false,
		            bmap: {
		                center: [114.523618,38.00252],
		                zoom: 14,
		                roam: true
		            },
		            visualMap: {
		                show: false,
		                top: 'top',
		                min: 0,
		                max: 5,
		                seriesIndex: 0,
		                calculable: true,
		                inRange: {
		                    color: ['blue', 'blue', 'green', 'yellow', 'red']
		                }
		            },
		            series: [{
		                type: 'heatmap',
		                coordinateSystem: 'bmap',
		                data: points,
		                pointSize: 5,
		                blurSize: 6
		            }]
		        });
		        // 添加百度地图插件
		        var bmap = myChart.getModel().getComponent('bmap').getBMap();
		        bmap.addControl(new BMap.MapTypeControl());
			}
		});
	})
</script>
</head>
<body>
    <div id="main" style="width:1200px;height:600px;"></div>
</body>
</html>