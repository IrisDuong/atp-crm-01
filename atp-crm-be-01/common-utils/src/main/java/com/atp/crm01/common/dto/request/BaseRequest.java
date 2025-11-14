package com.atp.crm01.common.dto.request;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseRequest {
	private int pageSize;
	private int currentPage;
	private int totalElements;

}
