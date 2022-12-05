package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import com.example.demo.payload.request.TimeRequest;

@Component
public class ConvertDateToJodaDateUtil {
	
	List<TimeRequest> buyList = new ArrayList<>(); 
	
	public LocalDate convertdate(java.time.LocalDate date) {
		new LocalDate();
		return LocalDate.parse(date.toString());
	}
	public List<TimeRequest> addFromTime(){
		for(int i = 0; i<=23; i++) {
			String time ="";
			if(i>9) {
				time= i+":00:00";
			}else {
				time ="0"+i+":00:00";
			}
			TimeRequest request = TimeRequest.builder().time(time).build();
			buyList.add(request);
		}
		return buyList;
		
	}
	public List<TimeRequest> toFromTime(){
		for(int i = 0; i<=23; i++) {
			String time ="";
			if(i>9) {
				time= i+":59:00";
			}else {
				time ="0"+i+":59:00";
			}
			TimeRequest request = TimeRequest.builder().time(time).build();
			buyList.add(request);
		}
		return buyList;
		
	}
}
