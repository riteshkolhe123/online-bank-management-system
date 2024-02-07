package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.request.AdminLoginRequest;
import org.dnyanyog.dto.request.AdminResgisterRequest;
import org.dnyanyog.dto.response.AdminResgisterData;
import org.dnyanyog.dto.response.AdminLoginResponse;
import org.dnyanyog.dto.response.AdminResgisterResponse;
import org.dnyanyog.entity.Admin;
import org.dnyanyog.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	AdminRepository repo;
	
	@Autowired
	AdminResgisterResponse adminResponse;
	
	@Autowired
	AdminLoginResponse response;

	public AdminLoginResponse loginAdmin(AdminLoginRequest adminLoginRequest) {

		List<Admin> admin = repo.findByAdminIdAndPassword(adminLoginRequest.getAdminId(),
				adminLoginRequest.getPassword());

		if (admin.size() == 1) {
			response.setStatus("Success");
			response.setMessage("Admin Found!");
		} else {
			response.setStatus("Fail");
			response.setMessage("Admin Not Found");
		}
		return response;
	}
	public AdminResgisterResponse registerAdmin(AdminResgisterRequest adminServiceRequest) {
		
		Admin admin = new Admin();

		admin.setAdminEmail(adminServiceRequest.getAdminEmail());
		admin.setPassword(adminServiceRequest.getPassword());
		admin.setAdminId(adminServiceRequest.getAdminId());

		admin = repo.save(admin);

		adminResponse.setStatus("Success");
		adminResponse.setMessage("Admin Register Successfully");
		adminResponse.getAdminData().setAdminId(admin.getAdminId());

		adminResponse.getAdminData().setAdminEmail(admin.getAdminEmail());
		adminResponse.getAdminData().setPassword(admin.getPassword());

		return adminResponse;
	}

}
