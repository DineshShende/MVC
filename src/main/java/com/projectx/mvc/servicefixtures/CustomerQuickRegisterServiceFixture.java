package com.projectx.mvc.servicefixtures;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.*;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.projectx.mvc.domain.CustomerDocumetDTO;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.domain.UpdatePasswordDTO;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerAuthenticationDetailsDTO;
import com.projectx.rest.domain.CustomerEmailVerificationDetailsDTO;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerIdEmailDTO;
import com.projectx.rest.domain.CustomerIdMobileDTO;
import com.projectx.rest.domain.CustomerMobileVerificationDetailsDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.CustomerQuickRegisterStringStatusDTO;
import com.projectx.rest.domain.LoginVerificationDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyEmailLoginDetails;
import com.projectx.rest.domain.VerifyMobileDTO;
@Component
@Profile("Test")
public class CustomerQuickRegisterServiceFixture implements
		CustomerQuickRegisterService {

	@Override
	public CustomerQuickRegisterStringStatusDTO checkIfAlreadyExist(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String populateMessageForDuplicationField(String duplicationStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerQuickRegisterSavedEntityDTO addNewCustomer(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerQuickRegisterDTO getByCustomerId(CustomerIdDTO customerIdDTO) {
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
	public Boolean reSendMobilePin(CustomerIdMobileDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSendEmailHash(CustomerIdEmailDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSetMobilePin(CustomerIdMobileDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSetEmailHash(CustomerIdEmailDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerAuthenticationDetailsDTO verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean resetPassword(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerQuickRegisterDTO resetPasswordRedirect(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearTestData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerEmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdAndEmail(
			Long customerId, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerMobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdAndMobile(
			Long customerId, Long mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerAuthenticationDetailsDTO getAuthenticationDetailsByCustomerId(
			Long customerId) {
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
	public CustomerAuthenticationDetailsDTO verifyEmailLoginDetails(
			VerifyEmailLoginDetails emailLoginDetails) {
		// TODO Auto-generated method stub
		return null;
	}




}
