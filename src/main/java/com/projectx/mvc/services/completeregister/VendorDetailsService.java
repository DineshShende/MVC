package com.projectx.mvc.services.completeregister;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.projectx.mvc.domain.completeregister.DriverSimplified;
import com.projectx.mvc.domain.completeregister.L1DriverCompleteRegistration;
import com.projectx.mvc.domain.completeregister.L1VehicleCompleteRegistration;
import com.projectx.mvc.domain.completeregister.VehicleSimplified;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;


@Service
public interface VendorDetailsService {

	//VendorDetailsDTO createCustomerDetailsFromQuickRegisterEntity(Long entityId);
	
	VendorDetailsDTO update(VendorDetailsDTO vendorDetails);
	
	VendorDetailsDTO getCustomerDetailsById( Long vendorId);
	
	VendorDetailsDTO intializeMetaData(VendorDetailsDTO vendorDetailsDTO);
	
	
	Model initialiseShowVendorDetails(Long entityId,Model model);
	
	Integer count();
	
	Boolean clearTestData();
	
	DriverDetailsDTO addDriver(DriverDetailsDTO driverDetailsDTO);
	
	DriverDetailsDTO initializeDriverDetails(DriverDetailsDTO driverDetailsDTO);
	
	DriverDetailsDTO addDriverSimplified(DriverSimplified driverSimplified);
	
	DriverDetailsDTO l1DriverDataEntry(L1DriverCompleteRegistration l1DriverCompleteRegistration);
	
	DriverDetailsDTO getDriverById(Long driverId);
	
	DriverDetailsDTO getDriverByLicenceNumber(String licenceNumber);
	
	Boolean deleteDriverById(Long driverId);
	
	List<DriverDetailsDTO> getDriversByVendor(Long venorId);
	
	Integer vehicleCount();
	
	Boolean vehicleClearTestData();
	
	VehicleDetailsDTO save(VehicleDetailsDTO vehicleDetailsDTO);
	
	VehicleDetailsDTO saveSimplified(VehicleSimplified vehicleSimplified);
	
	VehicleDetailsDTO l1VehicleDataEntry(L1VehicleCompleteRegistration l1VehicleCompleteRegistration);
	
	VehicleDetailsDTO getVehicleById(Long vehicleId);
	
	Boolean deleteVehicleById(Long vehicleId);
	
	List<VehicleDetailsDTO> getVehiclesByvendor(Long vendorId);
	
	Integer driverCount();
	
	Boolean driverClearTestData();
	
	
	
	
	
}
