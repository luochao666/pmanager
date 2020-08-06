package com.booway.pmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booway.pmanager.result.MsgVo;
import com.booway.umanager.service.ItemService;

/**
 * 项目相关接口
 * @author JSB-LUOchao
 *
 */
@RestController
@RequestMapping("/item")
public class ItemController
{
    @Autowired
    private ItemService itemService;
	
    MsgVo msgVo = null;
    /**
     * 这里还需要添加一个查重校验
     * @param itemName 项目名称
     * @param worker 项目负责人
     * @return
     */
	@RequestMapping("/create")
	public MsgVo createItem(@RequestParam(required = false)String itemId ,String itemName,String worker)
	{
	    
	    try
        {
	        if (itemService.checkName(itemName) > 0)
            {
                return new MsgVo(2,0,"该项目/工程名已存在，请重写填写！",null);
            }
            msgVo = itemService.createItem(itemName, worker);
            return msgVo;
        } catch (Exception e)
        {
            e.printStackTrace();
            return MsgVo.getError(e.getMessage());
        }   
	}
	
	/**
	 * 删除项目
	 * @param itemId
	 * @return
	 */
	@RequestMapping("delete")
	public MsgVo deleteItem(String itemId)
	{
	     try
         {
         return itemService.deleteItem(itemId);
         } catch (Exception e)
         {
         e.printStackTrace();
         return MsgVo.getError("删除执行失败");
         }
	}
	
	/**
	 * 编辑项目
	 * @param itemId
	 * @param itemName
	 * @param worker
	 * @return
	 */
	@RequestMapping("edit")
	public MsgVo editItem(String itemId, String itemName, String worker)
	{
	    try
        {
            return itemService.editItem(itemId, itemName, worker);
        } catch (Exception e)
        {
            e.printStackTrace();
            return MsgVo.getError("编辑执行出错");
        }
	}
	
	/**
	 * 页面展示或者条件查询
	 * @param name
	 * @param deptId
	 * @return
	 */
	@RequestMapping("query")
	public MsgVo queryByName(@RequestParam(value = "name",required = false)String name, @RequestParam(value = "deptId",required = false)String deptId)
	{
	    System.out.println("模糊查询参数：" + name);
	    try
        {
            return itemService.queryItemByName(name,deptId);
        } catch (Exception e)
        {
            e.printStackTrace();
            return MsgVo.getError("查询执行出错");
        }
	}
	
	

}
