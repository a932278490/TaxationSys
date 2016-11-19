package com.dsjsys.tools.core.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordUtil {
     private Configuration configuration = null;
     
     public WordUtil(){
    	 configuration = new Configuration();
    	 configuration.setDefaultEncoding("UTF-8");
     }
     
     public void createWord(Map<String, Object> dataMap,String fileName){
    	  
    	 //dataMap.put("deptname", "信息科");
    	 configuration.setClassForTemplateLoading(this.getClass(), "/com/dsjsys/templete"); // FTL文件所存在的位置
    	 try {
			Template template = configuration.getTemplate("dsj.ftl");
			
			File outFile = new File("D:/"+ fileName.trim().replaceAll("/", "") +".doc");
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
			template.process(dataMap, out);
			out.close();
    	 } catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
     }
}
