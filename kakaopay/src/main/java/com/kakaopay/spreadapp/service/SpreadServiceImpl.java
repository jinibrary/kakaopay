package com.kakaopay.spreadapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.kakaopay.spreadapp.dao.SpreadRequestBasDAO;
import com.kakaopay.spreadapp.dao.SpreadRequestIzDAO;
import com.kakaopay.spreadapp.vo.SpreadRequestBasVO;
import com.kakaopay.spreadapp.vo.SpreadRequestIzVO;



@Service
public class SpreadServiceImpl implements SpreadService{

	private final static Logger LOG = Logger.getGlobal();
	
	@Autowired
	private SpreadRequestBasDAO spreadRequestBasDAO;
	@Autowired
	private SpreadRequestIzDAO spreadRequestIzDAO;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private DateService dateService;
	
	static private int limitMin = -10;
	static private int limitDay = -7;
	
	/**
	 *	뿌리기 요청
	 *	1. token 발행
	 *	2. TB_뿌리기_요청_기본(TB_SPREAD_REQUEST_BAS)에 해당 요청 적재
	 *	3. TB_뿌리기_요청_내역(TB_SPREAD_REQUEST_BAS)에 해당 요청 적재
	 */
	public HashMap<String, String> registSpreadRequest(int _user, String _room, int _rgTotalMoney, int _rgUserCount)
	{
		
		Date limitTime = dateService.changeMinDate(new Date(), limitMin);
		HashMap<String, String> response = new HashMap<String, String>();
		
		//token 발행
		SpreadRequestBasVO basVO = null; 
		List<SpreadRequestBasVO> rBasVOList = null; 
		String token = "";
		do {
			token = tokenService.tokenGenerator(3);
			
			basVO = new SpreadRequestBasVO(
					 token					//token
					,_room					//room
					, limitTime);			//rgRequestDate
			try {
				rBasVOList = spreadRequestBasDAO.selectAfterRgDateSpreadRequestBas(basVO);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}while(rBasVOList.size() != 0); 
		
		response.put("token", token);
		
		//TB_뿌리기_요청_기본 테이블에 요청 적재
		int insertBasVO = 0;
		basVO = new SpreadRequestBasVO(
				 token						//token
				,_room						//room
				,new Date()					//rgRequestDate
				,_user						//user
				,_rgTotalMoney				//rgTotalMoney
				,_rgUserCount				//rgUserCount
				);
		try {
			insertBasVO = spreadRequestBasDAO.insertSpreadRequestBas(basVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(insertBasVO != 1)
		{
			LOG.severe(this.getClass().toString());
		}
		
		//TB_뿌리기_요청_내역 테이블에 요청 적재
		int insertIzVO= 0;
		int rgMoney = _rgTotalMoney / _rgUserCount;
		SpreadRequestIzVO IzVO = new SpreadRequestIzVO(
				token						//token
				,_room						//room
				,0							//reqSeq
				,_user
				,new Date()					//rgRequestDate
				,rgMoney					//rgMoney
				,0							//rgUser
				,null);						//rgServeDate
		
		for ( int i = 1 ; i <= _rgUserCount; i ++)
		{
			IzVO.increaseReqSeq();
			if(i == _rgUserCount && (rgMoney * i) != _rgTotalMoney)
			{
				rgMoney += _rgTotalMoney - (rgMoney * i);
				IzVO.setRgMoney(rgMoney);
			}
			
			try {
				insertIzVO += spreadRequestIzDAO.insertSpreadRequestIz(IzVO);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		
		if(insertBasVO == 1
				|| insertIzVO == _rgUserCount)
		{
			response.put("responseCode", "200");
		}
		return response;
	}
	
	/**
	 *받기
	 *	1. TB_뿌리기요청기본(TB_SPREAD_REQUEST_BAS)받기 예외처리 
	 *		- 10분내에 (_user, _room, _token)에 해당하는 뿌리기요청이 있었는지 조회 
	 *
	 *	2. 받기 분배 할당
	 *
	 *  3. TB_SPREAD_REQUEST_BAS와 TB_SPREAD_REQUEST_IZ의 뿌리기 요청자가 다른경
	 */		
	public int serveSpreadRequest(int _user, String _room, String _token)
	{
		Date limitTime = dateService.changeMinDate(new Date(), limitMin);
		int responseCode = 0;
		
		//받기 예외처리  
		SpreadRequestBasVO basVO = new SpreadRequestBasVO(
				 _token					//token
				,_room					//room
				, limitTime);			//rgRequestDate
		List<SpreadRequestBasVO> rBasVOList = null; 
		try {
			rBasVOList = spreadRequestBasDAO.selectAfterRgDateSpreadRequestBas(basVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rBasVOList == null
				|| rBasVOList.size() != 1)
		{
			LOG.severe(this.getClass().toString());
		}

		//받기 예외처리 - 1. 해당 요청 room 미존재 
		if (rBasVOList.size() == 0)	
		{
			responseCode = -100;
			return responseCode;
		}
		//받기 예외처리 - 2. 뿌리기 요청 유저와 받기 요청 유저가 같음 
		else if(rBasVOList.get(0).getUser() == _user) 
		{
			responseCode = -200;
			return responseCode;
		}
		
		//받기 분배 할당 
		//자기자신이 아니면 분배된 것 중에 하나 수령
		SpreadRequestIzVO izVO = new SpreadRequestIzVO(
				_token								//token
				, _room								//room
				, rBasVOList.get(0).getUser()
				, limitTime);						//rgRequestDate
		SpreadRequestIzVO rIzVO = null;
		try {
			rIzVO = spreadRequestIzDAO.selectRowSpreadRequestIz(izVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//받기 예외처리 - 3. TB_SPREAD_REQUEST_BAS와 TB_SPREAD_REQUEST_IZ의 뿌리기 요청자가 다른경  
		if(rBasVOList.get(0).getUser() != rIzVO.getUser())
		{
			responseCode = -300;
			return responseCode;
		}
		
		int updateIzVO = 0;
		if( rIzVO != null && rBasVOList.size() == 1)
		{
			SpreadRequestIzVO uIzVO = new SpreadRequestIzVO(
					rIzVO.getToken()				//token
					,rIzVO.getRoom()				//room
					,rBasVOList.get(0).getUser()
					,rIzVO.getRgRequestDate());		//rgRequestDate
			uIzVO.setReqSeq(rIzVO.getReqSeq());		//reqSeq
			uIzVO.setRgUser(_user);					//rgUser
			uIzVO.setRgServeDate(new Date());		//rgServeDate
			
			try {
				updateIzVO = spreadRequestIzDAO.updateSpreadRequestIz(uIzVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if( updateIzVO == 1)
		{
			responseCode = 200;
		}
		
		return responseCode;
		
	}
	
	/**
	 *조회하기
	 *	1. 조회 예외처리 
	 *	2. 응답 데이터처리 
	 */
	public List<HashMap<String, String>> retrieveSpreadRequest(int _user, String _room, String _token)
	{
		// response
		List<HashMap<String, String>> responseList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> responseHashMap = new HashMap<String, String>();
		
		Date limitTime = dateService.changeDayDate(new Date(), limitDay);
 
		//조회 예외처리 - 1. 뿌리기를 요청한 사람만 조회 가
		SpreadRequestBasVO basVO = new SpreadRequestBasVO(
				 _token								//token
				,_room								//room
				, limitTime);						//rgRequestDate
		basVO.setUser(_user);
		List<SpreadRequestBasVO> rBasVOList= null; 
		
		try {
			rBasVOList = spreadRequestBasDAO.selectAfterRgDateSpreadRequestBas(basVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rBasVOList.equals(null))
		{
			LOG.severe(this.getClass().toString());
		}
		
		//조회 예외처리 - 1. 뿌리기를 요청하지 않은 사람이 조회시 예외처
		if ( rBasVOList.size() == 0)
		{
			responseHashMap.put("responseCode", "-100");
			responseList.add(responseHashMap);
			return responseList;
		}
		else
		{	//Add TB_SPREAD_REQUEST_Bas into Response List
			responseHashMap.put("responseCode", "200");
			responseHashMap.put("RG_REQUEST_DATE"	, rBasVOList.get(0).getRgRequestDate().toString());
			responseHashMap.put("RG_TOTAL_MONEY"	, Integer.toString(rBasVOList.get(0).getRgTotalMoney()));
		}
		//응답데이터처리 
		SpreadRequestIzVO izVO = new SpreadRequestIzVO(
				_token								//token
				, _room								//room
				, rBasVOList.get(0).getUser()
				, limitTime);						//rgRequestDate
		List<SpreadRequestIzVO> rIzVOList = null;
		try {
			rIzVOList = spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz(izVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rIzVOList.equals(null))
		{
			LOG.severe(this.getClass().toString());
		}
		
		// Add TB_SPREAD_REQUEST_IZ into Response List
		int serveTotalMoney = 0;
		List<HashMap<String, String>> rIzList = new ArrayList<HashMap<String,String>>();
		for( int j = 0 ; j < rIzVOList.size() ;j ++)
		{
			HashMap<String, String> rIzHashMap = new HashMap<String, String>();
			//받지 않은 분배건은 TB_뿌리기요청내역에 0으로 저장 
			if(rIzVOList.get(j).getRgUser() != 0)
			{
				serveTotalMoney += rIzVOList.get(j).getRgMoney();
				
				rIzHashMap.put("RG_SERVE_MONEY"	, Integer.toString(rIzVOList.get(j).getRgMoney()));
				rIzHashMap.put("RG_USER"		, Integer.toString(rIzVOList.get(j).getRgUser()));	
				
				rIzList.add(rIzHashMap);
			}
		}
		responseHashMap.put("SERVE_TOTAL_MONEY"	, Integer.toString(serveTotalMoney));
		responseHashMap.put("SERVE_LIST"		, rIzList.toString());
		
		responseList.add(responseHashMap);
	
		
		return responseList;
		
	}
	
}
