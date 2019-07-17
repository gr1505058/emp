myAppModule.filter("imgFilter" , function($filter){
    return function(input){
        if(input!=undefined){
            // var img = input;
            // if(flag=='detail'){
            //     var idx2 = img.lastIndexOf("/");
            //     var imgName = img.substring(idx2+1);
            //     return "/nmImg/original/"+imgName;
            // }else{
            //     var idx1 = img.indexOf("?");
            //     img = img.substring(0,idx1);
            //     var idx2 = img.lastIndexOf("/");
            //     var imgName = img.substring(idx2+1);
            //     var imgSize = '420x560';
            //     if(flag == 'small'){
            //         imgSize = '75x100';
            //     }
            //     return "/nmImg/"+imgSize+"/"+imgName;
            // }
            return input.replace(/75x100/g,"420x560");
        }else{
            return "";
        }
    };
});
myAppModule.controller('ng-ctrl-nm-product', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {
    $scope.getProductBySku = function () {
        $http.post('http://'+window.location.host+'/nm/api/getProductBySku',
            {'nmSku':$scope.nmSku}
        ).success(function (data) {
            if(data.success){
                $scope.product = data.data;
                $scope.productMainImgs = JSON.parse(data.data.imgMain);
                $scope.productDetails = data.data.detailObject;
            }
        });
    }
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
    $scope.add2Cart = function () {
        var sc = $cookies.getObject("sc");
        var hasSku = false;
        for(var i=0;i<sc.length;i++){
            if(sc[i] == $scope.nmSku){
                hasSku = true;
                break;
            }
        }
        if(!hasSku){
            sc.push($scope.nmSku);
            $scope.addCookie(sc);
            $('#animation_box').removeClass('wobble');
            $scope.sc = sc;
            $('#animation_box').addClass('wobble');
            $scope.addInfo = {'info':'商品已成功添加到购物车！','style':{"color":"#1ab394"},'hasSku':hasSku};
        }else{
            $scope.addInfo = {'info':'商品已在购物车内！','style':{"color":"orange"},'hasSku':hasSku};
        }
        $scope.add2CartSuccess = true;
        $('#animation_box_1').removeClass('rotateIn');
        $('#animation_box_1').addClass('rotateIn');
        $('#animation_box_2').removeClass('fadeInUp');
        $('#animation_box_2').addClass('fadeInUp');

    }

    var nmSku = $scope.getUrlParamValue("sku");
    if(nmSku != null){
        $scope.nmSku = parseInt(nmSku);
    }else{
        alert("No sku error!");
        location.href = window.location.origin + window.location.pathname;
    }
    $scope.getProductBySku();

    var sc = $cookies.getObject("sc");
    if(sc == undefined || sc == null){
        $scope.addCookie([]);
        $scope.sc = [];
    }else{
        $scope.sc = sc;
    }
});