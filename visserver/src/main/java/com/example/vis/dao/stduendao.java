package com.example.vis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.vis.entitys.studentInfo;

public class stduendao {
	
	public List<studentInfo> selectAll(NamedParameterJdbcTemplate jdbcTemplate){
		String sql="Select * from data1";
		BeanPropertyRowMapper<studentInfo> rowMapper=new BeanPropertyRowMapper<studentInfo>(studentInfo.class);
		List<studentInfo> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<studentInfo> selectAlloneColumn(NamedParameterJdbcTemplate jdbcTemplate,ArrayList<String> ColumnNames){
		String sql="Select ";
		int index=0;
		if(ColumnNames.size()>0) {
			for(String c :ColumnNames) {
				if(index==0) {
					sql=sql+c;
				}
				else {
					sql=sql+","+c;
				}
				index++;
			}
			 sql=sql+"  from data1 ";
		}
		else {
			sql=sql+" * from data1";
		}
		BeanPropertyRowMapper<studentInfo> rowMapper=new BeanPropertyRowMapper<studentInfo>(studentInfo.class);
		List<studentInfo> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<studentInfo> selectSomeColumn(NamedParameterJdbcTemplate jdbcTemplate ,HashMap<String, String> ColumnMap){
		String sql="Select * from data1 ";
		if(ColumnMap.keySet().size()>0) {
			sql=sql+"where ";
			int index=0;
			for(String key : ColumnMap.keySet()) {
				if(index==0) {
					sql=sql+key+"='"+ColumnMap.get(key)+"' ";
				}
				else {
					sql=sql+" and "+ key+"='"+ColumnMap.get(key)+"' ";
				}
				index++;
			}
		}
		System.out.println(sql);
		BeanPropertyRowMapper<studentInfo> rowMapper=new BeanPropertyRowMapper<studentInfo>(studentInfo.class);
		List<studentInfo> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
}
