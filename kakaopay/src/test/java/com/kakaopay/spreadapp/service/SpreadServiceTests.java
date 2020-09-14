package com.kakaopay.spreadapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakaopay.spreadapp.dao.SpreadRequestBasDAO;
import com.kakaopay.spreadapp.dao.SpreadRequestIzDAO;
import com.kakaopay.spreadapp.vo.SpreadRequestBasVO;
import com.kakaopay.spreadapp.vo.SpreadRequestIzVO;

@SpringBootTest
public class SpreadServiceTests {

	@Autowired
	SpreadRequestBasDAO spreadRequestBasDAO;
	@Autowired
	SpreadRequestIzDAO spreadRequestIzDAO;
	@Autowired
	SpreadService spreadService;
	@Autowired
	TokenService tokenService;
	@Autowired
	DateService dateService;
	/**
	 * public HashMap<String, String> registSpreadRequest(int _user, String _room, int _rgTotalMoney, int _rgUserCount);
	 * @param _user			뿌리기 요청 유저 ID
	 * @param _room			뿌리기 요청 방 ID
	 * @param _rgTotalMoney	뿌리기 요청 금액
	 * @param _rgUserCount	뿌리기 요청 인
	 * @return type : HashMap
	 * 	TB_뿌리기요청기본(TB_SPREAD_REQUEST_BAS) / TB_뿌리기요청내역(TB_SPREAD_REQUEST_IZ)에 적재 
	 * 		
	 * 	return : Hashmap {token , success - responseCode : 200}
	 */
	
