package org.synjones.springframework.start;  
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
 

import org.synjones.springframework.server.NanoHTTPd;

import java.io.IOException;
import java.util.Date;

/**
 * 继承Application接口后项目启动时会按照执行顺序执行run方法
 * 通过设置Order的value来指定执行的顺序
 */
@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {
	DeivceServerThread myThread; 
    @Override
    public void run(ApplicationArguments args) throws Exception { 

		myThread = new DeivceServerThread();  
        myThread.start(); 
    } 
    
} 

class DeivceServerThread extends Thread {  
    public void run() {    
    	System.out.println("qd"); 
    	  try {
    		  new NanoHTTPd(5002,false); 
    		  new NanoHTTPd(9900,true); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
    }  