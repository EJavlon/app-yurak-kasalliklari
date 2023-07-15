package com.company.appyurakkasalliklari.model;

import com.google.gson.annotations.SerializedName;

public class Kasallik{

	@SerializedName("help")
	private String help;

	@SerializedName("name")
	private String name;

	@SerializedName("about")
	private String about;

	public void setHelp(String help){
		this.help = help;
	}

	public String getHelp(){
		return help;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAbout(String about){
		this.about = about;
	}

	public String getAbout(){
		return about;
	}
}