package com.atguigu.cpes.bean;

/**
 *  @author 
 */
public class Certtype {
    private String acctype;
    private Integer certid;
    private Integer id;

    public void setAcctype(String acctype){
        this.acctype = acctype;
    }
    public String getAcctype(){
        return this.acctype;
    }

    public void setCertid(Integer certid){
        this.certid = certid;
    }
    public Integer getCertid(){
        return this.certid;
    }

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }

}