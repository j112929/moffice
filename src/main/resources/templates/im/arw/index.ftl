<html ng-app="ask" ng-cloak>
<head>
    <link href="../../static/css/bootstrap/3.3.4/bootstrap.css" rel="stylesheet">
    <link href="../../static/css/angular-growl/0.4.0/angular-growl.min.css" rel="stylesheet">
    <link href="../../static/css/console.css" rel="stylesheet">
    <link href="../../static/css/arw.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div style="padding-top: 20px;">
        <div ng-controller="askController as ctrl">
            <h2 ng-cloak>
                问一问自动回复关键字
                <div class="pull-right">
                    <button class="btn btn-info" ng-class="{disabled:config.toolbar=='list'}" ng-click="config.toolbar='list'">搜索</button>
                    <button class="btn btn-success" ng-class="{disabled:config.toolbar=='add'}" ng-click="config.toolbar='add'">新增</button>
                    <button class="btn btn-danger" ng-click="ctrl.reloadRedis()">刷新Redis</button>
                </div>
            </h2>
            <hr/>
            <div class="form-inline" data-ng-if="config.toolbar=='add'">
                <div class="form-group">
                    <select ng-model="entity.mall" class="form-control" ng-options="option.text for option in config.malls track by option.value">f for f in formats
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" ng-model="entity.word" class="form-control input-sm" placeholder="关键字"/>
                </div>
                <div class="form-group">
                    <input type="text" ng-model="entity.weight" class="form-control input-sm" placeholder="权重 1-10 数字" size="10"/>
                </div>
                <div class="form-group">
                    <input type="text" ng-model="entity.reply" class="form-control input-sm" placeholder="回复内容"/>
                </div>
                <button class="btn btn-success" data-ng-click="ctrl.create()">添加</button>
            </div>
            <div class="form-inline" data-ng-if="config.toolbar=='list'">
                <div class="form-group">
                    <input type="text" ng-model="search.word" class="form-control input-sm" placeholder="请输入关键字">
                </div>
                <button class="btn btn-info" type="button" data-ng-click="ctrl.search()">搜索!</button>
            </div>
            <hr/>
            <div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                    <#--<td>编号</td>-->
                        <td>所属MALL</td>
                        <td>关键字</td>
                        <td>权重</td>
                        <td>回复内容</td>
                        <td style="width: 200px;">创建日期</td>
                        <td style="width: 150px;">操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="result in results">
                    <#--<td>{{result.id}}</td>-->
                        <td>{{ctrl.getMallName(result.mall)}}</td>
                        <td>{{result.word}}</td>
                        <td>{{result.weight}}</td>
                        <td>{{result.reply}}</td>
                        <td>{{result.create|date: 'yyyy-MM-dd hh:mm:ss'}}</td>
                        <td>
                            <button class="btn btn-link" ng-click="ctrl.drop(result.id)">删除</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div uib-pagination boundary-links="true"
                     total-items="config.pager.total"
                     items-per-page="config.pager.size"
                     ng-model="config.pager.index"
                     class="pagination-sm pull-right"
                     previous-text="&lsaquo;"
                     next-text="&rsaquo;"
                     first-text="&laquo;"
                     last-text="&raquo;"></div>
            </div>
        </div>

    </div>
</div>
<div growl></div>
<script type="text/javascript" src="../../static/js/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="../../static/js/angular/1.4.8/angular.min.js"></script>
<script type="text/javascript" src="../../static/js/angular/1.4.8/angular-sanitize.min.js"></script>
<script type="text/javascript" src="../../static/js/angular/1.4.8/angular-animate.min.js"></script>
<script type="text/javascript" src="../../static/js/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.min.js"></script>
<script type="text/javascript" src="../../static/js/angular-growl/v0.4.0/angular-growl.min.js"></script>
<script type="text/javascript" src="../../static/js/ask/arw.js"></script>
</body>
</html>