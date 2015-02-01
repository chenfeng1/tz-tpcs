package com.tz.tpcs.web;

import com.tz.tpcs.dao.AreaDao;
import com.tz.tpcs.entity.Area;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
	   * 根据code，获得下属城市列表
	   * @return List<Area>
	   * @author 管成功
	   * @amender 胡荆陵
	   */
	  @RequestMapping(value = "/getCity", method= RequestMethod.GET)
	  public List<Area> initAdd(@RequestParam String code){
		    List<Area> areas = areaDao.findByParentCode(code);
		    List<Area> result = new ArrayList<>();
		  	for (Area area : areas) {
				result.add(new Area(area.getCode(), area.getName()));
			}
		  	return result;
	  }
}
