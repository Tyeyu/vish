package com.example.vis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.vis.entitys.AccessRecord;
import com.example.vis.entitys.ExpensesRecord;


public class AccessRecordDao {

	public List<AccessRecord> selectAll(NamedParameterJdbcTemplate jdbcTemplate){
		String sql="Select * from data3";
		BeanPropertyRowMapper<AccessRecord> rowMapper=new BeanPropertyRowMapper<AccessRecord>(AccessRecord.class);
		List<AccessRecord> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<AccessRecord> selectAlloneColumn(NamedParameterJdbcTemplate jdbcTemplate,ArrayList<String> ColumnNames){
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
			 sql=sql+"  from data3 ";
		}
		else {
			sql=sql+" * from data3";
		}
		BeanPropertyRowMapper<AccessRecord> rowMapper=new BeanPropertyRowMapper<AccessRecord>(AccessRecord.class);
		List<AccessRecord> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<AccessRecord> selectSomeColumn(NamedParameterJdbcTemplate jdbcTemplate ,HashMap<String, String> ColumnMap){
		String sql="Select * from data3 ";
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
		BeanPropertyRowMapper<AccessRecord> rowMapper=new BeanPropertyRowMapper<AccessRecord>(AccessRecord.class);
		List<AccessRecord> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<AccessRecord> selectlikedate(NamedParameterJdbcTemplate jdbcTemplate,String date){
		String sql="select * from data3 where Date like '"+date+"%'";
		BeanPropertyRowMapper<AccessRecord> rowMapper=new BeanPropertyRowMapper<AccessRecord>(AccessRecord.class);
		List<AccessRecord> Acs=jdbcTemplate.query(sql, rowMapper);
		return  Acs.size()>0? Acs:null;
	}
}
