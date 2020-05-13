package com.ueelab.extension.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author Yipeng.Liu
 */
@Mapper
public interface TestDao {

	@Select("SELECT * FROM t_client")
	Map<String, Object> test();
}
