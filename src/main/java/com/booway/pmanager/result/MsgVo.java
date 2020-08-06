package com.booway.pmanager.result;

/**
 * 返回给前端的统一数据类
 * @author JSB-LUOchao
 *
 */
public class MsgVo
{

	private int code;
	
	private int count;
	
	private String msg;
	
	private Object data;
	
	
	
	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public Object getData()
	{
		return data;
	}
	public void setData(Object data)
	{
		this.data = data;
	}
	public MsgVo(int code,int count,String msg,Object data)
	{
		this.code = code;
		this.count = count;
		this.msg = msg;
		this.data = data;
	}
	
	public MsgVo(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}
	
	public MsgVo(int code)
	{
		this.code = code;
	}
	
	public static MsgVo getSuccess(int count, String msg, Object data)
	{
		return new MsgVo(0, count, msg, data);
	}
	
	public static MsgVo getError(String msg)
	{
		return new MsgVo(1,msg);
	}
}
