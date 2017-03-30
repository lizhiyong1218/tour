package com.lzy.tour.service.impl;

import java.util.List;
import java.util.Map;

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
		return getMapper().insertSelective(record);
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
		return getMapper().deleteByPrimaryKey(id);
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
		return getMapper().updateByPrimaryKeySelective(record);
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
		return getMapper().selectByPrimaryKey(id);
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
    	return getMapper().getOnebyModel(params);
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
    	return getMapper().getAll(params);
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
    	return getMapper().getAll(params, pageBounds);
    }
    
}
