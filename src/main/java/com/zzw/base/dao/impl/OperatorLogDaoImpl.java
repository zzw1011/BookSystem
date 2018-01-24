package com.zzw.base.dao.impl;

import com.zzw.base.dao.OperatorLogDao;
import org.springframework.stereotype.Repository;

/**
 * 操作员日志dao接口实现类
 * @author Administrator
 * @param <OperatorLog>
 */
@Repository("operatorLogDaoImpl")
public class OperatorLogDaoImpl<OperatorLog> extends BaseDaoImpl<OperatorLog>
        implements OperatorLogDao<OperatorLog>
{
}
