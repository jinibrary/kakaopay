package com.kakaopay.spreadapp.vo;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakaopay.spreadapp.service.TokenService;
@SpringBootTest
public class SpreadVOTest {

	@Autowired
	TokenService tokenService;
	@Test
	public void SpreadRequestBasVOTest()
	{
		
		System.out.println("START SpreadRequestBasVOTest()");
		//SpreadRequestBasVOTest Cunstruct Test
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			Date 	rgRequestDate 	=	new Date();
			int 	user			= 	 (int)(Math.random() * 1000);
			int 	rgTotalMoney	=	(int)(Math.random() * 100000);
			int 	rgUserCount		=	(int)(Math.random() * 10) +1;
			
			System.out.println(
					"token : " 				+ token
					+ "	room :" 				+ room
					+ "	rgRequestDate : " 	+ rgRequestDate
					+ "	user : " 				+ 	user
					+ "	rgTotalMoney :" 		+ rgTotalMoney
					+ "	rgUserCount : " 		+ rgUserCount);
			
			SpreadRequestBasVO basVO = new SpreadRequestBasVO(
					token
					, room
					, rgRequestDate
					, user
					, rgTotalMoney
					, rgUserCount);
			
			System.out.println(basVO);
		
		
		System.out.println("END SpreadRequestBasVOTest()");
	}
	@Test
	public void SPreadRequestIzVOTest()
	{
		
		System.out.println("START SpreadRequestBasVOTest()");
		
		SpreadRequestIzVO izVO = null;
		
		{	//SpreadRequestBasVOTest Cunstruct Test
			String 	token			=	tokenService.tokenGenerator(3);
			String 	room			=	tokenService.tokenGenerator(3);
			int 	reqSeq			=	(int)(Math.random() * 10);
			int 	user 			=	(int)(Math.random() * 1000);
			Date 	rgRequestDate	= 	new Date();
			int 	rgMoney			=	(int)(Math.random() * 100000);
			int 	rgUser			=	0;
			Date 	rgServeDate		=	null;
			
			System.out.println(
					"token : " 				+ token
					+ "	room : " 			+ room
					+ "	reqSeq : " 			+ reqSeq
					+ " user : "			+ user
					+ "	rgRequestDate : " 	+ rgRequestDate
					+ "	rgMoney : " 			+ rgMoney
					+ "	rgServeDate : " 		+ rgServeDate);
			
			izVO = new SpreadRequestIzVO(	
					token
					, room
					, reqSeq
					, user
					, rgRequestDate
					, rgMoney
					, rgUser
					, rgServeDate);
			
			System.out.println(izVO);
		}
		{ // increasReqSeq() TEST
			System.out.println("START increaseReqSeq() TEST");
			
			System.out.println("BEFORE reqSeq		:		" 			+ izVO.getReqSeq());
			izVO.increaseReqSeq();
			System.out.println("AFTER reqSeq		:		" 			+ izVO.getReqSeq());
					
			System.out.println("END increaseReqSeq() TEST");
		}
		System.out.println("END SpreadRequestBasVOTest()");
	}
}
