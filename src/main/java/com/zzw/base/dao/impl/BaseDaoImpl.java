package com.zzw.base.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.base.dao.BaseDao;
import com.zzw.base.model.PageQuery;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/29 0029.
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

    /*
    * SQL会话
    * */
    @Autowired
    private SqlSession sqlSession;

    /**
     * 保存一个对象
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param o 查询对象
     * @return 结果
     */
    @Override
    public int save(final String mapperId, final T o) {
        return sqlSession.insert(mapperId, o);
    }

    /**
     * 保存map对象
     * @param mapperId 调用**mapper.xml的执行的id
     * @param map 查询集合
     * @return 结果
     */
    @Override
    public int save(final String mapperId, final Map map) {
        return sqlSession.insert(mapperId,map);
    }

    /**
     * 更新一个对象
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param o 参数
     * @return 结果
     */
    @Override
    public int update(final String mapperId, final T o) {
        return sqlSession.update(mapperId,o);
    }

    /**
     * 修改多个对象
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param param
     *            查询的参数：key为参数名,value为参数值
     * @return 结果
     */
    @Override
    public int update(final String mapperId, final Map param) {
        return sqlSession.update(mapperId,param);
    }

    /**
     * 删除一个对象
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param id 删除对象或者删除
     * @return 删除是否成功
     */
    @Override
    public int delete(String mapperId, Object id) {
        return sqlSession.delete(mapperId, id);
    }

    /**
     * 删除多个对象
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param param
     *            查询的参数：key为参数名,value为参数值
     * @return 结果
     */
    @Override
    public int delete(String mapperId, Map param) {
        return sqlSession.delete(mapperId, param);
    }

    /**
     * 查询一个对象
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param id 参数
     * @return 查询结果
     */
    @Override
    public T get(String mapperId, Object id) {
        return sqlSession.selectOne(mapperId, id);
    }

    /**
     * 查询集合
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param param
     *            查询的参数：key为参数名,value为参数值
     * @return 查询结果
     */
    @Override
    public List<T> find(String mapperId, Map param) {
        List<T> list;
        if(param !=null && param.size()>0){
            list = sqlSession.selectList(mapperId, param);
        }else{
            list = sqlSession.selectList(mapperId);
        }
        return list;
    }


    /**
     * 查询集合
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param model
     *            参数model对象
     * @return 查询结果
     */
    @Override
    public List<T> find(String mapperId, Object model) {
        List<T> list;
        if(model !=null ){
            list = sqlSession.selectList(mapperId, model);
        }else{
            list = sqlSession.selectList(mapperId);
        }
        return list;
    }

    /**
     * select count(*) from 类
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param param 查询参数
     * @return param 查询的参数：key为参数名,value为参数值
     */
    @Override
    public Long count(String mapperId, Map param) {
        Long count;
        if(param !=null && param.size()>0){
            count = sqlSession.selectOne(mapperId, param);
        }else{
            count = sqlSession.selectOne(mapperId);
        }
        return count;
    }

    /**
     * select count(*) from 类
     * @param mapperId
     *            调用**mapper.xml的执行的id
     * @param model 查询对象
     * @return param 查询的参数：key为参数名,value为参数值
     */
    @Override
    public Long count(String mapperId, Object model) {
        Long count;
        if(model !=null){
            count = sqlSession.selectOne(mapperId,model);
        }else{
            count = sqlSession.selectOne(mapperId);
        }
        return count;
    }

    /**
     * 分页查询
     * @param mapperId 调用**mapper.xml的执行的id
     * @param param 参数
     * @param pageQuery 分页对象
     * @return 分页结果
     */
    @Override
    public PageInfo<T> findPage(String mapperId, Map param, PageQuery pageQuery) {
        Assert.notNull(pageQuery);
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getRows(),pageQuery.getOrderBy());
        List<T> list = this.find(mapperId,param);
        PageInfo<T> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<T> findPage(String mapperId, T o, PageQuery pageQuery) {
        Assert.notNull(pageQuery);
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getRows(),pageQuery.getOrderBy());
        List<T> list = this.find(mapperId, o);
        PageInfo<T> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
