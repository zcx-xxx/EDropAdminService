<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script type="text/javascript">
	$(function() {
// 		var myChart = echarts.init($("#main"));
		$.ajax({
			type: "POST",
			url: "user_operation_statistics/get_user_dau_data",
			data: "",
			dataType: "json",
			success: function(msg){
				data = msg;
				var myChart = echarts.init(document.getElementById('main'));
				myChart.setOption(option = {
		            title: {
		                text: 'DAU'
		            },
		            tooltip: {
		                trigger: 'axis'
		            },
		            xAxis: {
		                data: data.map(function (item) {
		                    return item[0];
		                })
		            },
		            yAxis: {
		                splitLine: {
		                    show: false
		                }
		            },
		            toolbox: {
		                left: 'center',
		                feature: {
		                    dataZoom: {
		                        yAxisIndex: 'none'
		                    },
		                    restore: {},
		                    saveAsImage: {}
		                }
		            },
		            dataZoom: [{
		                startValue: '2020-01-01'
		            }, {
		                type: 'inside'
		            }],
		            visualMap: {
		                top: 10,
		                right: 10,
		                pieces: [{
		                    gt: 0,
		                    lte: 50,
		                    color: '#096'
		                }, {
		                    gt: 50,
		                    lte: 100,
		                    color: '#ffde33'
		                }, {
		                    gt: 100,
		                    lte: 150,
		                    color: '#ff9933'
		                }, {
		                    gt: 150,
		                    lte: 200,
		                    color: '#cc0033'
		                }, {
		                    gt: 200,
		                    lte: 300,
		                    color: '#660099'
		                }, {
		                    gt: 300,
		                    color: '#7e0023'
		                }],
		                outOfRange: {
		                    color: '#999'
		                }
		            },
		            series: {
		                name: 'DAU',
		                type: 'line',
		                data: data.map(function (item) {
		                    return item[1];
		                }),
		                markLine: {
		                    silent: true,
		                    data: [{
		                        yAxis: 50
		                    }, {
		                        yAxis: 100
		                    }, {
		                        yAxis: 150
		                    }, {
		                        yAxis: 200
		                    }, {
		                        yAxis: 300
		                    }]
		                }
		            }
		        });
			}
		});
	})
</script>
</head>
<body>
    <div id="main" style="width: 1000px;height:500px;"></div>
</body>
</html>