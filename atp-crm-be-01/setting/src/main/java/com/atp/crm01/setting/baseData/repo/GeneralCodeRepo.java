package com.atp.crm01.setting.baseData.repo;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atp.crm01.setting.baseData.dto.GeneralCodeRequestDTO;
import com.atp.crm01.setting.baseData.dto.GeneralCodeResponseDTO;
import com.atp.crm01.setting.baseData.entity.GeneralCode;
import com.atp.crm01.setting.baseData.entity.GeneralCodeID;

public interface GeneralCodeRepo extends JpaRepository<GeneralCode, GeneralCodeID>{

	@Query("SELECT new com.atp.crm01.setting.baseData.dto.GeneralCodeResponseDTO"
			+ "(G.id.commonCodeNo,G.id.generalCodeNo,G.featureCodeNo,G.codeTypeNo,G.useStatusNo,G.localeCodeNo,G.isTree)"
			+ " FROM GeneralCode G"
			+ " WHERE G.id.commonCodeNo = :commonCodeNo AND G.featureCodeNo = :featureCodeNo AND G.useStatusNo = :useStatusNo"
			+ " ORDER BY G.id.generalCodeNo ASC")
	List<GeneralCodeResponseDTO> getListGeneralByCommonCode(int commonCodeNo,String featureCodeNo,String useStatusNo);
	
	@Query("SELECT COALESCE(MAX(G.id.generalCodeNo),0) FROM GeneralCode G"
			+ " WHERE G.id.commonCodeNo = :commonCodeNo"
			+ " AND G.featureCodeNo = :featureCodeNo"
			+ " AND G.useStatusNo = :useStatusNo")
	Integer findMaxGeneralcode(int commonCodeNo,String featureCodeNo,String useStatusNo);
	
//	@Query("SELECT new com.atp.crm01.setting.baseData.dto.GeneralCodeRequestDTO(G.id.commonCodeNo,G.id.generalCodeNo,G.featureCodeNo,G.isTree) FROM GeneralCode G"
//			+ " WHERE G.id.commonCodeNo = :commonCodeNo"
//			+ " AND G.featureCodeNo = :featureCodeNo"
//			+ " AND G.useStatusNo = :useStatusNo"
//			+ " AND G.id.generalCodeNo IN :generalCodes")
//	List<GeneralCodeRequestDTO> getListExistedGeneralCodeNo(@Param("generalCodes") List<Integer> generalCodes, int commonCodeNo,String featureCodeNo,String useStatusNo);
}
