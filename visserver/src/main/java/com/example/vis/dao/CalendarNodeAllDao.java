package com.example.vis.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.vis.entitys.CalendarNodeAll;

public class CalendarNodeAllDao {
	@SuppressWarnings("unchecked")
	public <T> List<T> selectAlldate(NamedParameterJdbcTemplate jdbcTemplate){
		String sqlone="select * from CalendarnodeAll ";
		BeanPropertyRowMapper<CalendarNodeAll> rowMapper=new BeanPropertyRowMapper<CalendarNodeAll>(CalendarNodeAll.class);
		List<T> one=(List<T>) jdbcTemplate.query(sqlone, rowMapper);
		return one.size()>0?one:null;
	}
}
