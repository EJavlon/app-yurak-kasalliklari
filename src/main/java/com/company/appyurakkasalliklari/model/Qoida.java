package com.company.appyurakkasalliklari.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Qoida{
	@SerializedName("mulohazalar")
	private List<MulohazalarItem> mulohazalar;

	@SerializedName("xulosa")
	private String xulosa;

	@SerializedName("id")
	private int id;

	@SerializedName("zanjir")
	private Boolean zanjir;

	@SerializedName("xulosaQiymat")
	private String xulosaQiymat;

	public Qoida() {
	}

	public void setMulohazalar(List<MulohazalarItem> mulohazalar){
		this.mulohazalar = mulohazalar;
	}

	public List<MulohazalarItem> getMulohazalar(){
		return mulohazalar;
	}

	public void setXulosa(String xulosa){
		this.xulosa = xulosa;
	}

	public String getXulosa(){
		return xulosa;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId(){
		return id;
	}


	public Boolean getZanjir() {
		return zanjir;
	}

	public void setZanjir(Boolean zanjir) {
		this.zanjir = zanjir;
	}

	public String getXulosaQiymat() {
		return xulosaQiymat;
	}

	public void setXulosaQiymat(String xulosaQiymat) {
		this.xulosaQiymat = xulosaQiymat;
	}

	@Override
	public String toString() {
		return "Qoida{" +
				"mulohazalar=" + mulohazalar +
				", xulosa='" + xulosa + '\'' +
				", id=" + id +
				", zanjir=" + zanjir +
				", xulosaQiymat='" + xulosaQiymat + '\'' +
				'}';
	}
}