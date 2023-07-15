package com.company.appyurakkasalliklari.model;

import com.google.gson.annotations.SerializedName;

public class Atribut{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private Integer id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}


	public int getId(){
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}