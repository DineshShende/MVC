package com.projectx.mvc.servicefixtures.quickregister;

import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.*;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStringStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;
@Component
@Profile("Test")
public class QuickRegisterServiceFixture implements
		QuickRegisterService {

	@Override
	public QuickRegisterStringStatusDTO checkIfAlreadyExist(
			QuickRegisterEntity customerQuickRegisterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String populateMessageForDuplicationField(String duplicationStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuickRegisterSavedEntityDTO addNewCustomer(
			QuickRegisterEntity customerQuickRegisterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyEmail(VerifyEmailDTO emailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSendMobilePin(CustomerIdTypeMobileDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSendEmailHash(CustomerIdTypeEmailDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSetMobilePin(CustomerIdTypeMobileDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSetEmailHash(CustomerIdTypeEmailDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationDetailsDTO verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean resetPassword(Long customerId,Integer customerType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuickRegisterDTO resetPasswordRedirect(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearTestData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdTypeAndEmail(
			Long customerId,Integer customerType, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile(
			Long customerId,Integer customerType, Long mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationDetailsDTO getAuthenticationDetailsByCustomerIdType(
			Long customerId,Integer customerType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDocumetDTO saveCustomerDocumet(
			CustomerDocumetDTO customerDocumetDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDocumetDTO getCustomerDocumetById(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationDetailsDTO verifyEmailLoginDetails(
			VerifyEmailLoginDetails emailLoginDetails) {
		// TODO Auto-generated method stub
		return null;
	}




}
