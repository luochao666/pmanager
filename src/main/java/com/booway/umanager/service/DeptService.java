package com.booway.umanager.service;

import java.util.List;





import javax.jws.WebService;

import com.booway.pmanager.entity.Dept;
/**
 * 
 * @author 超帅
 *
 */
@WebService
public interface DeptService
{
    List<Dept> getList();
}
