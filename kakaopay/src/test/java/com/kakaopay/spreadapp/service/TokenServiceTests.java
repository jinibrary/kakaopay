package com.kakaopay.spreadapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakaopay.spreadapp.service.TokenService;
@SpringBootTest
public class TokenServiceTests {
	@Autowired
	TokenService tokenService;
	
	/**
	 * 	public String tokenGenerator(int length)
	 *	입력받은 length만큼의 임의의 토큰을 생성 
	 */
	@Test
	void tokenGeneratorTest()
	{
		System.out.println("START tokenGeneratorTest()");
		
		{
			System.out.println("PARAM 3 :		" + tokenService.tokenGenerator(3));
			
			System.out.println("PARAM 5 :		" + tokenService.tokenGenerator(5));
		}
		
		System.out.println("END tokenGeneratorTest()");
	}
}
