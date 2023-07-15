package com.company.appyurakkasalliklari.model;

import com.google.gson.annotations.SerializedName;

public class MulohazalarItem{

	@SerializedName("atribut")
	private String atribut;

	@SerializedName("qiymat")
	private String qiymat;

	public void setAtribut(String atribut){
		this.atribut = atribut;
	}

	public String getAtribut(){
		return atribut;
	}

	public void setQiymat(String qiymat){
		this.qiymat = qiymat;
	}

	public String getQiymat(){
		return qiymat;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;

		if (!(obj instanceof MulohazalarItem)) return false;

		MulohazalarItem obj1 = (MulohazalarItem) obj;

		return atribut.equals(obj1.atribut) && qiymat.equals(obj1.getQiymat());
	}

	@Override
	public String toString() {
		return "MulohazalarItem{" +
				"atribut='" + atribut + '\'' +
				", qiymat='" + qiymat + '\'' +
				'}';
	}

}