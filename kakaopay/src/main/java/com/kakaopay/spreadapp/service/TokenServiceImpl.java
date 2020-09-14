package com.kakaopay.spreadapp.service;

import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService{
	
	/**
	 *	입력받은 length만큼의 임의의 토큰을 생성 
	 */
	public String tokenGenerator(int length)
	{
		int index = 0;
		char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		StringBuffer sb = new StringBuffer();
		for ( int i = 0 ; i < length ; i ++)
		{
			index = (int) (charSet.length * Math.random());
			sb.append(charSet[index]);
		}
		return sb.toString();
	}
}
