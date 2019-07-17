import cn.gr.util.HttpClientUtil;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Created by rong.gao on 2018/8/22.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String trd = HttpClientUtil.doGetFromHeader("http://www.yohobuy.com/erp2goods?sku=3091814","Location");
        int idx1 = trd.lastIndexOf("/")+1;
        int idx2 = trd.lastIndexOf(".");
        System.out.println(trd+"~"+trd.substring(idx1,idx2));
    }
}
