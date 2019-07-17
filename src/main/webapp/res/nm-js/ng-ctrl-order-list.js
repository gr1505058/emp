myAppModule.filter("imgFilter" , function($filter){
    return function(input){
        if(input!=undefined){
            var json = JSON.parse(input);
            // var img = json[0];
            // var idx1 = img.indexOf("?");
            // img = img.substring(0,idx1);
            // var idx2 = img.lastIndexOf("/");
            // var imgName = img.substring(idx2+1);
            // return "/nmImg/75x100/"+imgName;
            return json[0].replace(/75x100/g,"96x96");
        }else{
            return "";
        }
    };
});
myAppModule.filter("statusFilter" , function($filter){
    return function(input){
        if(input!=undefined){
            if(input == 0){return "未支付"}
            if(input == 1){return "已支付"}
            if(input == 2){return "已取消"}
        }else{
            return "";
        }
    };
});
myAppModule.controller('ng-ctrl-nm', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {
    $scope.getUrlParamValue = function(name){
        var search = window.location.search;
        if(search != undefined && search.length > 0){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = search.substr(1).match(reg);
            if(r!=null){
                return  unescape(r[2]);
            }
        }
        return null;
    }
    $scope.NN = function(input){
        if(typeof(input) == 'string'){
            if(input !=undefined && input != null && input.trim() != ''){
                return true;
            }
        }else{
            if(input !=undefined && input != null){
                return true;
            }
        }
        return false;
    }
    $scope.getOrderListFromSearch = function () {
        $scope.isSearch = true;
        if(!$scope.NN($scope.searchData)){
            $scope.isSearch = false;
        }
        $scope.getOrderList();
    }
    $scope.getOrderListAll = function(){
        $scope.isSearch = true;
        $scope.searchData = "#whosYourDaddy";
        $scope.getOrderList();
    }
    $scope.getOrderListFromCookie = function () {
        $scope.isSearch = false;
        $scope.getOrderList();
    }
    $scope.getOrderList = function () {
        $("#spinnersModal").modal("show");
        $http.post('http://'+window.location.host+'/nm/api/getOrderList',
            {'orderId':JSON.stringify($scope.oid),'isSearch':$scope.isSearch,'searchData':$scope.searchData,'page':$scope.page,'numOfEachPage':10}
        ).success(function (data) {
            if(data.success){
                $scope.orderList = data.data;
                $scope.totalPage = data.totalPage;
            }
            $("#spinnersModal").modal("hide");
        });
    }
    $scope.pageChange = function (flag) {
        if(flag == 'first'){
            if($scope.page <= 1){
                return;
            }
            $scope.page = 1;
        }
        if(flag == 'prev'){
            if($scope.page <= 1){
                return;
            }
            $scope.page = $scope.page - 1;
        }
        if(flag == 'input'){
            var inputPage = prompt("请输入要跳转到的页数：",$scope.page);
            console.log(inputPage);
            if(inputPage != undefined && inputPage != null){
                inputPage = inputPage.trim();
                inputPage = inputPage.substring(0,1);
                if(inputPage == '' || isNaN(inputPage)){
                    alert("Error：页数不是正整数！");
                    return;
                }
                var inputPageInt = parseInt(inputPage);
                if(inputPageInt > $scope.totalPage){
                    alert("Error：超出当前最大页数:"+$scope.totalPage+"！");
                    return;
                }
                $scope.page = inputPageInt;
            }
        }
        if(flag == 'next'){
            if($scope.page >= $scope.totalPage){
                return;
            }
            $scope.page = $scope.page + 1;
        }
        if(flag == 'last'){
            if($scope.page >= $scope.totalPage){
                return;
            }
            $scope.page = $scope.totalPage;
        }
        $scope.getOrderList();
    }
    $scope.addCookie = function (data) {
        var expireDate = new Date();
        expireDate.setTime(expireDate.getTime() + 30 * 24 * 60 * 60 * 1000);// cookie 30天有效
        $cookies.putObject('sc',data,{expires: new Date(expireDate)});
    }


    $scope.page = 1;
    $scope.totalPage = 1;
    var secret = $cookies.getObject("whosYourDaddy");
    if(secret != undefined && secret != null && secret != ''){
        $scope.getOrderListAll();
    }else{
        var oid = $cookies.getObject("oid");
        if(oid == undefined || oid == null){
        }else{
            $scope.oid = oid;
            $scope.getOrderListFromCookie();
        }
    }

    var sc = $cookies.getObject("sc");
    if(sc == undefined || sc == null){
        $scope.addCookie([]);
        $scope.sc = [];
    }else{
        $scope.sc = sc;
    }
});