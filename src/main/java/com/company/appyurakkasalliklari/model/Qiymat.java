package com.company.appyurakkasalliklari.model;

import com.google.gson.annotations.SerializedName;

public class Qiymat{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}