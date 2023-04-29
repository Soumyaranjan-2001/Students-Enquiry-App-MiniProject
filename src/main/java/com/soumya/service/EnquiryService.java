package com.soumya.service;

import java.util.List;
import java.util.Optional;

import com.soumya.binding.DashboardResponse;
import com.soumya.binding.EnquiryForm;
import com.soumya.binding.EnquirySearchCriteria;
import com.soumya.entity.StudentEnqEntity;

public interface EnquiryService {

	public DashboardResponse getDashboardData(Integer userId);

	public List<String> getCourses();

	public List<String> getEnqStatuses();

	public boolean saveEnquiry(EnquiryForm form);

	public List<StudentEnqEntity> getEnquiries();
	
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId);

	public Optional<StudentEnqEntity> findById(Integer userId);

}
