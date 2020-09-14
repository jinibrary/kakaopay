package com.kakaopay.spreadapp.vo;

import java.util.Date;

/**
 * @author sangjin
 *
 */
public class SpreadRequestIzVO {
	private String 	token;
	private String 	room;
	private int 	reqSeq;
	private int		user;
	private Date 	rgRequestDate;
	private int 	rgMoney;
	private int 	rgUser;
	private Date 	rgServeDate;

	public SpreadRequestIzVO(String token, String room,int reqSeq, int user, Date rgRequestDate,  int rgMoney, int rgUser,
			Date rgServeDate) {
		super();
		this.token = token;
		this.room = room;
		this.user = user;
		this.rgRequestDate = rgRequestDate;
		this.reqSeq = reqSeq;
		this.rgMoney = rgMoney;
		this.rgUser = rgUser;
		this.rgServeDate = rgServeDate;
	}


	public SpreadRequestIzVO(String token, String room, int user, Date rgRequestDate) {
		super();
		this.token = token;
		this.room = room;
		this.user = user;
		this.rgRequestDate = rgRequestDate;
	}


	public void increaseReqSeq()
	{
		reqSeq += 1;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	public int getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(int reqSeq) {
		this.reqSeq = reqSeq;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
	
	public Date getRgRequestDate() {
		return rgRequestDate;
	}

	public void setRgRequestDate(Date rgRequestDate) {
		this.rgRequestDate = rgRequestDate;
	}
	
	public int getRgMoney() {
		return rgMoney;
	}

	public void setRgMoney(int rgMoney) {
		this.rgMoney = rgMoney;
	}

	public int getRgUser() {
		return rgUser;
	}

	public void setRgUser(int rgUser) {
		this.rgUser = rgUser;
	}

	public Date getRgServeDate() {
		return rgServeDate;
	}

	public void setRgServeDate(Date rgServeDate) {
		this.rgServeDate = rgServeDate;
	}


	@Override
	public String toString() {
		return "SpreadRequestIzVO [token=" + token + ", room=" + room + ", reqSeq=" + reqSeq + ", user=" + user
				+ ", rgRequestDate=" + rgRequestDate + ", rgMoney=" + rgMoney + ", rgUser=" + rgUser + ", rgServeDate="
				+ rgServeDate + "]";
	}
}
