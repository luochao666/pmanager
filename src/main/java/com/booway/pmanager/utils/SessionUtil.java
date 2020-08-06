package com.booway.pmanager.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



/**
 * 
 * @author 超帅
 *
 */
public class SessionUtil
{

    private static HttpSession session = null;
    
    private static void getSession()
    {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        session = request.getSession();
    }
    
    
    
    public static void setAttribute(String loginName)
    {
        getSession();
        session.setAttribute("loginName", loginName);
    }
    
    
    public static String getAttrbute()
    {
        getSession();
        return (String) session.getAttribute("loginName");
    }
    
    public static void remove()
    {
        getSession();
        session.removeAttribute("loginName");
    }
}
