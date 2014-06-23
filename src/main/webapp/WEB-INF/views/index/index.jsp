<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
	<link rel="stylesheet"  type="text/css" href="${ctx}/static/styles/index.css">
	<script type="text/javascript" src="${ctx}/static/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx}/static/highcharts/modules/exporting.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12">
        	<p class="alert alert-info"><b>提示:</b> 个人记账功能暂未开通，现仅开放团队记账功能</p>
        </div>
    </div>
    <div class="row">
    	<!-- 今日统计start -->
    	<div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">
                    	<i class="glyphicon glyphicon-dashboard"></i>
                    	今日统计
                    </h3>
                </div>
                <div class="panel-body today-panel">
                	<div class="row">
                    	
                    	<div class="col-md-4 col-sm-6 col-xs-12">
                        	<div class="panel panel-default">
                            	<div class="panel-heading">
                                	<h3 class="panel-title">
                                    	今日备注
                                    </h3>
                                </div>
                                <div class="panel-body">
                                	<p>倪大杰：今天晚上可以吃排骨吗？</p>
                                    <p>黄建：吃豆瓣拌饭</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                        	<div class="panel panel-default">
                            	<div class="panel-heading">
                                	<h3 class="panel-title">
                                    	今日值班
                                    </h3>
                                </div>
                                <div class="panel-body">
                                	<p class="duty-user">
                                    	<span class="label label-primary">杨涛</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                        	<div class="panel panel-default">
                            	<div class="panel-heading">
                                	<div class="panel-title">
                                    	是否录入
                                    </div>
                                </div>
                                <div class="panel-body">
                                	<p class="duty-user">
                                    	<span class="label label-success">已录入</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
     <!-- 今日统计结束 -->
    </div>
    <div class="row">
    	<!-- 今日账单start -->
    	<div class="col-md-5">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">
                    	今日账单
                    </h3>
                </div>
                <div class="panel-body">
                	<table class="table">
                    	<thead>
                        	<tr>
                            	<th>物品名称</th>
                                <th>物品类型</th>
                                <th>物品数量</th>
                      			<th>物品价格</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<tr>
                            	<td>莲花白</td>
                                <td>食品-蔬菜</td>
                                <td>1</td>
                                <td><span class="label label-info">5.5</span></td>
                            </tr>
                            <tr>
                            	<td>白菜</td>
                                <td>食品-蔬菜</td>
                                <td>1</td>
                                <td><span class="label label-info">4.7</span></td>
                            </tr>
                            <tr>
                            	<td>芹菜</td>
                                <td>食品-蔬菜</td>
                                <td>0.5</td>
                                <td><span class="label label-info">2.7</span></td>
                            </tr>
                            <tr>
                            	<td>猪肉</td>
                                <td>食品-肉类</td>
                                <td>0.8</td>
                                <td><span class="label label-info">9.0</span></td>
                            </tr>
                            <tr>
                            	<td colspan="4" style="text-align:right;">总计：<span class="label label-info">32.0</span>元</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- 今日账单end -->
        <div class="col-md-3">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">类型资金统计</h3>
                </div>
                <div class="panel-body">
                	<p>本月截至到今天消费情况如下：</p>
                    <p>用在 <span class="label label-primary">食品</span> 上的消费总计:<span class="label label-info">327.0</span>元</p>
                    <p>用在 <span class="label label-primary">生活用品</span> 上的消费总计:<span class="label label-info">20.0</span>元</p>
                    <p>用在 <span class="label label-primary">厨房用具</span> 上的消费总计:<span class="label label-info">58.6</span>元</p>
                    <hr>
                    <p class="pull-right">总计消费:<span class="label label-info">430.0</span>元</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">资金饼图</h3>
                </div>
                <div class="panel-body" id="money_bin">
                	
                </div>
            </div>
        </div>
    </div>
    <div class="row">
    	<!-- 本月记账预览start -->
    	<div class="col-md-4">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">本月记账预览</h3>
                </div>
                <div class="panel-body" style="padding-top:0;">
                	<table id="calendar">
                    	<caption>2014年6月</caption>
                        <thead>
                        	<tr>
                            	<th scope="col" title="星期一">一</th>
                                <th scope="col" title="星期二">二</th>
                                <th scope="col" title="星期三">三</th>
                                <th scope="col" title="星期四">四</th>
                                <th scope="col" title="星期五">五</th>
                                <th scope="col" title="星期六">六</th>
                                <th scope="col" title="星期日">日</th>
                            </tr>
                        </thead>
                        <tfoot>
                        	<tr>
                            	<td colspan="3" id="calendar_prev">
                                	<a href="javascript:;" title="查看上月记账预览"><< 五月</a>
                                </td>
                                <td class="pad">&nbsp;</td>
                                <td colspan="3" id="calendar_next" class="pad">&nbsp;</td>
                            </tr>
                        </tfoot>
                        <tbody>
                        	<tr>
                            	<td colspan="6" class="pad">&nbsp;</td>
                                <td><a href="javascript:;">1</a></td>
                            <tr>
                            <tr>
                            	<td><a href="javascript:;">2</a></td>
                                <td><a href="javascript:;">3</a></td>
                                <td><a href="javascript:;">4</a></td>
                                <td><a href="javascript:;">5</a></td>
                                <td><a href="javascript:;">6</a></td>
                                <td><a href="javascript:;">7</a></td>
                                <td><a href="javascript:;">8</a></td>
                            </tr>
                            <tr>
                            	<td><a href="javascript:;">9</a></td>
                                <td><a href="javascript:;">10</a></td>
                                <td><a href="javascript:;">11</a></td>
                                <td><a href="javascript:;">12</a></td>
                                <td><a href="javascript:;">13</a></td>
                                <td><a href="javascript:;">14</a></td>
                                <td><a href="javascript:;">15</a></td>
                            </tr>
                            <tr>
                            	<td><a href="javascript:;">16</a></td>
                                <td><a href="javascript:;">17</a></td>
                                <td><a href="javascript:;">18</a></td>
                                <td><a href="javascript:;">19</a></td>
                                <td><a href="javascript:;">20</a></td>
                                <td><a href="javascript:;">21</a></td>
                                <td><a href="javascript:;">22</a></td>
                            </tr>
                            <tr>
                            	<td><a href="javascript:;">23</a></td>
                                <td>24</td>
                                <td>25</td>
                                <td>26</td>
                                <td>27</td>
                                <td>28</td>
                                <td>29</td>
                            </tr>
                            <tr>
                            	<td>30</td>
                                <td class="pad" colspan="6">&nbsp;</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- 本月记账预览end -->
        <div class="col-md-8">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">本月排值表</h3>
                </div>
                <div class="panel-body" style="padding-top:0;">
                	<table id="fullcalendar">
                    	<caption>2014年6月</caption>
                        <thead>
                        	<tr>
                            	<th scope="col" title="星期一">一</th>
                                <th scope="col" title="星期二">二</th>
                                <th scope="col" title="星期三">三</th>
                                <th scope="col" title="星期四">四</th>
                                <th scope="col" title="星期五">五</th>
                                <th scope="col" title="星期六">六</th>
                                <th scope="col" title="星期日">日</th>
                            </tr>
                        </thead>
                        <tfoot>
                        	<tr>
                            	<td colspan="3" id="calendar_prev">
                                	<a href="javascript:;" title="查看上月排值"><< 五月</a>
                                </td>
                                <td class="pad">&nbsp;</td>
                                <td colspan="3" id="calendar_next" class="pad">&nbsp;</td>
                            </tr>
                        </tfoot>
                        <tbody>
                        	<tr>
                            	<td colspan="6" class="pad">&nbsp;</td>
                                <td>1&nbsp;<span class="label label-default">倪大杰</span></td>
                            <tr>
                            <tr>
                            	<td>2&nbsp;<span class="label label-danger">黄建</span></td>
                                <td>3&nbsp;<span class="label label-warning">杨涛</span></td>
                                <td>4&nbsp;<span class="label label-primary">蒋昆花</span></td>
                                <td>5&nbsp;<span class="label label-default">倪大杰</span></td>
                                <td>6&nbsp;<span class="label label-danger">黄建</span></td>
                                <td>7&nbsp;<span class="label label-warning">杨涛</span></td>
                                <td>8&nbsp;<span class="label label-primary">蒋昆花</span></td>
                            </tr>
                            <tr>
                            	<td>9&nbsp;<span class="label label-default">倪大杰</span></td>
                                <td>10&nbsp;<span class="label label-danger">黄建</span></td>
                                <td>11&nbsp;<span class="label label-warning">杨涛</span></td>
                                <td>12&nbsp;<span class="label label-primary">蒋昆花</span></td>
                                <td>13&nbsp;<span class="label label-default">倪大杰</span></td>
                                <td>14&nbsp;<span class="label label-danger">黄建</span></td>
                                <td>15&nbsp;<span class="label label-warning">杨涛</span></td>
                            </tr>
                            <tr>
                            	<td>16&nbsp;<span class="label label-primary">蒋昆花</span></td>
                                <td>17&nbsp;<span class="label label-default">倪大杰</span></td>
                                <td>18&nbsp;<span class="label label-danger">黄建</span></td>
                                <td>19&nbsp;<span class="label label-warning">杨涛</span></td>
                                <td>20&nbsp;<span class="label label-primary">蒋昆花</span></td>
                                <td>21&nbsp;<span class="label label-default">倪大杰</span></td>
                                <td>22&nbsp;<span class="label label-danger">黄建</span></td>
                            </tr>
                            <tr>
                            	<td>23&nbsp;<span class="label label-warning">杨涛</span></td>
                                <td>24&nbsp;<span class="label label-primary">蒋昆花</span></td>
                                <td>25&nbsp;<span class="label label-default">倪大杰</span></td>
                                <td>26&nbsp;<span class="label label-danger">黄建</span></td>
                                <td>27&nbsp;<span class="label label-warning">杨涛</span></td>
                                <td>28&nbsp;<span class="label label-primary">蒋昆花</span></td>
                                <td>29&nbsp;<span class="label label-default">倪大杰</span></td>
                            </tr>
                            <tr>
                            	<td>30&nbsp;<span class="label label-danger">黄建</span></td>
                                <td class="pad" colspan="6">&nbsp;</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$("#money_bin").highcharts({
		chart:{
			plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			height:200
		},
		credits:{
     		enabled:false
		},
		title:{
			text:'资金统计饼图'
		},
		tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}元</b>'
        },
		 plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f}元'
                }
            }
        },
		series: [{
            type: 'pie',
            name: '金额',
            data: [
                ['食品', 327.0],
                ['生活用品', 20.0], 
                ['厨房用具', 58.6],
            ]
        }]
	});
})
</script>
</body>
</html>