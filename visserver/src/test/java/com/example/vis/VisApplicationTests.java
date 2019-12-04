package com.example.vis;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.vis.dao.stduendao;
import com.example.vis.entitys.studentInfo;

@SpringBootTest
class VisApplicationTests {
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	private stduendao stdao=new stduendao();
	@Test
	void contextLoads() {
		String sql="Select * from data1";
		System.out.println(sql);
		BeanPropertyRowMapper<studentInfo> rowMapper=new BeanPropertyRowMapper<studentInfo>(studentInfo.class);
		List<studentInfo> studentInfos=jdbcTemplate.query(sql, rowMapper);
		for(studentInfo s: studentInfos) {
			System.out.print(s.toString());
		}
		System.out.println(studentInfos.size());
	}
	
	@Test
	void testStudselectSomeColumn() {
		HashMap<String, String> maps=new HashMap<String, String>();
		maps.put("major", "18国际金融");
		maps.put("sex", "女");
		List<studentInfo> studentInfos=stdao.selectSomeColumn(jdbcTemplate, maps);
		for(studentInfo s: studentInfos) {
			System.out.println(s.toString());
		}
	}
//	@Test
//	void testAdao() {
//		Adao ad=new Adao();
//		List<studentInfo> studentInfos=ad.selectAllstudent(jdbcTemplate, "data3");
//		for(studentInfo s: studentInfos) {
//			System.out.println(s.toString());
//		}
//	}
}
