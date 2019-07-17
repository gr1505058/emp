package cn.gr.mapper;

import cn.gr.entity.NmCatalog;
import cn.gr.entity.NmCatalogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NmCatalogMapper {
    int countByExample(NmCatalogExample example);

    int deleteByExample(NmCatalogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NmCatalog record);

    int insertSelective(NmCatalog record);

    List<NmCatalog> selectByExample(NmCatalogExample example);

    NmCatalog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NmCatalog record, @Param("example") NmCatalogExample example);

    int updateByExample(@Param("record") NmCatalog record, @Param("example") NmCatalogExample example);

    int updateByPrimaryKeySelective(NmCatalog record);

    int updateByPrimaryKey(NmCatalog record);
}