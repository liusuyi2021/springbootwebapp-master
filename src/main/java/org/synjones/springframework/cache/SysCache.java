package org.synjones.springframework.cache;
 
import org.synjones.springframework.domain.Product;
import org.synjones.springframework.domain.ZxtData;

/**
 * 
*     
*
 */
public class SysCache {
 
	//private static SysCache cache = null; 

	private Product zx;
	private String zxStatus="0";

    private static class SysCacheMyHandler{  
        private static SysCache instance = new SysCache();  
    }   
      
    private SysCache(){}  
       
    public static SysCache getInstance() {   
    	//System.out.println(SysCacheMyHandler.instance);
        return SysCacheMyHandler.instance;  
    }  

	public Product getZx() {
		if (zx!=null&&zxStatus.equals("0")) { 
			zxStatus="1";
		}else{
			return null;
		}
		return zx;
	}

	public void setZx(Product zx) {
		zxStatus="0";
		this.zx = zx;
	}   
       
 
	
}
