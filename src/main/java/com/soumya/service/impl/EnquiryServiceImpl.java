package com.soumya.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soumya.binding.DashboardResponse;
import com.soumya.binding.EnquiryForm;
import com.soumya.binding.EnquirySearchCriteria;
import com.soumya.entity.CourseEntity;
import com.soumya.entity.EnqStatusEntity;
import com.soumya.entity.StudentEnqEntity;
import com.soumya.entity.UserDtlsEntity;
import com.soumya.repo.CourseRepo;
import com.soumya.repo.EnqStatusRepo;
import com.soumya.repo.StudentEnqRepo;
import com.soumya.repo.UserDtlsRepo;
import com.soumya.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private StudentEnqRepo enqRepo;
	
	@Autowired
	private CourseRepo coursesRepo;
	
	@Autowired
	private EnqStatusRepo statusRepo;
	
	@Autowired
	private HttpSession session;


	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		
		DashboardResponse response = new DashboardResponse();
		
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userEntity = findById.get();
			List<StudentEnqEntity> enquiries = userEntity.getEnquiries();
			Integer totalCnt = enquiries.size();
			Integer enrolledCnt = enquiries.stream()
					.filter(e -> e.getEnqStatus().equals("Enrolled"))
					.collect(Collectors.toList()).size();
			Integer lostCnt = enquiries.stream()
					.filter(e -> e.getEnqStatus().equals("Lost"))
					.collect(Collectors.toList()).size();
			
			response.setTotalEnquriesCnt(totalCnt);
			response.setEnrolledCnt(enrolledCnt);
			response.setLostCnt(lostCnt);
		}
		return response;
	}
	
	@Override
	public List<String> getCourses() {
		List<CourseEntity> findAll = coursesRepo.findAll();
		List<String> names = new ArrayList<>();
		for(CourseEntity entity : findAll) {
			names.add(entity.getCourseName());
		}
		return names;
	}
	
	@Override
	public List<String> getEnqStatuses() {
		List<EnqStatusEntity> findAll = statusRepo.findAll();
		List<String> statusList = new ArrayList<>();
		for(EnqStatusEntity entity : findAll) {
			statusList.add(entity.getStatusName());
		}
		return statusList;
	}
	
	@Override
	public boolean saveEnquiry(EnquiryForm form) {
		StudentEnqEntity enqEntity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);
		
		Integer userId =(Integer) session.getAttribute("userId");
		UserDtlsEntity userEntity = userDtlsRepo.findById(userId).get();
		enqEntity.setUser(userEntity);
		enqRepo.save(enqEntity);
		return true;
	}

    
	@Override
	public List<StudentEnqEntity> getEnquiries() {
		Integer userId =(Integer) session.getAttribute("userId");
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			return enquiries;
		}
		return null;
	}

	
	@Override
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			
			//filter logic
			if(null != criteria.getCourseName() && !"".equals(criteria.getCourseName())) {
				
			 enquiries = enquiries.stream()
				                  .filter(e -> e.getCourseName().equals(criteria.getCourseName()))
				                  .collect(Collectors.toList());
				
			}
			
			if(null != criteria.getEnqStatus() && !"".equals(criteria.getEnqStatus())) {
			enquiries = enquiries.stream()
				          .filter(e -> e.getEnqStatus().equals(criteria.getEnqStatus()))
				          .collect(Collectors.toList());
			}
			if(null != criteria.getClassMode() && !"".equals(criteria.getClassMode())) {
				enquiries = enquiries.stream()
						.filter(e -> e.getClassMode().equals(criteria.getClassMode()))
						.collect(Collectors.toList());
			}
			
			return enquiries;
		}
		return null;
	}

	@Override
	public Optional<StudentEnqEntity> findById(Integer userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	

}
