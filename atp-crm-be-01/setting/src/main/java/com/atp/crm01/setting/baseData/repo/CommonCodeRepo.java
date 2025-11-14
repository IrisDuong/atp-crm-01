package com.atp.crm01.setting.baseData.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.atp.crm01.setting.baseData.dto.CommonCodeRequestDTO;
import com.atp.crm01.setting.baseData.dto.CommonCodeResponseDTO;
import com.atp.crm01.setting.baseData.entity.CommonCode;

public interface CommonCodeRepo extends JpaRepository<CommonCode, Integer>, JpaSpecificationExecutor<CommonCode>{
//	List<CommonCodeResponseDTO> searchCommonCode(CommonCodeRequestDTO param);
}
