package com.atp.crm01.common.service;

public interface CustomDataConverter<E,D> {

	D buildDTOFromEntity(E e);
	E buildEntityFromDto(D d);
}
