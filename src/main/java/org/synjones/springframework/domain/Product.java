package org.synjones.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 

    private String name;
    private String faceid;
    private Float temp;
    private String record_time;
    private String deviceNo;  
    private String image_Url;  
 
    public String getFaceid() {
		return faceid;
	}

	public void setFaceid(String faceid) {
		this.faceid = faceid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 

	public Float getTemp() {
		return temp;
	}

	public void setTemp(Float temp) {
		this.temp = temp;
	}

	public String getRecord_time() {
		return record_time;
	}

	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getImage_Url() {
		return image_Url;
	}

	public void setImage_Url(String image_Url) {
		this.image_Url = image_Url;
	}
 
}
