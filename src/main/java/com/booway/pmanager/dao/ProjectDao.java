package com.booway.pmanager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 工程操作接口
 * @author 超帅
 *
 */
@Repository
public interface ProjectDao
{

    int editProject(@Param("itemId")String itemId, @Param("itemName") String itemName);
    
}
