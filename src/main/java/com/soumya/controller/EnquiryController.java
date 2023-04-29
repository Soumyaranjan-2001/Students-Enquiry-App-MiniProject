package com.soumya.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soumya.binding.DashboardResponse;
import com.soumya.binding.EnquiryForm;
import com.soumya.binding.EnquirySearchCriteria;
import com.soumya.entity.StudentEnqEntity;
import com.soumya.repo.StudentEnqRepo;
import com.soumya.service.EnquiryService;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private HttpSession session;
	@Autowired
	private StudentEnqRepo repo;
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		//TODO: logic to fetch data for dashboard 
		//System.out.println("dashboard method called....");
		Integer userId =(Integer) session.getAttribute("userId");
		DashboardResponse dashboardData = enqService.getDashboardData(userId);
		model.addAttribute("dashboardData", dashboardData);
		return "dashboard";
	}
	
	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {
		initForm(model);
		//System.out.println(formObj);
		boolean status = enqService.saveEnquiry(formObj);
		if(status) {
			model.addAttribute("succMsg", "Enquiry Added");
		}else {
			model.addAttribute("errMsg", "Problem Occured");
		}
		return "add-enquiry";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		/* //get courses  for drop down
		List<String> courses = enqService.getCourses();
		
		//get enq status for drop down
		List<String> enqStatuses = enqService.getEnqStatuses();
		
		//create binding class obj
		EnquiryForm formObj = new EnquiryForm();
		
		//set data in model obj
		model.addAttribute("courseNames", courses);
		model.addAttribute("statusNames", enqStatuses);
		model.addAttribute("formObj", formObj); */
		initForm(model);
		return "add-enquiry"; 
	}
	
	public void initForm(Model model) {
		//get courses  for drop down
		List<String> courses = enqService.getCourses();
		
		//get enq status for drop down
		List<String> enqStatuses = enqService.getEnqStatuses();
		
		//create binding class obj
		EnquiryForm formObj = new EnquiryForm();
		
		//set data in model obj
		model.addAttribute("courseNames", courses);
		model.addAttribute("statusNames", enqStatuses);
		model.addAttribute("formObj", formObj);
	}
	
	
	@GetMapping("/enquires")
	public String viewEnquiryPage(Model model) {
		initForm(model);
		List<StudentEnqEntity> enquiries = enqService.getEnquiries();
		model.addAttribute("enquiries", enquiries);
		return "view-enquiries"; 
	}
	
	@GetMapping("/filter-enquiries")
	public String getFilteredEnqs(@RequestParam String cname,
			@RequestParam String status, 
			@RequestParam String mode, 
			Model model) {
		
		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		criteria.setCourseName(cname);
		criteria.setClassMode(mode);
		criteria.setEnqStatus(status);
		//System.out.println(criteria);
		
		Integer userId =(Integer) session.getAttribute("userId");
		List<StudentEnqEntity> filteredEnqs = enqService.getFilteredEnqs(criteria, userId);
		
		model.addAttribute("enquiries", filteredEnqs);
		
		return "filter-enquiries-page";
	}
	
	
	@GetMapping("/edit")
	public String edit(@RequestParam("enqId") Integer enqId, Model model) {
		
		initForm(model);

		Optional<StudentEnqEntity> findById = repo.findById(enqId);

		if (findById.isPresent()) {
			StudentEnqEntity record = findById.get();
			model.addAttribute("formObj", record);

		}
		return "add-enquiry";

	}
	
	
	
	


}
