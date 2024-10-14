package com.toolshop.model;

public class Tool {

    //Tool Code - Unique identifier for a tool instance
    //Tool Type - The type of tool. The type also specifies the daily rental charge, and the days for which the
    //  daily rental charge applies.
    //Brand - The brand of the ladder, chain saw or jackhammer.
    String code;
    String type;
    String brand;

    public Tool(String code, String type, String brand){
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
