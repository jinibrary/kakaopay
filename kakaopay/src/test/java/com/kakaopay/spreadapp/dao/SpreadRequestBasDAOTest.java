package com.kakaopay.spreadapp.dao;

import java.util.Date;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakaopay.spreadapp.service.DateService;
import com.kakaopay.spreadapp.service.TokenService;
import com.kakaopay.spreadapp.vo.SpreadRequestBasVO;
@SpringBootTest
public class SpreadRequestBasDAOTest {

	@Autowired
	SpreadRequestBasDAO spreadRequestBASDao;
	@Autowired
	TokenService tokenService;
	@Autowired
	DateService dateService;
	static private int limitMin = -10;
	static private int limitDay = -7;
	
	/*
	 * id				=	"insertSpreadRequestBas"
	 * parameterType	=	"com.kakaopay.spreadapp.vo.SpreadRequestBasVO"
			INSERT INTO 
				TB_SPREAD_REQUEST_BAS
			VALUES(
				 #{token}
				,#{room}
				,#{rgRequestDate}
				,#{user}
				,#{rgTotalMoney}
				,#{rgUserCount})
			;
	*/
	@Test	
	void insertSpreadRequestBasTest()
	{
		System.out.println("START insertSpreadRequestBasTest()");
		{
			//SpreadRequestBasVOTest Cunstruct Test
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			Date 	rgRequestDate 	=	new Date();
			int 	user			= 	(int)(Math.random() * 1000);
			int 	rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	rgUserCount		=	(int)(Math.random() * 10) + 1;
			
			SpreadRequestBasVO basVO = new SpreadRequestBasVO(
					token
					, room
					, rgRequestDate
					, user
					, rgTotalMoney
					, rgUserCount);
			
			System.out.println(basVO);
			
			System.out.println("spreadRequestDao.insertSpreadRequestBas START");
			int iBasVO = 0;
			try {
				iBasVO = spreadRequestBASDao.insertSpreadRequestBas(basVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestDao.insertSpreadRequestBas RESULT : " + iBasVO);
			
			String 	token2			=	token;
			String 	room2			=	room;
			Date 	rgRequestDate2 	=	dateService.changeMinDate(rgRequestDate, limitMin);
			
			SpreadRequestBasVO basVO2 = new SpreadRequestBasVO(
					token2
					, room2
					, rgRequestDate2);
			
			System.out.println("print SpreadRequestBasVO2 : " + basVO2);
			System.out.println("spreadRequestDao.selectAfterRgDateSpreadRequestBas START ");
			List<SpreadRequestBasVO> rBasVOList = null;
			try {
				rBasVOList= spreadRequestBASDao.selectAfterRgDateSpreadRequestBas(basVO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestDao.selectAfterRgDateSpreadRequestBas Result : ");
			for ( int i = 0 ; i < rBasVOList.size() ; i ++)
			{
				System.out.println("rBasVoList " + i + "번째 BasVO	:	" + rBasVOList.get(i));
			}
			
		}
		System.out.println("END insertSpreadRequestBasTest()");

	}
	/*
	 * id				=	"selectAfterRgDateSpreadRequestBas"
	 * parameterType	=	"com.kakaopay.spreadapp.vo.SpreadRequestBasVO"
	 * resultType 		=	"com.kakaopay.spreadapp.vo.SpreadRequestBasVO"
		SELECT 
			*
		FROM
			TB_SPREAD_REQUEST_BAS
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			<if test="user != 0">
       		AND USER = #{user}
    		</if>
			AND RG_REQUEST_DATE >= #{rgRequestDate}
		;
	*/
	@Test
	void selectAfterRgDateSpreadRequestBas()
	{
		System.out.println("START selectAfterRgDateSpreadRequestBas()");
		{
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			Date 	rgRequestDate 	=	new Date();
			int 	user			= 	(int)(Math.random() * 1000);
			int 	rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	rgUserCount		=	(int)(Math.random() * 10) + 1;
			
			SpreadRequestBasVO basVO = new SpreadRequestBasVO(
					token
					, room
					, rgRequestDate
					, user
					, rgTotalMoney
					, rgUserCount);
			
			System.out.println(basVO);
			
			System.out.println("spreadRequestDao.insertSpreadRequestBas START");
			int iBasVO = 0;
			try {
				iBasVO = spreadRequestBASDao.insertSpreadRequestBas(basVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestDao.insertSpreadRequestBas RESULT : " + iBasVO);
			
			String 	token2			=	token;
			String 	room2			=	room;
			Date 	rgRequestDate2 	=	dateService.changeMinDate(rgRequestDate, limitMin);
			
			SpreadRequestBasVO basVO2 = new SpreadRequestBasVO(
					token2
					, room2
					, rgRequestDate2);
			
			System.out.println("print SpreadRequestBasVO2 : " + basVO2);
			System.out.println("spreadRequestDao.selectAfterRgDateSpreadRequestBas START ");
			List<SpreadRequestBasVO> rBasVOList = null;
			try {
				rBasVOList= spreadRequestBASDao.selectAfterRgDateSpreadRequestBas(basVO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("spreadRequestDao.selectAfterRgDateSpreadRequestBas Result : ");
			for ( int i = 0 ; i < rBasVOList.size() ; i ++)
			{
				System.out.println("rBasVoList " + i + "번째 BasVO	:	" + rBasVOList.get(i));
			}
			
		}
		System.out.println("END selectAfterRgDateSpreadRequestBas()");
	}
}
