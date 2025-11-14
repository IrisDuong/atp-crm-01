package com.atp.crm01.setting.baseData.entity;


import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.atp.crm01.setting.auditing.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "SYA_GENERAL_CODE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralCode extends BaseDataCode implements Persistable<GeneralCodePK>{

	@EmbeddedId
	private GeneralCodePK id;
	
	@MapsId("commonCodeNo")
	@ManyToOne
	@JoinColumn(name = "common_code_no")
	private CommonCode ownerCommonCode;

	@Column(name = "parent_general_code_no")
    private String parentGeneralCodeNo;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "parent_general_code_no",referencedColumnName = "general_code_no", insertable = false, updatable = false)
		,@JoinColumn(name = "common_code_no",referencedColumnName = "common_code_no", insertable = false, updatable = false)
	})
	private GeneralCode parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GeneralCode> children;
	
	@Column(name = "is_tree")
	private boolean isTree;

	@Column(name = "tree_level")
	private Integer treeLevel;

	@Transient
	private boolean isNew = true;
	
	@Override
	public boolean isNew() {
		return isNew;
	}

	@PrePersist
	public void beforeSave() {
		this.isNew = false;
	}
}
