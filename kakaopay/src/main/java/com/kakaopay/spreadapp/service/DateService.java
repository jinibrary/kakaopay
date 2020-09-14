package com.kakaopay.spreadapp.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
/**
 * @author sangjin
 *	
 */
@Service
public class DateService {

	Calendar cal = Calendar.getInstance();
	
	/**
	 * @param _stDate	
	 * @param _comDate
	 * 
	 * _stDate > _comDate : 1
	 * _stDate == _comdate : 0
	 * _stDate < _comdate : -1
	 * @return 
	 */
	public int compareDate(Date _stDate, Date _comDate) {
		
		return _stDate.compareTo(_comDate);
	}
	
	/**
	 * @param _date
	 * @param min
	 * _date 에서 _min만큼 분을 이동 
	 * @return
	 */
	public Date changeMinDate(Date _date, int _min)
	{
		cal.setTime(_date);
		cal.add(Calendar.MINUTE, _min);
		_date = cal.getTime();
		
		return _date;
	}
	
	/**
	 * @param _date
	 * @param _hour
	 * _date 에서 _hour만큼 분을 이동 
	 * @return
	 */
	public Date changeHourDate(Date _date, int _hour)
	{
		cal.setTime(_date);
		cal.add(Calendar.HOUR, _hour);
		_date = cal.getTime();
		
		return _date;
	}

	/**
	 * @param _date
	 * @param _day
	 * _date 에서 _day만큼 날을 이동 
	 * @return
	 */
	public Date changeDayDate(Date _date, int _day)
	{
		cal.setTime(_date);
		cal.add(Calendar.DATE, _day);
		_date = cal.getTime();
		
		return _date;
	}
}
