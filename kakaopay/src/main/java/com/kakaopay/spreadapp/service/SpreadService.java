package com.kakaopay.spreadapp.service;

import java.util.HashMap;
import java.util.List;

public interface SpreadService {
	/**
	 * @param _user			뿌리기 요청 유저 ID
	 * @param _room			뿌리기 요청 방 ID
	 * @param _rgTotalMoney	뿌리기 요청 금액
	 * @param _rgUserCount	뿌리기 요청 인
	 * @return
	 */
	public HashMap<String, String> registSpreadRequest(int _user, String _room, int _rgTotalMoney, int _rgUserCount);
	/**
	 * @param _user			뿌리기 요청 유저 ID
	 * @param _room			뿌리기 요청 방 ID
	 * @param _token		뿌리기 요청 token
	 *받기 요청
	 *	1. 
	 * @return
	 */
	public int serveSpreadRequest(int _user, String _room, String _token);
	/**
	 * @param _user			뿌리기 요청 유저 ID
	 * @param _room			뿌리기 요청 방 ID
	 * @param _token		뿌리기 요청 token
	 * @return
	 */
	public List<HashMap<String, String>> retrieveSpreadRequest(int _user, String _room, String _token);
}
