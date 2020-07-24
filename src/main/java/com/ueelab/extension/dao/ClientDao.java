package com.ueelab.extension.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ueelab.extension.entity.ClientEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;



@Mapper
public interface ClientDao extends BaseMapper<ClientEntity> {

	@Select("SELECT * FROM t_client WHERE is_delete = 0")
	List<ClientEntity> selectAll();

	@Select("SELECT * FROM t_client WHERE client_id = #{clientId} AND is_delete = 0")
	ClientEntity selectByClientId(String clientId);
}
