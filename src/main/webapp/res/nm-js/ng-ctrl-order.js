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
    $scope.getShoppingCart = function () {
        $http.post('http://'+window.location.host+'/nm/api/getShoppingCart',
            {'sc':$scope.sku}
        ).success(function (data) {
            if(data.success){
                $scope.productList = data.data;
                $scope.refreshPrice();
            }
        });
    }
    $scope.refreshPrice = function () {
        if($scope.productList.length > 0){
            var totalPrice = 0;
            for(var i=0;i<$scope.productList.length;i++){
                totalPrice += parseFloat($scope.productList[i].mySalePrice);
            }
            $scope.totalPrice = totalPrice;
            $scope.totalPriceAll = totalPrice + $scope.freight;
        }
    }
    $scope.addCookie = function (key,value) {
        var expireDate = new Date();
        expireDate.setTime(expireDate.getTime() + 30 * 24 * 60 * 60 * 1000);// cookie 30天有效
        $cookies.putObject(key,value,{expires: new Date(expireDate)});
    }
    $scope.submitOrder = function () {
        if(!$scope.NN($scope.receiverName)){
            alert("请填写收货人姓名！");
            return;
        }
        if(!$scope.NN($scope.receiverAddress)){
            alert("请填写收货人地址！");
            return;
        }
        if($scope.receiverAddress.indexOf("省") < 0 || $scope.receiverAddress.indexOf("市") < 0){
            alert("收货人地址请填写完整的省市信息，例如 **省**市**区/县***小区**！");
            return;
        }
        if(!$scope.NN($scope.receiverPhone)){
            alert("请填写收货人电话号码！");
            return;
        }
        if($scope.totalPrice == 0){
            alert("订单中没有商品，请返回购物车选择！");
            return;
        }
        $("#spinnersModal").modal("show");
        $http.post('http://'+window.location.host+'/nm/api/submitOrder',
            {'name':$scope.receiverName,'address':$scope.receiverAddress,'phone_number':$scope.receiverPhone,'sku':$scope.sku,'total_price':$scope.totalPriceAll}
        ).success(function (data) {
            if(data.success){
                var oid = $cookies.getObject("oid");
                if(oid == undefined || oid == null){
                    var arr = [];
                    arr.push(data.data);
                    $scope.oid = arr;
                }else{
                    oid.unshift(data.data);
                    $scope.oid = oid;
                }
                $scope.addCookie('oid',$scope.oid);
                $cookies.remove('sc');
                location.href = "order-detail.html?oid="+data.data;
            }else{
                alert(data.data);
            }
            $("#spinnersModal").modal("hide");
        });
    }


    $scope.freight = 10;
    $scope.totalPrice = 0;
    $scope.totalPriceAll = 0;
    var sku = $scope.getUrlParamValue("sku");
    if(sku != undefined && sku != null && sku != ""){
        $scope.sku = atob(sku);
        $scope.getShoppingCart();
    }else{
        alert("[Error:sku is empty!]");
        location.href = "index.html";
    }
});