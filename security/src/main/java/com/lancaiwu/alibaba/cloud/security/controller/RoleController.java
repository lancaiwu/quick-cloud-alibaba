package com.lancaiwu.alibaba.cloud.security.controller;

import com.lancaiwu.alibaba.cloud.security.bean.SecurityResponse;
import com.lancaiwu.alibaba.cloud.security.service.impl.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:38
 * 角色管理
 */
@Slf4j
@RestController
@RequestMapping(value = "/security")
public class RoleController {


    @Resource
    private RoleService roleService;

    /**
     * 新增角色信息 角色名称(必须以ROLE_起始命名)
     */
    @PostMapping(value = "/role/add")
    public SecurityResponse add(String roleName, String roleNameCN, String remark) {
        try {
            if (!ObjectUtils.isEmpty(roleName) && !ObjectUtils.isEmpty(roleNameCN)) {
                boolean existenceStatus = roleService.roleNameIsExistence(roleName);
                if (!existenceStatus) {
                    roleService.addRoleInfo(roleName, roleNameCN, remark);
                    return new SecurityResponse(true, "1", "Add role success!!", "roleName: " + roleName + " add success!!");
                } else {
                    return new SecurityResponse(false, "-1", "Role already exists!!", "roleName: " + roleName + " already exists!!");
                }
            } else {
                return new SecurityResponse(false, "-1", "Incomplete information!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("roleService addRoleInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Add role failure!!", "roleName: " + roleName + " add failure!!");

        }
    }

    /**
     * 为角色分配可访问的资源菜单 (批量菜单id:逗号分隔)
     */
    @PostMapping(value = "/role/addMenus")
    public SecurityResponse addRolesForUser(String roleId, String menuId) {
        try {
            if (!ObjectUtils.isEmpty(roleId) && !ObjectUtils.isEmpty(menuId)) {
                roleService.addMenusForRole(roleId, menuId);
                return new SecurityResponse(true, "1", "Role add menu success!!", "roleId: " + roleId + " menuId: " + menuId);
            } else {
                return new SecurityResponse(false, "-1", "Role add menu failure!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("addRolesForUser failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Role add menu failure!!", "roleId: " + roleId + " menuId: " + menuId);

        }
    }

    /**
     * 删除角色信息 (批量删除id 逗号分隔)
     */
    @PostMapping(value = "/role/delete")
    public SecurityResponse delete(String id) {
        try {
            if (!ObjectUtils.isEmpty(id)) {
                roleService.deleteRoleInfo(id);
                return new SecurityResponse(true, "1", "Delete role success!!", "id: " + id);
            } else {
                return new SecurityResponse(false, "-1", "Role delete failure!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("deleteRoleInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Delete role failure!!", "id: " + id);
        }
    }

    /**
     * 修改角色信息
     */
    @PostMapping(value = "/role/update")
    public SecurityResponse update(String id, String roleName, String roleNameCN, String remark) {
        try {
            if (!ObjectUtils.isEmpty(id) && !ObjectUtils.isEmpty(roleName) && !ObjectUtils.isEmpty(roleNameCN)) {
                boolean notExistenceOfUpdateRoleName = roleService.isNotExistenceOfUpdateRoleName(id, roleName);
                if (notExistenceOfUpdateRoleName) {
                    roleService.updateRoleInfo(id, roleName, roleNameCN, remark);
                    return new SecurityResponse(true, "1", "Update roleInfo success!!", "roleName: " + roleName + " update success!!");
                } else {
                    return new SecurityResponse(false, "-1", "Update roleInfo failure!!", "roleName: " + roleName + " already exists!!");
                }
            } else {
                return new SecurityResponse(false, "-1", "Incomplete information!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("updateRoleInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Update roleInfo failure!!", "roleName: " + roleName + " update failure!!");
        }
    }

    /**
     * 获取所有角色信息 (分页加模糊查询)
     */
    @PostMapping(value = "/role/findAll")
    public SecurityResponse findAll(String pageNum, String pageSize, String roleNameCN) {
        try {
            if (!ObjectUtils.isEmpty(pageNum) && !ObjectUtils.isEmpty(pageSize)) {
                SecurityResponse securityResponse = new SecurityResponse();
                List<Map<String, Object>> roleInfoList = roleService.findAllRoleInfo(pageNum, pageSize, roleNameCN, securityResponse);
                return securityResponse;
            } else {
                return new SecurityResponse(false, "-1", "Incomplete information!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("findAllRoleInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Find all role failure!!", e.getMessage());
        }
    }
}