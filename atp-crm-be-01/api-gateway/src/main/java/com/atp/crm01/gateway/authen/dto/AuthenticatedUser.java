package com.atp.crm01.gateway.authen.dto;

public record AuthenticatedUser(String sub,String name,String email,String phoneNo,String avatar) {

}
