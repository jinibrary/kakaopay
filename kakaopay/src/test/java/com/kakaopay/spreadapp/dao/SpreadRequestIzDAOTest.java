package com.kakaopay.spreadapp.dao;

import java.util.Date;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakaopay.spreadapp.service.DateService;
import com.kakaopay.spreadapp.service.TokenService;
import com.kakaopay.spreadapp.vo.SpreadRequestBasVO;
import com.kakaopay.spreadapp.vo.SpreadRequestIzVO;
@SpringBootTest
public class SpreadRequestIzDAOTest {

	@Autowired
	SpreadRequestIzDAO spreadRequestIzDAO;
	@Autowired
	TokenService tokenService;
	@Autowired
	DateService dateService;
	static private int limitMin = -10;
	static private int limitDay = -7;
	/*
	 * 	id				=	"insertSpreadRequestIz"
		parameterType	=	"com.kakaopay.spreadapp.vo.SpreadRequestIzVO">
		insertSpreadRequestIz()
		INSERT INTO
			TB_SPREAD_REQUEST_IZ
		VALUES(
			 #{token}
			,#{room}
			,#{reqSeq}
			,#{user}
			,#{rgRequestDate}
			,#{rgMoney}
			,#{rgUser}
			,#{rgServeDate})
		;
	*/
	@Test
	void insertSpreadRequestIzTest()
	{
		System.out.println("START insertSpreadRequestIzTest()");
		{
			int _user = (int)(Math.random() * 1000);
			int _rgUserCount			=	(int)(Math.random() * 10) + 1;
			int _rgTotalMoney		=	(int)(Math.random() * 100000);
			
			SpreadRequestIzVO izVO = null;
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			int 	reqSeq			=	0;
			Date 	rgRequestDate	= 	new Date();
			int 	rgMoney			=	_rgTotalMoney / _rgUserCount;
			int 	rgUser			=	0;
			Date 	rgServeDate		=	null;
			
			izVO = new SpreadRequestIzVO(	
					token
					, room
					, reqSeq
					, _user
					, rgRequestDate
					, rgMoney
					, rgUser
					, rgServeDate);
			System.out.println("_rgUserCount : " + _rgUserCount + "_rgTotalMoney : " +_rgTotalMoney + "_user : " + _user);
			System.out.println(izVO);
			
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz START");
			int iIzVO = 0;
			for ( int i = 1 ; i <= _rgUserCount ; i ++)
			{
				izVO.increaseReqSeq();
				if ( _rgUserCount == i 
						&& _rgTotalMoney != izVO.getRgMoney() * i)
				{
					izVO.setRgMoney(_rgTotalMoney - izVO.getRgMoney() * (i - 1));
				}
				try {
					iIzVO = spreadRequestIzDAO.insertSpreadRequestIz(izVO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz RESULT : " + iIzVO);
			
			String 	token2			=	token;
			String 	room2			=	room;
			Date 	rgRequestDate2 	=	dateService.changeMinDate(rgRequestDate, limitMin);
			
			SpreadRequestIzVO izVO2 = new SpreadRequestIzVO(
					token2
					, room2
					, _user
					, rgRequestDate2);
			
			System.out.println("print SpreadRequestIzVO2 : " + izVO2);
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz START ");
			List<SpreadRequestIzVO> rIzVOList = null;
			try {
				rIzVOList= spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz(izVO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz Result : ");
			for ( int i = 0 ; i < rIzVOList.size() ; i ++)
			{
				System.out.println("rIzVoList " + i + "번째 IzVO	:	" + rIzVOList.get(i));
			}
		}
		System.out.println("END insertSpreadRequestIzTest()");
	}
	/*
	 * 	id				=	"selectAfterRgDateSpreadRequestIz"
		parameterType	=	"com.kakaopay.spreadapp.vo.SpreadRequestIzVO"
		resultType		=	"com.kakaopay.spreadapp.vo.SpreadRequestIzVO">
		SELECT 
			*
		FROM 
			TB_SPREAD_REQUEST_IZ
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			AND USER = #{user}
			AND RG_REQUEST_DATE >= #{rgRequestDate}
		;
	*/
	@Test
	void selectAfterRgDateSpreadRequestIzTest()
	{
		System.out.println("START selectAfterRgDateSpreadRequestIzTest()");
		{
			int _user = (int)(Math.random() * 1000);
			int _rgUserCount			=	(int)(Math.random() * 10) + 1;
			int _rgTotalMoney		=	(int)(Math.random() * 100000);
			
			SpreadRequestIzVO izVO = null;
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			int 	reqSeq			=	0;
			Date 	rgRequestDate	= 	new Date();
			int 	rgMoney			=	_rgTotalMoney / _rgUserCount;
			int 	rgUser			=	0;
			Date 	rgServeDate		=	null;
			
			izVO = new SpreadRequestIzVO(	
					token
					, room
					, reqSeq
					,_user
					, rgRequestDate
					, rgMoney
					, rgUser
					, rgServeDate);
			System.out.println("_rgUserCount : " + _rgUserCount + "_rgTotalMoney : " +_rgTotalMoney);
			System.out.println(izVO);
			
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz START");
			int iIzVO = 0;
			for ( int i = 1 ; i <= _rgUserCount ; i ++)
			{
				izVO.increaseReqSeq();
				if ( _rgUserCount == i 
						&& _rgTotalMoney != izVO.getRgMoney() * i)
				{
					izVO.setRgMoney(_rgTotalMoney - izVO.getRgMoney() * (i - 1));
				}
				try {
					iIzVO = spreadRequestIzDAO.insertSpreadRequestIz(izVO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz RESULT : " + iIzVO);
			
			String 	token2			=	token;
			String 	room2			=	room;
			Date 	rgRequestDate2 	=	dateService.changeMinDate(rgRequestDate, limitMin);
			
			SpreadRequestIzVO izVO2 = new SpreadRequestIzVO(
					token2
					, room2
					,_user
					, rgRequestDate2);
			
			System.out.println("print SpreadRequestIzVO2 : " + izVO2);
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz START ");
			List<SpreadRequestIzVO> rIzVOList = null;
			try {
				rIzVOList= spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz(izVO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz Result : ");
			for ( int i = 0 ; i < rIzVOList.size() ; i ++)
			{
				System.out.println("rIzVoList " + i + "번째 IzVO	:	" + rIzVOList.get(i));
			}
		}
		System.out.println("END selectAfterRgDateSpreadRequestIzTest()");
	}
	/*
	 *  id				=	"selectRowSpreadRequestIz"
		parameterType	=	"com.kakaopay.spreadapp.vo.SpreadRequestIzVO"
		resultType 		=	"com.kakaopay.spreadapp.vo.SpreadRequestIzVO">
	 *  
		SELECT 
			*
		FROM
			TB_SPREAD_REQUEST_IZ
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			AND RG_USER = 0 
			AND USER = #{user}
			AND RG_REQUEST_DATE >= #{rgRequestDate}
		LIMIT 1
		;
	*/
	@Test
	void selectRowSpreadRequestIzTest()
	{
		System.out.println("START selectRowSpreadRequestIzTest()");
		{
			int _user = (int)(Math.random() * 1000);
			int _rgUserCount			=	(int)(Math.random() * 10) + 1;
			int _rgTotalMoney		=	(int)(Math.random() * 100000);
			
			SpreadRequestIzVO izVO = null;
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			int 	reqSeq			=	0;
			Date 	rgRequestDate	= 	new Date();
			int 	rgMoney			=	_rgTotalMoney / _rgUserCount;
			int 	rgUser			=	0;
			Date 	rgServeDate		=	null;
			
			izVO = new SpreadRequestIzVO(	
					token
					, room
					, reqSeq
					, _user
					, rgRequestDate
					, rgMoney
					, rgUser
					, rgServeDate);
			System.out.println("_rgUserCount : " + _rgUserCount + "_rgTotalMoney : " +_rgTotalMoney);
			System.out.println(izVO);
			
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz START");
			int iIzVO = 0;
			for ( int i = 1 ; i <= _rgUserCount ; i ++)
			{
				izVO.increaseReqSeq();
				if ( _rgUserCount == i 
						&& _rgTotalMoney != izVO.getRgMoney() * i)
				{
					izVO.setRgMoney(_rgTotalMoney - izVO.getRgMoney() * (i - 1));
				}
				try {
					iIzVO = spreadRequestIzDAO.insertSpreadRequestIz(izVO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz RESULT : " + iIzVO);
			
			String 	token2			=	token;
			String 	room2			=	room;
			Date 	rgRequestDate2 	=	dateService.changeMinDate(rgRequestDate, limitMin);
			
			SpreadRequestIzVO izVO2 = new SpreadRequestIzVO(
					token2
					, room2
					, _user
					, rgRequestDate2);
			
			System.out.println("print SpreadRequestIzVO2 : " + izVO2);
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz START ");
			List<SpreadRequestIzVO> rIzVOList = null;
			try {
				rIzVOList= spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz(izVO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz Result : ");
			for ( int i = 0 ; i < rIzVOList.size() ; i ++)
			{
				System.out.println("rIzVoList " + i + "번째 IzVO	:	" + rIzVOList.get(i));
			}
			
			System.out.println("spreadRequestIzDAO.selectRowSpreadRequestIz START ");
			SpreadRequestIzVO rIzVO = null;
			try {
				rIzVO= spreadRequestIzDAO.selectRowSpreadRequestIz(izVO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestIzDAO.selectRowSpreadRequestIz Result : " + rIzVO);
		}
		System.out.println("END selectRowSpreadRequestIzTest()");
	}
	/*
	 * 	id				=	"updateSpreadRequestIz"
		parameterType	=	"com.kakaopay.spreadapp.vo.SpreadRequestIzVO">
		UPDATE
			TB_SPREAD_REQUEST_IZ
		SET
			 RG_USER = #{rgUser}
			,RG_SERVE_DATE = #{rgServeDate}
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			AND REQ_SEQ = #{reqSeq}
			AND USER = #{user}
			AND RG_REQUEST_DATE >= #{rgRequestDate}
		;
	*/
	@Test
	void updateSpreadRequestIzTest()
	{
		System.out.println("START updateSpreadRequestIzTest()");
		{
			int _user = (int)(Math.random() * 1000);
			int _rgUserCount			=	(int)(Math.random() * 10) + 1;
			int _rgTotalMoney		=	(int)(Math.random() * 100000);
			
			SpreadRequestIzVO izVO = null;
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			int 	reqSeq			=	0;
			Date 	rgRequestDate	= 	new Date();
			int 	rgMoney			=	_rgTotalMoney / _rgUserCount;
			int 	rgUser			=	0;
			Date 	rgServeDate		=	null;
			
			izVO = new SpreadRequestIzVO(	
					token
					, room
					, reqSeq
					,_user
					, rgRequestDate
					, rgMoney
					, rgUser
					, rgServeDate);
			System.out.println("_rgUserCount : " + _rgUserCount + "_rgTotalMoney : " +_rgTotalMoney);
			System.out.println(izVO);
			
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz START");
			int iIzVO = 0;
			for ( int i = 1 ; i <= _rgUserCount ; i ++)
			{
				izVO.increaseReqSeq();
				if ( _rgUserCount == i 
						&& _rgTotalMoney != izVO.getRgMoney() * i)
				{
					izVO.setRgMoney(_rgTotalMoney - izVO.getRgMoney() * (i - 1));
				}
				try {
					iIzVO = spreadRequestIzDAO.insertSpreadRequestIz(izVO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("spreadRequestIzDAO.insertSpreadRequestIz RESULT : " + iIzVO);
			
			String 	token2			=	token;
			String 	room2			=	room;
			Date 	rgRequestDate2 	=	dateService.changeMinDate(rgRequestDate, limitMin);
			
			SpreadRequestIzVO izVO2 = new SpreadRequestIzVO(
					token2
					, room2
					,_user
					, rgRequestDate2);
			
			System.out.println("print SpreadRequestIzVO2 : " + izVO2);
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz START ");
			List<SpreadRequestIzVO> rIzVOList = null;
			try {
				rIzVOList= spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz(izVO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz Result : ");
			for ( int i = 0 ; i < rIzVOList.size() ; i ++)
			{
				System.out.println("rIzVoList " + i + "번째 IzVO	:	" + rIzVOList.get(i));
			}
			
			String 	token3			=	token;
			String 	room3			=	room;
			Date 	rgRequestDate3 	=	dateService.changeMinDate(rgRequestDate, limitMin);;
			
			int		reqSeq3			= 	1;
			int		rgUser3			= 	(int)(Math.random() * 1000);
			Date	rgServeDate3	=	dateService.changeMinDate(new Date(), 1);
			SpreadRequestIzVO izVO3 = new SpreadRequestIzVO(
					token3
					, room3
					,_user
					, rgRequestDate3);
			izVO3.setReqSeq(reqSeq3);
			izVO3.setRgUser(rgUser3);
			izVO3.setRgServeDate(rgServeDate3);
			
			System.out.println("print SpreadRequestIzVO3 : " + izVO3);
			System.out.println("spreadRequestIzDAO.updateSpreadRequestIz START ");
			
			int updateIzVO = 0;
			try {
				updateIzVO= spreadRequestIzDAO.updateSpreadRequestIz(izVO3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestIzDAO.updateSpreadRequestIz Result : " + updateIzVO);

			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz START ");
			List<SpreadRequestIzVO> rIzVOList2 = null;
			try {
				rIzVOList2 = spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz(izVO3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestIzDAO.selectAfterRgDateSpreadRequestIz Result : ");
			for ( int i = 0 ; i < rIzVOList2.size() ; i ++)
			{
				System.out.println("rIzVOList " + i + "번째 IzVO	:	" + rIzVOList2.get(i));
			}
		}
		System.out.println("END updateSpreadRequestIzTest()");
	}
}
