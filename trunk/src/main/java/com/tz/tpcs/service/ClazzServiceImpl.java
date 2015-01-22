package com.tz.tpcs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.ClazzForm;

/**
 * 
 * @author 管成功
 * 
 */
@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {

	@Resource
	private ClazzDao cdao;

	@Override
	public void save(Clazz clazz) {
		// TODO Auto-generated method stub
		cdao.save(clazz);
	}

	@Override
	public List<Clazz> selectAllByCondition(String name, Integer min,
			Integer max) {
		// TODO Auto-generated method stub

		return cdao.getAllByCondition(name, min, max);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		cdao.delete(id);
	}

	@Override
	public Clazz getById(String id) {
		// TODO Auto-generated method stub
		return cdao.findOne(id);
	}

}
