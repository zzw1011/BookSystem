package com.zzw.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.zzw.base.dao.SystemParameterDao;
import com.zzw.base.entity.SystemParameterEntity;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.SystemParameterService;
import com.zzw.base.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SystemParameterServiceImpl
 */
@Service("systemParameterServiceImpl")
public class SystemParameterServiceImpl implements SystemParameterService {
    /**
     *
     */
    @SuppressWarnings("rawtypes")
    @Resource(name = "systemParameterDaoImpl")
    private SystemParameterDao systemParameterDao;

    /**
     *
     */
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * @param pageQuery 条件
     * @return 結果
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<SystemParameterEntity> findPage(final PageQuery pageQuery) {
        return systemParameterDao.findPage(
                "systemParameter_selectSystemParameter", null, pageQuery);
    }

    /**
     * @param parameterKey 条件
     * @param pageQuery    条件
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<SystemParameterEntity> findPageSearch(
            final String parameterKey, final PageQuery pageQuery) {
        SystemParameterEntity systemParameterEntity = new SystemParameterEntity();
        systemParameterEntity.setParameterKey(parameterKey);
        return systemParameterDao.findPage(
                "systemParameter_selectSystemParameterByKey",
                systemParameterEntity, pageQuery);
    }

    /**
     * @param systemParameterEntity 条件
     */
    @SuppressWarnings("unchecked")
    @Override
    public void save(final SystemParameterEntity systemParameterEntity) {
        systemParameterEntity.setCreateUser(
                userService.seleUserId(userService.getCurrentUser()));
        systemParameterDao.save("systemParameter_saveSystemParameter",
                systemParameterEntity);
    }

    /**
     * @param ids 条件
     */
    @Override
    public void delete(final List<Long> ids) {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        systemParameterDao.delete("systemParameter_deleteSystemParameters",
                model);
    }

    /**
     * @param parameterKey 条件
     * @return 结果
     */
    @Override
    public SystemParameterEntity findSystemParameter(final String parameterKey) {
        SystemParameterEntity queryObj = new SystemParameterEntity();
        queryObj.setParameterKey(parameterKey);
        return (SystemParameterEntity) systemParameterDao
                .get("systemParameter_selectSystemParameterByKey", queryObj);
    }

    /**
     * @param id 条件
     * @return 结果
     */
    @Override
    public SystemParameterEntity findSystemParameterById(final Long id) {
        SystemParameterEntity queryObj = new SystemParameterEntity();
        queryObj.setId(id);
        return (SystemParameterEntity) systemParameterDao
                .get("systemParameter_selectSystemParameterById", queryObj);
    }

    /**
     * @param systemParameterEntity 条件
     */
    @SuppressWarnings("unchecked")
    @Override
    public void update(final SystemParameterEntity systemParameterEntity) {
        systemParameterEntity.setCreateUser(
                userService.seleUserId(userService.getCurrentUser()));
        systemParameterDao.update("systemParameter_updateSystemParameter",
                systemParameterEntity);
    }

    /**
     * @param parameterKey 条件
     * @return 结果
     */
    @Override
    @Cacheable(value = "sysParamCache", key = "#parameterKey")
    public SystemParameterEntity selectFromCache(final String parameterKey) {
        return this.findSystemParameter(parameterKey);
    }

    @Override
    public void systemParameterSavePart(SystemParameterEntity systemParameterEntity) {
        systemParameterDao.save("systemParameterSavePart", systemParameterEntity);
    }

    @Override
    public void systemParameterUpdatePart(SystemParameterEntity systemParameterEntity) {
        systemParameterDao.update("systemParameterUpdatePart", systemParameterEntity);
    }

    /**
     * clearCache
     */
    @Override
    @CacheEvict(value = "sysParamCache", allEntries = true)
    public void clearCache() {

    }
}
