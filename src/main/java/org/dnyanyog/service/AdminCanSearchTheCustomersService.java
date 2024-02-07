package org.dnyanyog.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.response.AdminCanSearchTheCustomersResponse;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.repo.AdminCanSearchTheCustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;

@Service
public class AdminCanSearchTheCustomersService {

	@Autowired
	AdminCanSearchTheCustomersRepository repo;

	public AdminCanSearchTheCustomersResponse getSingleCustomer(Long customerId) {
		AdminCanSearchTheCustomersResponse response = new AdminCanSearchTheCustomersResponse();

		Optional<CustomerResgistration> receivedData = repo.findByCustomerId(customerId);

		if (receivedData.isEmpty()) {
			response.setStatus("Fail");
			response.setMessage("Customer Not Found ");
		}else
		{
			CustomerResgistration customer = receivedData.get();

			response.setCustomerId(customer.getCustomerId());
			response.setFirstName(customer.getFirstName());
			response.setLastName(customer.getLastName());
			response.setDateOfBirth(customer.getDateOfBirth());
			response.setMobileNumber(customer.getMobileNo());
			response.setEmailId(customer.getEmailId());
			response.setGender(customer.getGender());
			response.setBranch(customer.getBranch());
			response.setPassword(customer.getPassword());
			response.setPermantAddress(customer.getPermanentAddress());
			response.setPresentAddress(customer.getPresentAddress());

			response.setStatus("Success");
			response.setMessage("Customer Serach Successfully ");

		}

		return response;
	}

}
