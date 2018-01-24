package com.zzw.base.dao.impl;

import com.zzw.base.dao.SystemParameterDao;
import org.springframework.stereotype.Repository;

/**
 * SystemParameterDaoImpl
 * @param <SystemParameterEntity>
 */
@Repository("systemParameterDaoImpl")
public class SystemParameterDaoImpl<SystemParameterEntity>
        extends BaseDaoImpl<SystemParameterEntity>
        implements SystemParameterDao<SystemParameterEntity>
{
}
