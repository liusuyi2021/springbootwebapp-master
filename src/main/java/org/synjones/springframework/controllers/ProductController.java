package org.synjones.springframework.controllers;

import org.synjones.springframework.cache.SysCache;
import org.synjones.springframework.domain.Product;
import org.synjones.springframework.domain.ZxtData;
import org.synjones.springframework.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning rpoducts:");
        return "products";
    }
 
    @RequestMapping(value ="/getproducts")
    public void list(HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8"); 
    	Iterable<Product> productlist=productService.listAllProducts(); 
	     PrintWriter printWriter1 = response.getWriter();

	     printWriter1.write(JSONArray.toJSONString(productlist));
	     printWriter1.close();
    }
 
    
    @RequestMapping(value ="/getproductsPage1")
    public void showSortPage(HttpServletResponse response,Integer page, Integer size, String name, String wendu, String type) throws IOException{
    	response.setContentType("text/html;charset=utf-8");  
    	
    	
	    PrintWriter printWriter1 = response.getWriter(); 
	    String msg = "{\"data\":%s,\"recordsTotalPages\":%s,\"recordsTotal\":%s}";
	    Map<String,Object> returnMap=productService.findAllByname(name,wendu, page, size);
	    
		List<Product> list = (List<Product>) returnMap.get("list");
		Long totalRecord = (Long) returnMap.get("totalRecord");
		
	   // System.out.println("findAllByname:"+list.size()); 
	    
		msg = String.format(msg, JSONArray.toJSONString(list),getPage(size,totalRecord.intValue()), totalRecord);
		
 	    printWriter1.write(msg);
	    printWriter1.close(); 
    } 

    
    @RequestMapping(value ="/excelDownload")
    public void excelDownload(HttpServletResponse response,String name, String wendu) throws IOException{
         Map<String,Object> returnMap=productService.findAllByname(name,wendu, 1, 9999999);

     	Random random = new Random(); 
         int s = random.nextInt(999999)%(999999-100000+1) + 100000;
		List<Product> list = (List<Product>) returnMap.get("list");
		Long totalRecord = (Long) returnMap.get("totalRecord");
		 
		String[] header = {"序号","姓名", "工号", "温度", "时间"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("温度检测记录表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text); 
            cell.setCellStyle(headerStyle);
        }

        //获取所有的employee 
        for(int i=0;i<list.size();i++){
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
            //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(list.get(i).getId().toString()));
            //第二列创建并赋值
            row1.createCell(1).setCellValue(new HSSFRichTextString(list.get(i).getName()));
            row1.createCell(2).setCellValue(new HSSFRichTextString(list.get(i).getFaceid()));
            //第三列创建并赋值
            row1.createCell(3).setCellValue(new HSSFRichTextString(list.get(i).getTemp().toString()));
            //第4列创建并赋值 
            row1.createCell(4).setCellValue(new HSSFRichTextString(list.get(i).getRecord_time()));  
            
        }


        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=employee"+s+".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());

    } 
    
    
    private Integer getPage(Integer pageSize,Integer totalRecord){  
    	
        if(totalRecord%pageSize==0){  
            return totalRecord/pageSize;  
        }else{  
        	return totalRecord/pageSize+1;  
        }  
    }  
    @RequestMapping(value ="/getNewData")
    public void getNewData(HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8"); 

    	Product zxtData=SysCache.getInstance().getZx(); 
	     PrintWriter printWriter1 = response.getWriter(); 
	     printWriter1.write(JSONObject.toJSONString(zxtData));
	     printWriter1.close();
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product){

        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

}
