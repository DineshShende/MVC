package com.projectx.mvc.domain.quickregister;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class QuickRegisterMVCDTO {

	private QuickRegisterDTO quickRegisterDTO;
	
	public QuickRegisterMVCDTO() {
		
	}

	public QuickRegisterMVCDTO(QuickRegisterDTO quickRegisterDTO) {
		super();
		this.quickRegisterDTO = quickRegisterDTO;
	}

	public QuickRegisterDTO getQuickRegisterDTO() {
		return quickRegisterDTO;
	}

	public void setQuickRegisterDTO(QuickRegisterDTO quickRegisterDTO) {
		this.quickRegisterDTO = quickRegisterDTO;
	}

	@Override
	public String toString() {
		return "QuickRegisterMVCDTO [quickRegisterDTO=" + quickRegisterDTO
				+ "]";
	}

	
	
	
}
