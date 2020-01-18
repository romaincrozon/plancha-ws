package com.theia.services;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;

public class SardineService {

	private static Sardine sardine;
	
	public static Sardine getInstance() {
		if (sardine == null) {
			sardine = SardineFactory.begin("crozonr", "oee120!R");
		}
		return sardine;
	}
}
