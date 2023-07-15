package com.company.appyurakkasalliklari.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AtributQiymat{

	@SerializedName("id")
	private Integer id;

	@SerializedName("atribut")
	private String atribut;

	@SerializedName("qiymat")
	private List<String> qiymat;

	public AtributQiymat() {
	}

	public AtributQiymat(String atribut, List<String> qiymat) {
		this.atribut = atribut;
		this.qiymat = qiymat;
	}

	public void setAtribut(String atribut){
		this.atribut = atribut;
	}

	public String getAtribut(){
		return atribut;
	}

	public void setQiymat(List<String> qiymat){
		this.qiymat = qiymat;
	}

	public List<String> getQiymat(){
		return qiymat;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}