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
            return "";
        }else{
            return "";
        }
    };
});
myAppModule.filter("secretFilter" , function($filter){
    return function(input,flag,isSeeFull){
        if(input!=undefined){
            if(isSeeFull){
                return input;
            }else{
                if(flag == 'name'){
                    var name = input.trim();
                    var length = name.length;
                    return "*" + name.substring(length-1);
                }
                if(flag == 'address'){
                    var address = input.trim();
                    var length = address.length;
                    var idx = address.indexOf("市");
                    return address.substring(0,idx+1) + "****" + address.substring(length-2);
                }
                if(flag == 'phone'){
                    var phone = input.trim();
                    var str1 = phone.substring(0,3);
                    var str2 = phone.substring(7);
                    return str1+"****"+str2;
                }
                return "";
            }
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
    $scope.getOrderByOid = function () {
        $http.post('http://'+window.location.host+'/nm/api/getOrderByOid',
            {'orderId':$scope.oid}
        ).success(function (data) {
            if(data.success){
                $scope.order = data.data;
                $scope.expressInfoList = data.expressInfoList;
                $scope.orderStatus = data.data.status;
                $scope.orderExpressNumber = data.data.expressNumber;
                for (var i=0;i<data.expressInfoList.length;i++){
                    $scope.orderExpressInfo += data.expressInfoList[i] + "\n";
                }
                $scope.getShoppingCart(data.data.sku);
                $scope.refreshPrice();
            }
        });
    }
    $scope.getShoppingCart = function (sku) {
        $("#spinnersModal").modal("show");
        $http.post('http://'+window.location.host+'/nm/api/getShoppingCart',
            {'sc':sku}
        ).success(function (data) {
            if(data.success){
                $scope.productList = data.data;
                $scope.refreshPrice();
            }
            $("#spinnersModal").modal("hide");
        });
    }
    $scope.refreshPrice = function () {
        if($scope.productList != undefined && $scope.productList.length > 0){
            var totalPrice = 0;
            for(var i=0;i<$scope.productList.length;i++){
                totalPrice += parseFloat($scope.productList[i].mySalePrice);
            }
            $scope.totalPrice = totalPrice;
            $scope.totalPriceAll = totalPrice + $scope.freight;
        }
    }
    $scope.updateExpress = function () {
        if(!$scope.NN($scope.orderStatus)){
            alert("订单状态不能为空！")
            return;
        }
        if($scope.orderStatus != '0' && $scope.orderStatus != '1' && $scope.orderStatus != '2'){
            alert("订单状态只能为0、1、2！")
            return;
        }
        if(!$scope.NN($scope.orderExpressNumber)){
            $scope.orderExpressNumber = "";
        }

        $("#spinnersModal").modal("show");
        $http.post('http://'+window.location.host+'/nm/api/updateExpress',
            {'orderId':$scope.oid,'orderStatus':$scope.orderStatus,'expressNumber':$scope.orderExpressNumber,'expressInfo':$scope.orderExpressInfo}
        ).success(function (data) {
            if(data.success){
                $scope.order = data.data;
                $scope.expressInfoList = data.expressInfoList;
                $("#myModal_express").modal("hide");
            }else{
                alert(data.data);
            }
            $("#spinnersModal").modal("hide");
        });
    }

    $scope.freight = 10;
    $scope.totalPrice = 0;
    $scope.totalPriceAll = 0;
    $scope.orderExpressInfo = "";
    $scope.isSeeFull = false;
    var oid = $scope.getUrlParamValue("oid");
    if(oid != undefined && oid != null && oid != ""){
        $scope.oid = oid;
        $scope.getOrderByOid();
    }else{
        alert("[Error:oid is empty!]");
        location.href = "index.html";
    }
});