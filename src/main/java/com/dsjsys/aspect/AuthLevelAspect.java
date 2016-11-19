package com.dsjsys.aspect;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.dsjsys.annotation.AuthLevel;
import com.dsjsys.exception.NoAuthException;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.tools.core.util.SysContext;

/**  
 * 切点类  
 * @author lvzheng  
 * @since 2016-10-18 Pm 20:35  
 * @version 1.0  
 */    
@Aspect    
@Component 
public class AuthLevelAspect {

	@Pointcut("@annotation(com.dsjsys.annotation.AuthLevel)")    
    public  void serviceAspect() {}   
	
    /**  
     * 前置通知 用于拦截用户的权限操作  
     *  
     * @param joinPoint 切点  
     * @throws Throwable 
     */    
    @Around("serviceAspect()")  
    public  Object doAround(ProceedingJoinPoint jp) throws Throwable{
    	HttpSession session = SysContext.getSession();  
    	HttpServletRequest  request = SysContext.getRequest();  
    	HttpServletResponse  response = SysContext.getResponse();   
        //读取session中的用户    
        Stuff stuff = (Stuff) session.getAttribute("loginStuff");
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;    
        Method targetMethod = methodSignature.getMethod();  
        //对于有permission注解的方法进行权限验证
        boolean b = targetMethod.isAnnotationPresent(AuthLevel.class);
        if (b) {//带有权限注解的方法
            AuthLevel authlevel = targetMethod.getAnnotation(AuthLevel.class);
            System.out.println(stuff.getRole().getLevel());
	            if(stuff.getRole().getLevel()>=authlevel.level()){
	            	System.out.println("执行权限方法");
	            	Object object;
					object = jp.proceed();
	            	return object;
	            }else{
	            	System.out.println("权限不够");
	            }
        }
        return "error/auth";
    }
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static int getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        int level = 0;    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    level = method.getAnnotation(AuthLevel.class).level();    
                     break;    
                }    
            }    
        }    
         return level;    
    }    
}
