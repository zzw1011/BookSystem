package com.zzw.base.service;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.OperatorLog;
import com.zzw.base.model.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * OperatorLogService
 *
 * @author Administrator
 */
public interface OperatorLogService {
    /**
     * 保存操作日志
     *
     * @param operatorLog 条件
     */
    void save(OperatorLog operatorLog);

    /**
     * 查询翻页列表
     *
     * @param queryMap 条件
     * @param pageQuery 分页条件
     * @return 结果
     */
    PageInfo<OperatorLog> findPage(Map<String, Object> queryMap, PageQuery pageQuery);

    /**
     * 删除
     *
     * @param id 条件
     */
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids 条件
     */
    void delete(List<Long> ids);

    /**
     * 详情
     *
     * @param id 条件
     * @return 结果
     */
    OperatorLog getById(Long id);
}
