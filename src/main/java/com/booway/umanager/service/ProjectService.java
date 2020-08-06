package com.booway.umanager.service;

import com.booway.pmanager.result.MsgVo;

/**
 * 工程接口
 * @author 超帅
 *
 */
public interface ProjectService
{
    /**
     * 添加工程 
     * @param parentId
     * @param worker
     * @param itemName
     * @return
     */
    MsgVo addProject(String parentId,String worker,String itemName) throws Exception;
    
    /**
     * 编辑工程
     * @param itemId
     * @param itemName
     * @return
     */
    MsgVo editProject(String itemId,String itemName) throws Exception;
    
    /**
     * 删除工程
     * @param itemId
     * @return
     */
    MsgVo deleteProject(String itemId) throws Exception;
}
