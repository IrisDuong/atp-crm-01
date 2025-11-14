package com.atp.crm01.common.utils;

public enum ServicePort {
	
	DISCOVERY("9999"),
	API_GATEWAY("8080"),
	AUTH_SERVER("9000"),
	PRODUCT_MGT("9091");
	
	private final String servicePort;
	
	ServicePort(String servicePort){
		this.servicePort = servicePort;
	}
	
}

