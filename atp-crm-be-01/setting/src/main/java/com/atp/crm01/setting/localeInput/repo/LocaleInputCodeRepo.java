package com.atp.crm01.setting.localeInput.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atp.crm01.setting.localeInput.entity.LocaleInputCode;
import com.atp.crm01.setting.localeInput.entity.LocaleInputCodePK;

public interface LocaleInputCodeRepo extends JpaRepository<LocaleInputCode, LocaleInputCodePK>{

	@Query("SELECT COALESCE(MAX(lic.id.localeCodeNo),0)+1 FROM LocaleInputCode lic")
	Integer findMaxLocaleCode();
	

	List<LocaleInputCode> findById_LocaleCodeNo(Integer localeCodeNo); //	tim danh sach LocaleInputCode boi 1 localeCode
	

	List<LocaleInputCode> findById_LocaleCodeNoIn(List<Integer> localeCodeNos); //	tim danh sach LocaleInputCode boi nhieu localeCodes


//	List<LocaleInputCode> findById_LocaleCodeAndId_LangCode(Integer localeCode,String langCode); //	tim danh sach LocaleInputCode boi 1 localeCode & 1 langCode
	
}
