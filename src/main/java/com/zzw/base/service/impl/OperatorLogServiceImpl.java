package com.zzw.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.zzw.base.dao.OperatorLogDao;
import com.zzw.base.entity.OperatorLog;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.OperatorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OperatorLogServiceImpl
 */
@Service("operatorLogServiceImpl")
public class OperatorLogServiceImpl implements OperatorLogService
{
    /**
     * operatorLogDao
     */
    @Autowired
    private OperatorLogDao<OperatorLog> operatorLogDao;

    /**
     *
     * @param operatorLog 条件
     */
    @Override
    public void save(final OperatorLog operatorLog)
    {
        operatorLogDao.save("addOperatorLog", operatorLog);
    }

    /**
     *
     * @param queryMap 条件
     * @param pageQuery 分页条件
     * @return 结果
     */
    @Override
    public PageInfo<OperatorLog> findPage(final Map<String, Object> queryMap,
                                          final PageQuery pageQuery)
    {
        return operatorLogDao.findPage("selectOperatorLogPage", queryMap,
                pageQuery);
    }

    /**
     *
     * @param id 条件
     */
    @Override
    public void delete(final Long id)
    {
        OperatorLog log = new OperatorLog();
        log.setId(id);

        operatorLogDao.delete("deleteOperatorLog", log);
    }

    /**
     *
     * @param ids 条件
     */
    @Override
    public void delete(final List<Long> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);

        operatorLogDao.delete("optLog_deleteIds", model);
    }

    /**
     *
     * @param id 条件
     * @return 结果
     */
    @Override
    public OperatorLog getById(final Long id)
    {
        return operatorLogDao.get("selectOperatorLogById", id);
    }
}
