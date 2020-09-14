package com.kakaopay.spreadapp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.spreadapp.service.SpreadService;

@RestController
public class SpreadController {
	@Autowired
	public SpreadService spreadService;
	
	/**
	 * @param _user 		뿌리기 요청 유저 ID
	 * @param _room			뿌리기 요청 방 ID
	 * @param _rgTotalMoney 뿌리기 요청 금액 
	 * @param _rgUserCount	뿌리기 요청 인원 
	 * @return
	 */
	@RequestMapping(path = "/spread")
	public HashMap<String, String> registSpreadRequest(
			 @RequestHeader(value = "X-USER-ID") int _user
			,@RequestHeader(value = "X-ROOM-ID") String _room
			,@RequestParam(value = "RG-TOTAL-MONEY") int _rgTotalMoney
			,@RequestParam(value = "RG-USER-COUNT") int _rgUserCount)
	{
		HashMap<String, String> rSpreadService;
		rSpreadService = spreadService.registSpreadRequest(
				 _user
				,_room
				,_rgTotalMoney
				,_rgUserCount);
			
		//RETURN
		return rSpreadService;
		
	}

	/**
	 * @param _user		뿌리기 요청 유저 ID
	 * @param _room		뿌리기 요청 방 ID
	 * @param _token	뿌리기 Token
	 * @return
	 */
	@RequestMapping(path = "/serve")
	public int serveSpreadRequest(
			 @RequestHeader(value = "X-USER-ID") int _user
			,@RequestHeader(value = "X-ROOM-ID") String _room
			,@RequestParam(value = "TOKEN") String _token)
	{
		int rSpreadService;
		rSpreadService = spreadService.serveSpreadRequest(
				 _user
				 ,_room
				 ,_token);
		return rSpreadService;	
	}
	
	/**
	 * @param _user		뿌리기 요청 유저 ID
	 * @param _room		뿌리기 요청 방 ID
	 * @param _token	뿌리기 Token
	 * @return
	 */
	@RequestMapping(path = "/retrieve")
	public List<HashMap<String, String>> retrieveSpreadRequest(
			 @RequestHeader(value = "X-USER-ID") int _user
			,@RequestHeader(value = "X-ROOM-ID") String _room
			,@RequestParam(value = "TOKEN") String _token)
	{
		List<HashMap<String, String>>rSpreadService;
		rSpreadService = spreadService.retrieveSpreadRequest(
				 _user
				 ,_room
				 ,_token);
		return rSpreadService;	
	}
	
}
