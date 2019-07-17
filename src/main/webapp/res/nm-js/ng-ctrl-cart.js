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
myAppModule.controller('ng-ctrl-nm-product', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {
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
    $scope.addCookie = function (data) {
        var expireDate = new Date();
        expireDate.setTime(expireDate.getTime() + 30 * 24 * 60 * 60 * 1000);// cookie 30天有效
        $cookies.putObject('sc',data,{expires: new Date(expireDate)});
    }
    $scope.getShoppingCart = function () {
        $http.post('http://'+window.location.host+'/nm/api/getShoppingCart',
            {'sc':JSON.stringify($scope.sc)}
        ).success(function (data) {
            if(data.success){
                $scope.productList = data.data;
                $scope.refreshPrice();
            }
        });
    }
    $scope.refreshPrice = function () {
        var totalPrice = 0;
        var selectedNum = 0;
        var selectedSku = [];
        for(var i=0;i<$scope.productList.length;i++){
            if($scope.productList[i].check){
                selectedNum ++;
                selectedSku.push($scope.productList[i].nmSku);
                totalPrice += parseFloat($scope.productList[i].mySalePrice);
            }
        }
        $scope.totalPrice = totalPrice + $scope.freight;
        $scope.selectedNum = selectedNum;
        $scope.selectedSku = btoa(JSON.stringify(selectedSku));
        if($scope.selectedNum == 0){
            $scope.totalPrice = 0;
        }
        if($scope.selectedNum == $scope.productList.length){
            $scope.isSelectAll = true;
        }else{
            $scope.isSelectAll = false;
        }
    }
    $scope.check = function (idx) {
        $scope.productList[idx].check=!$scope.productList[idx].check;
        $scope.refreshPrice();
    }
    $scope.selectAll = function () {
        for(var i=0;i<$scope.productList.length;i++){
            if($scope.isSelectAll){
                $scope.productList[i].check = false;
            }else{
                $scope.productList[i].check = true;
            }
        }
        $scope.isSelectAll = !$scope.isSelectAll;
        $scope.refreshPrice();
    }
    $scope.clearCart = function () {
        if(confirm("确认清空购物车？")){
            $scope.addCookie([]);
            $scope.productList = [];
            $scope.refreshPrice();
        }
    }
    $scope.removeCart = function (sku) {
        var sc = [];
        for(var i=0;i<$scope.productList.length;i++){
            if($scope.productList[i].nmSku == sku){
                $scope.productList.splice(i,1);
            }else{
                sc.push($scope.productList[i].nmSku);
            }
        }
        $scope.addCookie(sc);
        $scope.refreshPrice();
    }

    $scope.productList = [];
    $scope.freight = 10;
    $scope.totalPrice = 0;
    $scope.selectedNum = 0;
    $scope.isSelectAll = true;
    var sc = $cookies.getObject("sc");
    if(sc == undefined || sc == null){
        $scope.addCookie([]);
        $scope.sc = [];
    }else{
        $scope.sc = sc;
    }

    $scope.getShoppingCart();
});