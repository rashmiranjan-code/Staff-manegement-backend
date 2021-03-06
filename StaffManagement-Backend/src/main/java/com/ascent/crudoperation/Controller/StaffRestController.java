package com.ascent.crudoperation.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ascent.crudoperation.model.Staff;
import com.ascent.crudoperation.service.StaffService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/staff")
public class StaffRestController {
	@Autowired
	private StaffService staffService;
	@PostMapping("/save")
	public ResponseEntity<String> saveStaff(@RequestBody Staff staff){

		boolean isSaved = staffService.saveStaff(staff);
		if(isSaved) {
			return new ResponseEntity<String>("Hi admin "+staff.getStaffName()+" Data saved sucessfully.", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("failed to saved", HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	@GetMapping("/retrive")
	public ResponseEntity<List<Staff>> getAllStaffs(){
		List<Staff> allStaff = staffService.getAllStaff();
		return new ResponseEntity<>(allStaff,HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStaffById(@PathVariable Integer id){
		boolean isDeleted = staffService.deleteStaffById(id);
		if(isDeleted) {
			return new ResponseEntity<>("Deleted",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Not Deleted",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	@PutMapping("/update")
    public ResponseEntity<String> updateStaff(@RequestBody Staff staff) {
		
			boolean isUpdated = staffService.updateStaff(staff);
			if(isUpdated) {
				return new ResponseEntity<>("Staff Data updated", HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("Data not updated", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		
	}
	@GetMapping("/retrive/staffById/{id}")
	
	public ResponseEntity<Optional<Staff>> getStaffById(@PathVariable int id){
		
		Optional<Staff> staff = staffService.getStaffById(id);
		if (staff.isPresent()) {
			
			return new ResponseEntity<>(staff , HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
}
