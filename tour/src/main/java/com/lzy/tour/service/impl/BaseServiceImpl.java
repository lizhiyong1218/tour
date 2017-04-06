package com.lzy.tour.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.service.BaseService;

/**
 * 
 * @ClassName: BaseServiceImpl
 * @Description: 公共service实现
 * @author 李志勇
 * @date 2015年4月8日 上午9:42:15
 * 
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>{
	
	private Logger logger=Logger.getLogger(this.getClass().getName());

	protected abstract  BaseMapper<T> getMapper();
	/**
	 * 
	 * @Title: insert
	 * @Description: 新增对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	@Override
	public int insert(T record){
		try {
			return getMapper().insertSelective(record);
		} catch (Exception e) {
			logger.error(e);
		}
    	return 0;
	}
	
	/**
	 * 
	 * @Title: deleteById
	 * @Description: 根据id删除对象
	 * @param id
	 * @return: int
	 * @throws
	 */
	@Override
	public int deleteById(Integer id){
		try {
			return getMapper().deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e);
		}
    	return 0;
	}
	
	/**
	 * 
	 * @Title: update
	 * @Description: 修改对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	@Override
	public int update(T record){
		try {
			return getMapper().updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			logger.error(e);
		}
    	return 0;
	}

	/**
	 * 
	 * @Title: getOneById
	 * @Description: 根据id查询对象
	 * @param id
	 * @return: T
	 * @throws
	 */
	@Override
	public T getOneById(Integer id){
		try {
			return getMapper().selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e);
		}
    	return null;
	}
	
	/**
	 * 
	 * @Title: getOneByModel
	 * @Description: 查询单个对象
	 * @param para
	 * @return:
	 * @throws
	 */
	@Override
    public T getOneByModel(Map<String, Object> params){
    	try {
    		return getMapper().getOnebyModel(params);
		} catch (Exception e) {
			logger.error(e);
		}
    	return null;
    }
    
	/**
	 * 
	 * @Title: getAll
	 * @Description: 查询所有对象
	 * @param o
	 * @return:
	 * @throws
	 */
    @Override
    public List<T> getAll(Map<String, Object> params) {
    	try {
    		return getMapper().getAll(params);
		} catch (Exception e) {
			logger.error(e);
		}
    	return null;
    }
    
	/**
	 * 
	 * @Title: getPagination
	 * @Description: 获取分页对象
	 * @param o
	 * @param pageBounds
	 * @return: Pagination<T>
	 * @throws
	 */
    public PageList<T> getPagination(Map<String, Object> params,PageBounds pageBounds){
    	try {
			return getMapper().getAll(params, pageBounds);
		} catch (Exception e) {
			logger.error(e);
		}
    	return null;
    }
    
}
