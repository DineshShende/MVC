package com.projectx.mvc.servicefixtures.quickregister;

import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.*;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.PasswordRestFailedException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.ForgetPasswordEntity;
import com.projectx.rest.domain.quickregister.MobilePinPasswordDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;
@Component
@Profile("Test")
public class QuickRegisterServiceFixture implements
		QuickRegisterService {

	@Override
	public String populateMessageForDuplicationField(String duplicationStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuickRegisterSavedEntityDTO addNewCustomer(
			QuickRegisterEntity customerQuickRegisterEntity)
			throws QuickRegisterDetailsAlreadyPresentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO)
			throws QuickRegisterEntityNotFoundException {
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
	public Boolean sendMobilePin(
			CustomerIdTypeMobileTypeRequestedByDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSendMobilePin(
			CustomerIdTypeMobileTypeRequestedByDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sendEmailHash(CustomerIdTypeEmailTypeUpdatedByDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSendEmailHash(
			CustomerIdTypeEmailTypeUpdatedByDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSetMobilePin(
			CustomerIdTypeMobileTypeRequestedByDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSetEmailHash(
			CustomerIdTypeEmailTypeUpdatedByDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationDetailsDTO verifyEmailLoginDetails(
			VerifyEmailLoginDetails emailLoginDetails)
			throws AuthenticationDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationDetailsDTO verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO)
			throws AuthenticationDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean resetPassword(Long customerId, Integer customerType,
			Integer emailOrMobile, String requestedBy)
			throws PasswordRestFailedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForgetPasswordEntity resetPasswordRedirect(String entity,
			String requestedBy) throws PasswordRestFailedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearTestData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdTypeAndEmail(
			Long customerId, Integer customerType, Integer emailType)
			throws EmailVerificationDetailNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile(
			Long customerId, Integer customerType, Integer mobileType)
			throws MobileVerificationDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationDetails getAuthenticationDetailsByCustomerIdType(
			Long customerId, Integer customerType)
			throws AuthenticationDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MobilePinPasswordDTO> getTestData() {
		// TODO Auto-generated method stub
		return null;
	}

				

}
