package com.example.vis.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.vis.entitys.CalendarStudent;

public class CalendarStudentDao {
	@SuppressWarnings("unchecked")
	public <T> List<T> SelectSomeDay(NamedParameterJdbcTemplate jdbcTemplate,String day,String tablename){
		
		String sql="select * from "+tablename;
		BeanPropertyRowMapper<CalendarStudent> rowMapper=new BeanPropertyRowMapper<CalendarStudent>(CalendarStudent.class);
		List<T> list=(List<T>)jdbcTemplate.query(sql, rowMapper);
		return list.size()>0? list:null;
		
		
	}
}
