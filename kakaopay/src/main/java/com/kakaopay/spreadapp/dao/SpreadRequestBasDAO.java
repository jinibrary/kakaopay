package com.kakaopay.spreadapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakaopay.spreadapp.vo.SpreadRequestBasVO;

@Mapper
public interface SpreadRequestBasDAO {
	
	/**
	 * @param spreadRequestBasVO
	 *	INSERT INTO 
			TB_SPREAD_REQUEST_BAS
		VALUES(
			 #{token}
			,#{room}
			,#{requestRgDate}
			,#{user}
			,#{rgTotalMoney}
			,#{rgUserCount})
		;
	 * @return	
	 * @throws Exception
	 */
	public Integer insertSpreadRequestBas(SpreadRequestBasVO spreadRequestBasVO) throws Exception;
	
	/**
	 * @param spreadRequestBasVO
	 * 	SELECT 
			*
		FROM
			TB_SPREAD_REQUEST_BAS
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			<if test="user != 0">
       		AND USER = #{user}
    		</if>
			AND REQUEST_RG_DATE >= #{requestRgDate}
		;
	 * @return
	 * @throws Exception
	 */
	public List<SpreadRequestBasVO> selectAfterRgDateSpreadRequestBas(SpreadRequestBasVO spreadRequestBasVO) throws Exception;
}
