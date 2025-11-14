package com.atp.crm01.setting.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component(value = "auditorAware")
public class AuditingAwareImpl implements AuditorAware<String>{

	public Optional<String> getCurrentAuditor() {
		return Optional.of("manhnk2");
	}

}
