package com.atp.crm01.setting.baseData.entity;


import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.atp.crm01.common.utils.CommonUtils;
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
public class GeneralCode extends BaseDataCode implements Persistable<GeneralCodeID>{

	@EmbeddedId
	private GeneralCodeID id;
	
	@MapsId("commonCodeNo")
	@ManyToOne
	@JoinColumn(name = "common_code_no")
	private CommonCode ownerCommonCode;
	
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<GeneralCode> children;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "parent_general_code_no"),@JoinColumn(name = "parent_common_code_no")})
	@ToString.Exclude
	private GeneralCode parent;
	
	@Column(name = "is_tree")
	private boolean isTree;

	
	@Column(name = "tree_level")
	private int treeLevel;
	
	@Transient
	private boolean isNew = true;
	
	public void beforeSave() {
		this.isNew = false;
	}
	
	private void addChild(GeneralCode child) {
		if(this.isTree && !CommonUtils.isEmptyData(this.children)) {
			this.children.add(child);
		}
	}
}
