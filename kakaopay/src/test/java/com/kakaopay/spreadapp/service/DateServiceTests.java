package com.kakaopay.spreadapp.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakaopay.spreadapp.service.DateService;

@SpringBootTest
public class DateServiceTests {

	@Autowired
	DateService dateService;
	
	Calendar cal = Calendar.getInstance();
	/**
	 * public int compareDate(Date _stDate, Date _comDate) {
	 * @param _stDate	
	 * @param _comDate
	 * @return
	 * 	_stDate > _comDate : 1
	 * 	_stDate == _comdate : 0
	 * 	_stDate < _comdate : -1 
	 */
	@Test
	void compareDateTest()
	{
		System.out.println("START compareDateTest()");
		
		{
			Date testDate1 = new Date();
			Date testDate2 = new Date();
			System.out.println("Date1 = Date2 : " + dateService.compareDate(testDate1, testDate2));
			
			cal.setTime(testDate1);
			cal.add(Calendar.MINUTE, 1);
			testDate2 = cal.getTime();
			
			System.out.println("Date1 < Date2 : " + dateService.compareDate(testDate1, testDate2));
			
			cal.setTime(testDate1);
			cal.add(Calendar.MINUTE, -1);
			testDate2 = cal.getTime();
			
			System.out.println("Date1 > Date2 : " + dateService.compareDate(testDate1, testDate2));
		}
		
		System.out.println("END compareDateTest()");
	}
	
	/**
	 * public Date changeMinDate(Date _date, int _min)
	 * @param 	_date
	 * @param 	min
	 * @return	_date 에서 _min만큼 분을 이동 
	 */
	@Test
	void changeMinDateTest()
	{
		System.out.println("START ChangeMinDateTest()");
		
		{
			Date date = new Date();
			
			System.out.println("INPUT DATE : 		" + date.toString());
			date = dateService.changeMinDate(date, 10);
			System.out.println("DATE / PARAM = 1 :	" + date.toString());
			date = dateService.changeMinDate(date, -10);
			System.out.println("DATE / PARAM = -1 : 	" + date.toString());
		}
		
		System.out.println("END ChangeMinDateTest()");
	}
	
	/**
	 * public Date changeHourDate(Date _date, int _hour)
	 * @param _date
	 * @param _hour
	 * 
	 * @return _date 에서 _hour만큼 분을 이동 
	 */
	@Test
	void changeHourDateTest()
	{
		System.out.println("START ChangeHourDateTest()");
		
		{
			Date date = new Date();
			
			System.out.println("INPUT DATE : 		" + date.toString());
			date = dateService.changeHourDate(date, 1);
			System.out.println("DATE / PARAM = 1 : 	" + date.toString());
			date = dateService.changeHourDate(date, -1);
			System.out.println("DATE / PARAM = -1 : 	" + date.toString());
		}
		
		System.out.println("START ChangeHourDateTest()");
	}

	/**
	 * public Date changeDayDate(Date _date, int _day)
	 * @param _date
	 * @param _day
	 * _date 에서 _day만큼 날을 이동 
	 * @return
	 */
	@Test
	void changeDayDateTest()
	{
		System.out.println("START ChangeDayDateTest()");
		
		{
			Date date = new Date();
			
			System.out.println("INPUT DATE : " + date.toString());
			date = dateService.changeDayDate(date, 1);
			System.out.println("DATE / PARAM = 1 : " + date.toString());
			date = dateService.changeDayDate(date, -1);
			System.out.println("DATE / PARAM = -1 : " + date.toString());
		}
		
		System.out.println("START ChangeDayDateTest()");
	}
}
