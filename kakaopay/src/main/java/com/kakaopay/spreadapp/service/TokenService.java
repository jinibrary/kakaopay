package com.kakaopay.spreadapp.service;

public interface TokenService {
	/**
	 * @param length	Token을 생성할 길이 
	 * @return
	 */
	public String tokenGenerator(int length);
}
