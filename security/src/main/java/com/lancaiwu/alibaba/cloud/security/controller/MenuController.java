package com.lancaiwu.alibaba.cloud.security.controller;

import com.lancaiwu.alibaba.cloud.security.bean.SecurityResponse;
import com.lancaiwu.alibaba.cloud.security.service.impl.MenuService;
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
 * @since 2020/10/8 09:36
 * <p>
 * 菜单管理
 */
@Slf4j
@RestController
@RequestMapping(value = "/security-manage")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 新增菜单 (url 必须是/caiDan1/**的形式)
     */
    @PostMapping(value = "/resource-manage/add")
    public SecurityResponse add(String url, String menuName, String parentId, String remark, String urlPre) {
        try {
            if (!ObjectUtils.isEmpty(url) && !ObjectUtils.isEmpty(menuName) && !ObjectUtils.isEmpty(parentId)) {
                boolean existenceStatus = menuService.menuNameIsExistence(menuName, parentId);
                if (!existenceStatus) {
                    Map<String, String> menuInfo = menuService.addMenuInfo(url, menuName, parentId, remark, urlPre);
                    return new SecurityResponse(true, "1", "Add menu success!!", menuInfo);
                } else {
                    return new SecurityResponse(false, "-1", "Menu already exists!!", "menuName: " + menuName + " already exists!!");
                }
            } else {
                return new SecurityResponse(false, "-1", "Incomplete information!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("addMenuInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Add menu failure!!", "menuName: " + menuName + " add failure!!");
        }
    }

    /**
     * 删除菜单 (批量删除id 逗号分隔)
     */
    @PostMapping(value = "/resource-manage/delete")
    public SecurityResponse delete(String id) {
        try {
            if (!ObjectUtils.isEmpty(id)) {
                menuService.deleteMenuInfo(id);
                return new SecurityResponse(true, "1", "Delete menu success!!", "id: " + id);
            } else {
                return new SecurityResponse(false, "-1", "Menu delete failure!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("deleteMenuInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Delete menu failure!!", "id: " + id);
        }
    }

    /**
     * 修改菜单信息
     */
    @PostMapping(value = "/resource-manage/update")
    public SecurityResponse update(String id, String url, String menuName, String parentId, String remark, String urlPre) {
        try {
            if (!ObjectUtils.isEmpty(id) && !ObjectUtils.isEmpty(url) && !ObjectUtils.isEmpty(menuName) && !ObjectUtils.isEmpty(parentId)) {
                boolean notExistenceOfUpdateMenuName = menuService.isNotExistenceOfUpdateMenuName(id, menuName, parentId);
                if (notExistenceOfUpdateMenuName) {
                    menuService.updateMenuInfo(id, url, menuName, parentId, remark, urlPre);
                    return new SecurityResponse(true, "1", "Update menuInfo success!!", "menuName: " + menuName + " update success!!");
                } else {
                    return new SecurityResponse(false, "-1", "Update menuInfo failure!!", "menuName: " + menuName + " already exists!!");
                }
            } else {
                return new SecurityResponse(false, "-1", "Incomplete information!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("updateMenuInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Update menuInfo failure!!", "menuName: " + menuName + " update failure!!");
        }
    }

    /**
     * 获取所有菜单信息
     */
    @PostMapping(value = "/resource-manage/findAll")
    public SecurityResponse findAll() {
        try {
            List<Map<String, Object>> menuInfoList = menuService.findAllMenuInfo();
            return new SecurityResponse(true, "1", "Find all role success!!", menuInfoList);
        } catch (Exception e) {
            log.error("findAllMenuInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Find all role failure!!", e.getMessage());
        }
    }
}