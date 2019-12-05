package com.example.vis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.vis.dao.AccessRecordDao;
import com.example.vis.dao.CalenderDao;
import com.example.vis.dao.ExpenseRecordDao;
import com.example.vis.dao.stduendao;
import com.example.vis.entitys.AccessRecord;
import com.example.vis.entitys.ExpensesRecord;
import com.example.vis.entitys.studentInfo;
@CrossOrigin(origins = {"http://localhost:8080", "null"})
@RestController
@Controller
public class DataController extends AController{
	private stduendao stdao=new stduendao();
	private ExpenseRecordDao Edao=new ExpenseRecordDao();
	private AccessRecordDao Adao=new AccessRecordDao();
	private CalenderDao CaDao=new CalenderDao();
	@RequestMapping(value="/All",method= RequestMethod.POST)//返回全部数据
	public <T> List<T> hello(@RequestBody Map<String, String> datas) {
		List<T>lists=null;
		switch ((datas.get("tableID"))) {
		case "1":
			lists=(List<T>) stdao.selectAll(this.getJdbcTemplate());
			break;
		case "2":
			lists=(List<T>) Edao.selectAll(this.getJdbcTemplate());
			break;
		case "3":
			lists=(List<T>) Adao.selectAll(this.getJdbcTemplate());
			break;
		default:
			break;
		}
		
		return lists;
	}
	@RequestMapping(value="/Columns",method= RequestMethod.POST)//返回某几列全部数据
	public <T> List<T> OneC(@RequestBody Map<String, String> datas) {
		String[]cs=datas.get("Columns").split(",");
		ArrayList<String>columns=new ArrayList<String>();
		for(String s: cs) {
			columns.add(s);
		}
		List<T>lists=null;
		switch ((datas.get("tableID"))) {
		case "1":
			lists=(List<T>) stdao.selectAlloneColumn(this.getJdbcTemplate(),columns);
			break;
		case "2":
			lists=(List<T>) Edao.selectAlloneColumn(this.getJdbcTemplate(),columns);
			break;
		case "3":
			lists=(List<T>) Adao.selectAlloneColumn(this.getJdbcTemplate(),columns);
			break;
		default:
			break;
		}
		
		return lists;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/MutColumn",method= RequestMethod.POST)//返回条件查询数据
	public <T> List<T> MutC(@RequestBody Map<String, String> datas) {
		HashMap<String, String> maps=new HashMap<String, String>();
		for(String s:datas.keySet()) {
			if(!s.equals("tableID"))
				maps.put(s, datas.get(s));
		}
		List<T> studentInfos=null;
		switch ((datas.get("tableID"))) {
		case "1":
			studentInfos=(List<T>) stdao.selectSomeColumn(this.getJdbcTemplate(), maps);
			break;
		case "2":
			studentInfos=(List<T>) Edao.selectSomeColumn(this.getJdbcTemplate(), maps);
			break;
		case "3":
			studentInfos=(List<T>) Adao.selectSomeColumn(this.getJdbcTemplate(), maps);
			break;
		default:
			break;
		}
		return studentInfos;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Rdate",method= RequestMethod.POST)//返回相关时间
	public <T> List<T> POSTdateR(@RequestBody Map<String, String> datas){
		List<T> lists=null;
		switch ((datas.get("tableID"))) {
		case "2":
			lists=(List<T>) Edao.selectlikedate(this.getJdbcTemplate(), datas.get("date"));
			break;
		case "3":
			lists=(List<T>) Adao.selectlikedate(this.getJdbcTemplate(),  datas.get("date"));
			break;
		default:
			break;
		}
		return lists;
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Calen",method= RequestMethod.POST)//返回相关时间
	public <T> List<T> Calenderdata(@RequestBody Map<String, String> datas){
		List<T> lists=null;
		lists=(List<T>) CaDao.selectlikedate(this.getJdbcTemplate(), datas.get("date"));
		return lists;
		
	}
}