	@Test
	void registSpreadRequestTest1()
	{
		System.out.println("START registSpreadRequestTest()");
		{	//_user, _room 이 같은 2번의 뿌리기 요 -> 다른 토큰을 등록하여 둘 다 성공 : 200 / 200
			
			String 	_room			=	tokenService.tokenGenerator(3);
			int 	_user			= 	(int)(Math.random() * 1000);
			int 	_rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	_rgUserCount		=	(int)(Math.random() * 10);
			
			HashMap<String, String> rSpreadService = null;
			System.out.println("spreadService.registSpreadRequest START");
			rSpreadService = spreadService.registSpreadRequest(
					 _user
					,_room
					,_rgTotalMoney
					,_rgUserCount);
			
			System.out.println("spreadService.registSpreadRequest RETURN : " + rSpreadService);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
			
			System.out.println("spreadService.registSpreadRequest START2");
			rSpreadService = spreadService.registSpreadRequest(
					 _user
					,_room
					,_rgTotalMoney
					,_rgUserCount);
			
			System.out.println("spreadService.registSpreadRequest RETURN2 : " + rSpreadService);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
		}
		System.out.println("END registSpreadRequestTest()");
	}
	
	
	/**
	 * public int serveSpreadRequest(int _user, String _room, String _token);
	 * @param _user			뿌리기 요청 유저 ID
	 * @param _room			뿌리기 요청 방 ID
	 * @param _token		뿌리기 요청 token
	 * @return	type : int 
	 *	1. 받기 예외처리 
	 *		: 10분내에 (_user, _room, _token)에 해당하는 뿌리기요청이 있었는지 조회 
	 *			1-1. 해당 요청 room 미존재 
	 *				fail - responseCode : -100
	 *			1-2. 뿌리기 요청 유저와 받기 요청 유저가 같음 
	 *				fail - responseCode : -200 
	 *	2. 받기 분배 할당 
 			: TB_뿌리기요청내역(TB_SPREAD_REQUEST_IZ) UPDATE
	 * 		
	 * return : int 
	 * 			success : 200
	 * 			fail :	-100	미존재
	 * 					-200	뿌리기 요청한 유저가 받기 요청을 보냈을 때  
	 */
	@Test
	void serveSpreadRequestTest()
	{
		System.out.println("START serveSpreadRequestTest()");
		{	//_user, _room 뿌리기 요청 이후 / 요청한 뿌리기 인원에 맞춰 받기 요청 
			// 뿌리기 요청 : 200 / 뿌리기 요청시 등록한 받기 인원 수만큼 200 응
			
			String 	_room			=	tokenService.tokenGenerator(3);
			int 	_user			= 	(int)(Math.random() * 1000);
			int 	_rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	_rgUserCount		=	(int)(Math.random() * 10) + 1;
			HashMap<String, String> rSpreadService = null;
			
			System.out.println("spreadService.registSpreadRequest START");
			rSpreadService = spreadService.registSpreadRequest(
					 _user
					,_room
					,_rgTotalMoney
					,_rgUserCount);
			
			System.out.println("spreadService.registSpreadRequest RETURN : " + rSpreadService);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
			
			System.out.println("spreadService.serveSpreadRequest START");
			int rSpreadServcieCode = 0;
			for( int i = 0 ; i < _rgUserCount ; i ++)
			{
				_user	= 	(int)(Math.random() * 1000);
				rSpreadServcieCode = spreadService.serveSpreadRequest(
						 _user
						,_room
						,rSpreadService.get("token"));
				
				System.out.println("spreadService.serveSpreadRequest " + i + " RETURN : " + rSpreadServcieCode);
			}
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
		}
		System.out.println("END serveSpreadRequestTest()");
	}
	@Test
	void serveSpreadRequestTest2()
	{
		System.out.println("START serveSpreadRequestTest()");
		{	//없는 token, room번호를 받기 요청 / 응답 : -100
			

			String 	_room			=	tokenService.tokenGenerator(3);
			int 	_user			= 	(int)(Math.random() * 1000);
			String		_token		= 	tokenService.tokenGenerator(3);
			
			System.out.println("spreadService.serveSpreadRequest START");
			int rSpreadServcieCode = 0;

			rSpreadServcieCode = spreadService.serveSpreadRequest(
					 _user
					,_room
					,_token);
				
			System.out.println("spreadService.serveSpreadRequest RETURN : " + rSpreadServcieCode);	

			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
		}
		System.out.println("END serveSpreadRequestTest()");
	}
	@Test
	void serveSpreadRequestTest3()
	{
		System.out.println("START serveSpreadRequestTest()");
		{	//없는 token, room번호는 존재하지만 뿌리기 요청자가 받기를 요청한 경우 / 응답 : -200
			String 	_room			=	tokenService.tokenGenerator(3);
			int 	_user			= 	(int)(Math.random() * 1000);
			int 	_rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	_rgUserCount		=	(int)(Math.random() * 10) + 1;
			HashMap<String, String> rSpreadService = null;
			
			System.out.println("spreadService.registSpreadRequest START");
			rSpreadService = spreadService.registSpreadRequest(
					 _user
					,_room
					,_rgTotalMoney
					,_rgUserCount);
			
			System.out.println("spreadService.registSpreadRequest RETURN : " + rSpreadService);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
			
			System.out.println("spreadService.serveSpreadRequest START");
			int rSpreadServcieCode = 0;
			
			rSpreadServcieCode = spreadService.serveSpreadRequest(
					 _user
					,_room
					,rSpreadService.get("token"));
			
			System.out.println("spreadService.serveSpreadRequest RETURN : " + rSpreadServcieCode);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
		}
		System.out.println("END serveSpreadRequestTest()");
	}
	/**
	 * public List<HashMap<String, String>> retrieveSpreadRequest(int _user, String _room, String _token);
	 * @param _user			뿌리기 요청 유저 ID
	 * @param _room			뿌리기 요청 방 ID
	 * @param _token		뿌리기 요청 token
	 * @return type : List<HashMap<String, String>> 
	 * 		
	 * 		TB_뿌리기요청기본(TB_SPREAD_REQUEST_BAS) / TB_뿌리기요청내역(TB_SPREAD_REQUEST_IZ) 조회
	 * 		
	 * 		
	 *  
	 *  	return : List<HashMAP> 
	 *  		
	 *  	List[
	 * 			HashMap(
	 * 				responseCode : 200(성공) / -100(미존재)
	 * 				,RG_REQUEST_DATE 	: 뿌리기 요청 등록 일자 
	 *  			,RG_TOTAL_MONEY		: 뿌리기 요청 금액 
	 *  			,SERVE_TOTAL_MONEY	: 받기 완료 총 금액 
	 *  			,SERVE_LIST			: 받기 완료 LIST[ 받은 금액(RG_SERVE_MONEY), 받은 유저(RG_USER)])
	 *  		,HashMap(...)
	 *  		, ... 
	 *  	]
	 */
	@Test
	public void retrieveSpreadReuqestTest()
	{
		System.out.println("START retrieveSpreadReuqestTest()");
		{	//성공 시나리오 테스트 
			String 	_room			=	tokenService.tokenGenerator(3);
			int 	_user			= 	(int)(Math.random() * 1000);
			int 	_rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	_rgUserCount		=	(int)(Math.random() * 10) + 1;
			HashMap<String, String> rSpreadService = null;
			
			System.out.println("spreadService.registSpreadRequest START");
			rSpreadService = spreadService.registSpreadRequest(
					 _user
					,_room
					,_rgTotalMoney
					,_rgUserCount);
			
			System.out.println("spreadService.registSpreadRequest RETURN : " + rSpreadService);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
			
			System.out.println("_rgUserCount : " + _rgUserCount);
			System.out.println("spreadService.serveSpreadRequest START");
			int rSpreadServcieCode = 0;
			for( int i = 0 ; i < _rgUserCount ; i ++)
			{
				int tempUser	= 	(int)(Math.random() * 1000);
				rSpreadServcieCode = spreadService.serveSpreadRequest(
						tempUser
						,_room
						,rSpreadService.get("token"));
				
				System.out.println("spreadService.serveSpreadRequest " + i + " RETURN : " + rSpreadServcieCode);
			}
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
			
			List<HashMap<String, String>> rSpreadServiceList = new ArrayList<HashMap<String,String>>();
			System.out.println("spreadService.retrieveSpreadRequest START");
			rSpreadServiceList = spreadService.retrieveSpreadRequest(
					_user
					,_room
					,rSpreadService.get("token"));
			
			System.out.println("spreadService.retrieveSpreadRequest RETURN : " + rSpreadServiceList);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitDay);
			
		}
		System.out.println("END retrieveSpreadReuqestTest()");
	}
	@Test
	public void retrieveSpreadReuqestTest2()
	{
		System.out.println("START retrieveSpreadReuqestTest()");
		{	// retrieve시 미존재 데이터를 요청 할 시 
			String 	_room			=	tokenService.tokenGenerator(3);
			int 	_user			= 	(int)(Math.random() * 1000);
			int 	_rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	_rgUserCount		=	(int)(Math.random() * 10) + 1;
			HashMap<String, String> rSpreadService = null;
			
			System.out.println("spreadService.registSpreadRequest START");
			rSpreadService = spreadService.registSpreadRequest(
					 _user
					,_room
					,_rgTotalMoney
					,_rgUserCount);
			
			System.out.println("spreadService.registSpreadRequest RETURN : " + rSpreadService);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
			
			System.out.println("_rgUserCount : " + _rgUserCount);
			System.out.println("spreadService.serveSpreadRequest START");
			int rSpreadServcieCode = 0;
			for( int i = 0 ; i < _rgUserCount ; i ++)
			{
				int tempUser	= 	(int)(Math.random() * 1000);
				rSpreadServcieCode = spreadService.serveSpreadRequest(
						tempUser
						,_room
						,rSpreadService.get("token"));
				
				System.out.println("spreadService.serveSpreadRequest " + i + " RETURN : " + rSpreadServcieCode);
			}
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitMin);
			
			String 	_room2			=	tokenService.tokenGenerator(3);
			int 	_user2			= 	(int)(Math.random() * 1000);
			
			List<HashMap<String, String>> rSpreadServiceList = new ArrayList<HashMap<String,String>>();
			System.out.println("spreadService.retrieveSpreadRequest START");
			rSpreadServiceList = spreadService.retrieveSpreadRequest(
					_user2
					,_room2
					,rSpreadService.get("token"));
			
			System.out.println("spreadService.retrieveSpreadRequest RETURN : " + rSpreadServiceList);
			
			//printAllTB(rSpreadService.get("token"), _user, _room, limitDay);
			
		}
		System.out.println("END retrieveSpreadReuqestTest()");
	}
	void printAllTB(String _token, int _user, String _room, int _limit)
	{
		//select SpreadRequestBas
		Date 	rgRequestDate 	=	dateService.changeMinDate(new Date(), _limit);
		
		SpreadRequestBasVO basVO = new SpreadRequestBasVO(
				_token
				, _room
				, rgRequestDate);
		
		System.out.println("print SpreadRequestBasVO : " + basVO);
		System.out.println("spreadRequestDao.selectAfterRgDateSpreadRequestBas START ");
		List<SpreadRequestBasVO> rBasVOList = null;
		try {
			rBasVOList= spreadRequestBasDAO.selectAfterRgDateSpreadRequestBas(basVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("spreadRequestDao.selectAfterRgDateSpreadRequestBas Result : ");
		for ( int i = 0 ; i < rBasVOList.size() ; i ++)
		{
			System.out.println("rBasVoList " + i + "번째 BasVO	:	" + rBasVOList.get(i));
		}
		
		SpreadRequestIzVO izVO = new SpreadRequestIzVO(
				_token
				, _room
				, _user
				, rgRequestDate);
		
		System.out.println("print SpreadRequestIzVO : " + izVO);
		System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz START ");
		List<SpreadRequestIzVO> rIzVOList = null;
		try {
			rIzVOList= spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz(izVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz Result : ");
		for ( int i = 0 ; i < rIzVOList.size() ; i ++)
		{
			System.out.println("rIzVoList " + i + "번째 BasVO	:	" + rIzVOList.get(i));
		}
	}
}

