package cn.gr.mapper;

import cn.gr.entity.NmSize;
import cn.gr.entity.NmSizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NmSizeMapper {
    int countByExample(NmSizeExample example);

    int deleteByExample(NmSizeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NmSize record);

    int insertSelective(NmSize record);

    List<NmSize> selectByExample(NmSizeExample example);

    NmSize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NmSize record, @Param("example") NmSizeExample example);

    int updateByExample(@Param("record") NmSize record, @Param("example") NmSizeExample example);

    int updateByPrimaryKeySelective(NmSize record);

    int updateByPrimaryKey(NmSize record);
}