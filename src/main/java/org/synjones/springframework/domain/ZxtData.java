package org.synjones.springframework.domain;


public class ZxtData {


    /**
     * ID : 89808276
     * drivers : 32
     * no : 0
     * src : {"name":"临时用户","id":"999999999999999999","type":"身份证","image":" ","record_time":"2020-03-05 14:35:50","TEMP":"36.3"}
     */

    private String ID;
    private int drivers;
    private int no;
    private SrcBean src;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getDrivers() {
        return drivers;
    }

    public void setDrivers(int drivers) {
        this.drivers = drivers;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public SrcBean getSrc() {
        return src;
    }

    public void setSrc(SrcBean src) {
        this.src = src;
    }

    public static class SrcBean {
        /**
         * name : 临时用户
         * id : 999999999999999999
         * type : 身份证
         * image :
         * record_time : 2020-03-05 14:35:50
         * TEMP : 36.3
         */

        private String name;
        private String id;
        private String type;
        private String faceid;
        private String image;
        private String record_time;
        private String TEMP;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRecord_time() {
            return record_time;
        }

        public void setRecord_time(String record_time) {
            this.record_time = record_time;
        }

        public String getTEMP() {
            return TEMP;
        }

        public void setTEMP(String TEMP) {
            this.TEMP = TEMP;
        }
    }
}
