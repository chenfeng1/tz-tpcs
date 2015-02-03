package com.tz.tpcs.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
	  /**
	   * 获取城市
	   * @param request
	   * @return
	   * @author 管成功
	   */
	  @RequestMapping(value = "/getCity", method= RequestMethod.POST)
	  public List<Area> initAdd(HttpServletRequest request){
		 String code = request.getParameter("code");
		    List<Area> areas = areaDao.getAreaByParentId(areaDao.getAreaId(code));
		    List<Area> result = new ArrayList<>();
		  	for (Area area : areas) {
				result.add(new Area(area.getZipCode(), area.getDivisionCode(), area.getName()));
			}
		  	return result;
	  }
}
