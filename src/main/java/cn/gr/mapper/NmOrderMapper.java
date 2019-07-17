package cn.gr.mapper;

import cn.gr.entity.NmOrder;
import cn.gr.entity.NmOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NmOrderMapper {
    int countByExample(NmOrderExample example);

    int deleteByExample(NmOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NmOrder record);

    int insertSelective(NmOrder record);

    List<NmOrder> selectByExample(NmOrderExample example);

    NmOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NmOrder record, @Param("example") NmOrderExample example);

    int updateByExample(@Param("record") NmOrder record, @Param("example") NmOrderExample example);

    int updateByPrimaryKeySelective(NmOrder record);

    int updateByPrimaryKey(NmOrder record);
}