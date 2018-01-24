package com.zzw.base.service.impl;

import com.zzw.base.dao.RolePermissionDao;
import com.zzw.base.entity.RolePermissionEntity;
import com.zzw.base.model.NestedCategory;
import com.zzw.base.model.TreeModel;
import com.zzw.base.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RolePermissionServiceImpl
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService
{
    /**
     * rolePermissionDao
     */
    @Autowired
    private RolePermissionDao<RolePermissionEntity> rolePermissionDao;

    /**
     * 根据roleId获取peridList
     */
    @Override
    public List<RolePermissionEntity> selectRolePersionByRoleId(final Long roleId)
    {
        List<RolePermissionEntity> rolePermissions = rolePermissionDao
                .selectRolePersionByRoleId(roleId);
        return rolePermissions;
    }

    /**
     *
     * @param rolePermission 参数
     * @return 结果
     */
    @Override
    public int addRolePermission(final RolePermissionEntity rolePermission)
    {
        int result = rolePermissionDao.addRolePermission(rolePermission);
        return result;
    }

    /**
     *
     * @param roleId 参数
     * @return 结果
     */
    @Override
    public int deleteRolePermission(final Long roleId)
    {
        int result = rolePermissionDao.deleteRolePermission(roleId);
        return result;
    }

    /**
     *
     * @param rolePermissionList 参数
     */
    @Override
    public void seveAll(final List<RolePermissionEntity> rolePermissionList)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", rolePermissionList);
        this.rolePermissionDao.saveAll(model);
    }

    /**
     *
     * @param nestedCategoryList 参数
     * @param rolePermissionList 参数
     * @return 结果
     */
    @Override
    public List<TreeModel> createCategoryTree(
            final List<NestedCategory> nestedCategoryList,
            final List<RolePermissionEntity> rolePermissionList)
    {
        TreeModel treeModel = new TreeModel();
        treeModel.setId(0L);
        treeModel.setState("open");
        treeModel.setText("根目录");

        createTree(treeModel, nestedCategoryList, rolePermissionList);

        List<TreeModel> result = new ArrayList<>();
        // easyui的树只接受数组格式的json
        result.add(treeModel);

        return result;
    }

    /**
     *
     * @param treeModel 参数
     * @param nestedCategoryList 参数
     * @param rolePermissionList 参数
     */
    private void createTree(final TreeModel treeModel,
                            final List<NestedCategory> nestedCategoryList,
                            final List<RolePermissionEntity> rolePermissionList)
    {
        for (NestedCategory category : nestedCategoryList)
        {
            TreeModel child = new TreeModel();
            child.setText(category.getCategoryName());
            child.setId(category.getId());
            child.setState("open");
            for (RolePermissionEntity rolePermissionrole : rolePermissionList)
            {
                if (category.getPermissionCode()
                        .equals(rolePermissionrole.getPerCode()))
                {
                    child.setChecked(true);
                    break;
                } else
                {
                    child.setChecked(false);
                }
            }

            treeModel.addChild(child);

            if (category.getChildren() != null
                    && !category.getChildren().isEmpty())
            {
                createTree(child, category.getChildren(), rolePermissionList);
            }
        }
    }
}
