package cn.gr.mapper;

import cn.gr.entity.NmBrand;
import cn.gr.entity.NmBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NmBrandMapper {
    int countByExample(NmBrandExample example);

    int deleteByExample(NmBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NmBrand record);

    int insertSelective(NmBrand record);

    List<NmBrand> selectByExample(NmBrandExample example);

    NmBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NmBrand record, @Param("example") NmBrandExample example);

    int updateByExample(@Param("record") NmBrand record, @Param("example") NmBrandExample example);

    int updateByPrimaryKeySelective(NmBrand record);

    int updateByPrimaryKey(NmBrand record);
}