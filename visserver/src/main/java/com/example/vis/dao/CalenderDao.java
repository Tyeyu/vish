package com.example.vis.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.vis.entitys.Calendardataone;
import com.example.vis.entitys.Calendardatatwo;

public class CalenderDao {
	public <T> List<T> selectlikedate(NamedParameterJdbcTemplate jdbcTemplate,String date){
		String sqlone="select * from Calendardata1 where Date like '"+date+"%'";
		String sqltwo="select * from Calendardata1 where Date like '"+date+"%'";
		BeanPropertyRowMapper<Calendardataone> rowMapper=new BeanPropertyRowMapper<Calendardataone>(Calendardataone.class);
		List<T> one=(List<T>) jdbcTemplate.query(sqlone, rowMapper);
		BeanPropertyRowMapper<Calendardatatwo> rowMapper2=new BeanPropertyRowMapper<Calendardatatwo>(Calendardatatwo.class);
		List<T> two=(List<T>) jdbcTemplate.query(sqltwo, rowMapper2);
		one.addAll(two);
		return one.size()>0?one:null;
	}
}
