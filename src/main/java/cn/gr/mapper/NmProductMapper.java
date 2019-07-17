package cn.gr.mapper;

import cn.gr.entity.NmProduct;
import cn.gr.entity.NmProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NmProductMapper {
    int countByExample(NmProductExample example);

    int deleteByExample(NmProductExample example);

    int deleteByPrimaryKey(Integer nmSku);

    int insert(NmProduct record);

    int insertSelective(NmProduct record);

    List<NmProduct> selectByExample(NmProductExample example);

    NmProduct selectByPrimaryKey(Integer nmSku);

    int updateByExampleSelective(@Param("record") NmProduct record, @Param("example") NmProductExample example);

    int updateByExample(@Param("record") NmProduct record, @Param("example") NmProductExample example);

    int updateByPrimaryKeySelective(NmProduct record);

    int updateByPrimaryKey(NmProduct record);
}