package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Degree;

public class TestDegree {

	public static void main(String[] args) {
		Degree[] dArray = Degree.values();
		for(Degree d : dArray){
			System.out.println(d);
		}
	}
}
