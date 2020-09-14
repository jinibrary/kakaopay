package com.kakaopay.spreadapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakaopay.spreadapp.vo.SpreadRequestIzVO;

@Mapper
public interface SpreadRequestIzDAO {
	/**
	 * @param spreadRequestIzVO
	 * INSERT INTO
			TB_SPREAD_REQUEST_IZ
		VALUES(
			 #{token}
			,#{room}
			,#{reqSeq}
			,#{requestRgDate}
			,#{rgMoney}
			,#{rgUser}
			,#{serveRgDate})
		;
	 * @return
	 * @throws Exception
	 */
	public Integer insertSpreadRequestIz(SpreadRequestIzVO spreadRequestIzVO) throws Exception;
	
	/**
	 * @param spreadReuqestIzVO
	 * SELECT 
			*
		FROM 
			TB_SPREAD_REQUEST_IZ
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			AND REQUEST_RG_DATE >= #{requestRgDate}
		;
	 * @return
	 * @throws Exception
	 */
	public List<SpreadRequestIzVO> selectAfterRgDateSpreadRequestIz(SpreadRequestIzVO spreadReuqestIzVO) throws Exception;
	
	/**
	 * @param spreadReuqestIzVO
	 * SELECT 
			*
		FROM
			TB_SPREAD_REQUEST_IZ
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			AND RG_USER = 0 
			AND REQUEST_RG_DATE >= #{requestRgDate}
		ORDER BY REQUEST_RG_DATE DESC
		LIMIT 1
		;
	 * @return
	 * @throws Exception
	 */
	public SpreadRequestIzVO selectRowSpreadRequestIz(SpreadRequestIzVO spreadReuqestIzVO) throws Exception;
	
	/**
	 * @param spreadReuqestIzVO
	 * UPDATE
			TB_SPREAD_REQUEST_IZ
		SET
			 RG_USER = #{rgUser}
			,RG_SERVE_DATE = #{rgServeDate}
		WHERE
				TOKEN = #{token}
			AND ROOM = #{room}
			AND REQ_SEQ = #{reqSeq}
			AND RG_REQUEST_DATE >= #{rgRequestDate}
		;
	 * @return
	 * @throws Exception
	 */
	public Integer updateSpreadRequestIz(SpreadRequestIzVO spreadReuqestIzVO) throws Exception;
}
