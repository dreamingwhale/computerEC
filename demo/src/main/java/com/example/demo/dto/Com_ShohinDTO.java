package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Com_ShohinDTO {
	
	
	private int shohin_id;
	private String shohin_name;
    private double tanka;
    private Timestamp createdate;
    private Timestamp updatedate;
    private int part;
    private String img;
    private boolean delete_flg;
	
	
    
    public int getShohin_id() {
		return shohin_id;
	}
	public void setShohin_id(int shohin_id) {
		this.shohin_id = shohin_id;
	}
	public String getShohin_name() {
		return shohin_name;
	}
	public void setShohin_name(String shohin_name) {
		this.shohin_name = shohin_name;
	}
	public double getTanka() {
		return tanka;
	}
	public void setTanka(double tanka) {
		this.tanka = tanka;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public int getPart() {
		return part;
	}
	public void setPart(int part) {
		this.part = part;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public boolean isDelete_flg() {
		return delete_flg;
	}
	public void setDelete_flg(boolean delete_flg) {
		this.delete_flg = delete_flg;
	}

}

