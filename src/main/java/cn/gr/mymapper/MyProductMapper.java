package cn.gr.mymapper;

import cn.gr.entity.NmOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyProductMapper {
    List<Integer> getBrandId(@Param("msort") Integer msort,@Param("misort") Integer misort,@Param("size") Integer size,@Param("isFlaw") Byte isFlaw);

    List<Integer> getSizeId(@Param("msort") Integer msort,@Param("misort") Integer misort,@Param("brand") Integer brand,@Param("isFlaw") Byte isFlaw);

    int insertOrderGetId(NmOrder nmOrder);

}