package com.dsjsys.annotation;

import java.lang.annotation.*;

/**  
 *自定义注解 权限拦截  
 */    
    
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface AuthLevel {    
    
    int level()  default 1;    
    
    
} 