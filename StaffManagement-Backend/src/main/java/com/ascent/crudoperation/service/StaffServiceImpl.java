package com.ascent.crudoperation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ascent.crudoperation.model.Staff;
import com.ascent.crudoperation.repository.StaffRepository;
@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepository srepo;

	@Override
	public boolean saveStaff(Staff staff) {
		// TODO Auto-generated method stub
		Staff savedStaff = srepo.save(staff);
		return savedStaff.getStaffId() != null ? true : false ;
	}

	@Override
	public boolean updateStaff(Staff staff) {
		
		
		Optional<Staff> findById = srepo.findById(staff.getStaffId());
		// TODO Auto-generated method stub
		if(findById.isPresent()) {
			srepo.save(staff);
			return true;
	}
		else {
		return false;
	}
}

	@Override
	public List<Staff> getAllStaff() {
		// TODO Auto-generated method stub
		return srepo.findAll();
	}

	@Override
	public boolean deleteStaffById(Integer staffId) {
		// TODO Auto-generated method stub
		try {
			srepo.deleteById(staffId);
			return true;
			}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Optional<Staff> getStaffById(Integer staffId) {
		// TODO Auto-generated method stub
		return srepo.findById(staffId);
	}

	
}
