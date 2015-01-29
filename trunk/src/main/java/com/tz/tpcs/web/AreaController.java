package com.tz.tpcs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.tpcs.dao.AreaDao;
import com.tz.tpcs.entity.Area;

/**
 * 做省市级联
 * @author guan
 *
 */
@RestController
@RequestMapping("/areas")
public class AreaController {
	
	@Resource
	private AreaDao areaDao;
	
	  @RequestMapping(value = "/getCity", method= RequestMethod.POST)
	  public List<Area> initAdd(HttpServletRequest request){
		 System.out.println(request.getParameter("code"));
		 String code = request.getParameter("code");
		    List<Area> areas = areaDao.getAreaByParentId(areaDao.getAreaId(code));
		    List<Area> result = new ArrayList<>();
		  	for (Area area : areas) {
				System.out.println(area);
				result.add(new Area(area.getCode(), area.getName()));
			}
		  	return result;
	  }
}
