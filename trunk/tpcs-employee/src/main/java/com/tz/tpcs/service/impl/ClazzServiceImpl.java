package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.service.ClazzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 *  Clazz Service 实现类
 * @author 管成功
 * @amender 胡荆陵
 */
@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {

	@Resource
	private ClazzDao clazzDao;

	@Override
	public void save(Clazz clazz) {
		clazzDao.save(clazz);
	}

	@Override
	public void deleteById(String id) {
		clazzDao.delete(id);
	}

	@Override
	public Clazz getById(String id) {
		return clazzDao.findOne(id);
	}

}
