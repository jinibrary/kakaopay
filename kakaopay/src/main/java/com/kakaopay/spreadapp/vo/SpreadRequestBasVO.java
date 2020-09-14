package com.kakaopay.spreadapp.vo;

import java.util.Date;

/**
 * @author sangjin
 *
 */
public class SpreadRequestBasVO {
	private String 	token;
	private String 	room;
	private Date 	rgRequestDate;
	private int 	user;
	private int 	rgTotalMoney;
	private int 	rgUserCount;
	
	
	public SpreadRequestBasVO(String token, String room, Date rgRequestDate) {
		super();
		this.token = token;
		this.room = room;
		this.rgRequestDate = rgRequestDate;
	}

	public SpreadRequestBasVO(String token, String room, Date rgRequestDate, int user, int rgTotalMoney,
			int rgUserCount) {
		super();
		this.token = token;
		this.room = room;
		this.rgRequestDate = rgRequestDate;
		this.user = user;
		this.rgTotalMoney = rgTotalMoney;
		this.rgUserCount = rgUserCount;
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

	public Date getRgRequestDate() {
		return rgRequestDate;
	}

	public void setRgRequestDate(Date rgRequestDate) {
		this.rgRequestDate = rgRequestDate;
	}
	
	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getRgTotalMoney() {
		return rgTotalMoney;
	}

	public void setRgTotalMoney(int rgTotalMoney) {
		this.rgTotalMoney = rgTotalMoney;
	}

	public int getRgUserCount() {
		return rgUserCount;
	}

	public void setRgUserCount(int rgUserCount) {
		this.rgUserCount = rgUserCount;
	}

	@Override
	public String toString() {
		return "SpreadRequestBasVO [token=" + token + ", room=" + room + ", rgRequestDate=" + rgRequestDate + ", user="
				+ user + ", rgTotalMoney=" + rgTotalMoney + ", rgUserCount=" + rgUserCount + "]";
	}
	
	
	
}
