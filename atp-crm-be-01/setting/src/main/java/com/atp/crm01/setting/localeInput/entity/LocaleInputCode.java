package com.atp.crm01.setting.localeInput.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.atp.crm01.setting.auditing.BaseEntity;
import com.atp.crm01.setting.baseData.entity.BaseDataCode;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "SYA_LOCALE_INPUT_CODE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LocaleInputCode extends BaseEntity{

	@EmbeddedId
	private LocaleInputCodePK id;

	@Column(name = "code_name", columnDefinition = "NVARCHAR(255)")
	private String codeName;
	
}
