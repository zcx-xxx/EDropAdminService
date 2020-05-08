<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>>" />
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
	var str = "[[]]";
	$(function() {
		data = JSON.parse("[[]]");
		var myChart = echarts.init(document.getElementById('main'));
		// 先加载空地图
		showBlankMap(myChart);
		// 分批加载地图数据		
		loadingMap(0, 1000, myChart);
	})
	
	function loadingMap(pageNum, pageSize, myChart) {
		// 异步加载数据
		$.ajax({
			type: "POST",
			url: "user_place_order_address_info/get_order_address_info_by_page",
			data: "begin=" + (pageNum * pageSize) + "&count=" + pageSize,
			dataType: "text",
			success: function(msg){
				if (msg.length > 0) {
					str = str.slice(0, str.length - 2);
					if (str.length > 10) {
						str = str + ",";
					}
					str = str + msg.slice(2);
				} else {
					return;
				}
				data = JSON.parse(str);
				// 隐藏加载动画
				myChart.hideLoading(); 
				
				var points = [].concat.apply([], data.map(function (track) {
		            return track.map(function (seg) {
		                return seg.coord.concat([1]);
		            });
		        }));
		        myChart.setOption(option = {
		            animation: true,
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
		        
		        loadingMap(pageNum + 1, pageSize, myChart);
			}
		});
	}
	
	function showBlankMap(myChart) {		
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
        // 显示加载动画
        myChart.showLoading();
	}
</script>
</head>
<body>
    <div id="main" style="width:1200px;height:600px;"></div>
</body>
</html>