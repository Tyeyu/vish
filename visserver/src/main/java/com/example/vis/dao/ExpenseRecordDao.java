package com.example.vis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.vis.entitys.ExpensesRecord;
import com.example.vis.entitys.studentInfo;



public class ExpenseRecordDao {
	public List<ExpensesRecord> selectAll(NamedParameterJdbcTemplate jdbcTemplate){
		String sql="Select * from data2";
		BeanPropertyRowMapper<ExpensesRecord> rowMapper=new BeanPropertyRowMapper<ExpensesRecord>(ExpensesRecord.class);
		List<ExpensesRecord> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<ExpensesRecord> selectAlloneColumn(NamedParameterJdbcTemplate jdbcTemplate,ArrayList<String> ColumnNames){
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
			 sql=sql+"  from data2 ";
		}
		else {
			sql=sql+" * from data2";
		}
		BeanPropertyRowMapper<ExpensesRecord> rowMapper=new BeanPropertyRowMapper<ExpensesRecord>(ExpensesRecord.class);
		List<ExpensesRecord> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<ExpensesRecord> selectSomeColumn(NamedParameterJdbcTemplate jdbcTemplate ,HashMap<String, String> ColumnMap){
		String sql="Select * from data2 ";
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
		BeanPropertyRowMapper<ExpensesRecord> rowMapper=new BeanPropertyRowMapper<ExpensesRecord>(ExpensesRecord.class);
		List<ExpensesRecord> studentInfos=jdbcTemplate.query(sql, rowMapper);
		return studentInfos.size()>0?studentInfos:null;
	}
	public List<ExpensesRecord> selectlikedate(NamedParameterJdbcTemplate jdbcTemplate,String date){
		String sql="select * from data2 where Date like '"+date+"%'";
		BeanPropertyRowMapper<ExpensesRecord> rowMapper=new BeanPropertyRowMapper<ExpensesRecord>(ExpensesRecord.class);
		List<ExpensesRecord> Exs=jdbcTemplate.query(sql, rowMapper);
		return Exs.size()>0?Exs:null;
	}
}
