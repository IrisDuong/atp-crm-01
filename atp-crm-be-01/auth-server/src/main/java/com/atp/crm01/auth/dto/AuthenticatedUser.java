package com.atp.crm01.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticatedUser {

	private String sub;
	private String name;
	private String email;
	private String phoneNo;
	private String avatar;
}
