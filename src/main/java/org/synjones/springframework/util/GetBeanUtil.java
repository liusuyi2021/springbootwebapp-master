package org.synjones.springframework.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.synjones.springframework.cache.SysCache;
import org.synjones.springframework.domain.Product;
import org.synjones.springframework.domain.ZxtData; 
import org.synjones.springframework.services.ProductService;
import org.synjones.springframework.services.ProductServiceImpl;

@Component 
public class GetBeanUtil {
	 @Autowired
	 private static ProductService productService;  
	 @PostConstruct
	 public void init() {
		 System.out.println("init"); 

	 }  
	    @Autowired
	    public void setProductService(ProductService productService) {
	        this.productService = productService;
	    }
	    
	 public static void newProduct(ZxtData zxtData){
		if(zxtData==null){
			System.out.println("null");
       	 	return;
		}
         if (zxtData.getSrc()==null||org.apache.commons.lang3.StringUtils.trimToEmpty(zxtData.getSrc().getTEMP()).equals("")) {
        	 System.out.println("丢弃"+zxtData.getSrc().getTEMP());
        	 return;
		}
		 Product mug = new Product(); 
		 if (!StringUtils.isEmpty(zxtData.getSrc().getFaceid())) // 图像数据为空
	     {
			 mug.setFaceid(zxtData.getSrc().getFaceid().split("_")[0]);
			 mug.setName(UnicodeUtil.unicodeToCn(zxtData.getSrc().getFaceid().split("_")[1]));
		 }else{

			 mug.setName(zxtData.getSrc().getName());
		 }
		  
		 SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");
		 String time=formatter.format(new Date());
		  //  System.out.println(time);
		    
	     boolean b =Base64Util.Base64ToImage(zxtData.getSrc().getImage().replaceAll("\n", "").replaceAll("\r", ""),"D://upload/pic"+time+".jpg"); 
	      //  System.out.println(b); 
		 mug.setImage_Url("/pic"+time+".jpg");
         mug.setTemp(Float.valueOf(zxtData.getSrc().getTEMP())); 
         mug.setRecord_time(zxtData.getSrc().getRecord_time());
         mug.setDeviceNo(zxtData.getID());
         SysCache.getInstance().setZx(mug);
         productService.saveProduct(mug);
	 } 
}
