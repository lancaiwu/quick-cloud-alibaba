package com.lancaiwu.alibaba.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lancaiwu.alibaba.cloud.security.bean.AuthorityUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:41
 */
@Repository
public interface AuthorityUserRepository extends BaseMapper<AuthorityUser> {

    @Select("SELECT password FROM authority_user WHERE username=#{username} AND valid_time>#{validTime}")
    String findPasswordByUsernameAfterValidTime(@Param("username") String username, @Param("validTime") String validTime);

    @Select("SELECT role_name FROM authority_role WHERE id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username}))")
    List<String> findRoleNameByUsername(@Param("username") String username);

    @Select("SELECT id,nickname,remark FROM authority_user WHERE username= #{username}")
    Map<String, Object> findUserIdAndNickNameAndRemarkByUsername(@Param("username") String username);

    @Select("SELECT url FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username})))")
    List<String> findUrlsByUsername(@Param("username") String username);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre, remark AS remark FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username}))) AND parent_id=0")
    List<Map<String, Object>> findRootMenuInfoByUsername(@Param("username") String username);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username}))) AND parent_id!=0")
    List<Map<String, Object>> findNotRootMenuInfoByUsername(@Param("username") String username);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre FROM authority_menu WHERE parent_id= #{id}")
    List<Map<String, Object>> findMenuInfoByParentId(@Param("id") int id);

    @Select("SELECT DISTINCT(parent_id) FROM authority_menu \n" +
            "\tWHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id= #{roleId}) \n" +
            "\tAND parent_id NOT IN (SELECT id FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id= #{roleId}) AND parent_id=0)\n" +
            "\tAND parent_id NOT IN (0,1)")
    List<Integer> findRootMenuIdOfPartialPermissionByRoleId(@Param("roleId") int roleId);

    @Select("SELECT DISTINCT(parent_id) FROM authority_menu \n" +
            "\tWHERE parent_id NOT IN (SELECT id FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username}))) AND parent_id=0) \n" +
            "\tAND id IN (SELECT menu_id FROM authority_role_menu WHERE role_id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username})))\n" +
            "\tAND parent_id NOT IN (0,1)")
    List<Integer> findRootMenuIdOfPartialPermission(@Param("username") String username);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre, remark AS remark FROM authority_menu WHERE id= #{menuId}")
    Map<String, Object> findMenuInfoByMenuId(@Param("menuId") int menuId);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre FROM authority_menu \n" +
            "\tWHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id= #{roleId})\n" +
            "\tAND parent_id= #{parentId}")
    List<Map<String, Object>> findChildrenMenuInfoByRoleIdAndParentId(@Param("roleId") int roleId, @Param("parentId") int parentId);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre FROM authority_menu \n" +
            "\tWHERE id IN (SELECT DISTINCT(id) FROM authority_menu \n" +
            "\t\tWHERE parent_id NOT IN (SELECT id FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username}))) AND parent_id=0) \n" +
            "\t\tAND id IN (SELECT menu_id FROM authority_role_menu WHERE role_id IN (SELECT role_id FROM authority_user_role WHERE user_id=(SELECT id FROM authority_user WHERE username= #{username})))\n" +
            "\t\tAND parent_id NOT IN (0,1))\n" +
            "\tAND parent_id= #{parentId}")
    List<Map<String, Object>> findChildrenMenuInfoByUsernameAndParentId(@Param("username") String username, @Param("parentId") int parentId);

    @Select("SELECT url FROM authority_menu")
    List<String> findAllMenuUrl();

    @Select("SELECT role_name FROM authority_role WHERE id IN (SELECT role_id FROM authority_role_menu WHERE menu_id IN (SELECT id FROM authority_menu WHERE url= #{url}))")
    List<String> findRoleNameByMenuUrl(@Param("url") String url);

    @Select("SELECT COUNT(username) FROM authority_user WHERE username= #{username}")
    int findCountByUsername(@Param("username") String username);

    @Insert("INSERT INTO authority_user (nickname, username, password, email, phone, valid_time, update_time, remark) VALUES ( #{nickname},  #{username},    #{password},  #{email},  #{phone},  #{validTime},#{nowTime},  #{remark})")
    void addUserInfo(@Param("nickname") String nickname, @Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("phone") String phone, @Param("validTime") String validTime, @Param("nowTime") String nowTime, @Param("remark") String remark);

    @Insert("INSERT INTO authority_user_role(user_id, role_id, update_time) VALUES( #{userId}, #{roleId}, #{updateTime})")
    int addRoleForUser(@Param("userId") int userId, @Param("roleId") int roleId, @Param("updateTime") String updateTime);

    @Delete("DELETE FROM authority_user_role WHERE user_id= #{userId}")
    int deleteRolesByUserId(@Param("userId") int userId);

    @Delete("DELETE FROM authority_user WHERE id= #{userId}")
    int deleteUserInfoByUserId(@Param("userId") int userId);

    @Update("UPDATE authority_user SET nickname= #{nickname}, username= #{username}, password= #{password}, email= #{email}, phone= #{phone}, valid_time= #{validTime}, update_time= #{validTime}, remark= #{remark=} WHERE id= #{id}")
    int updateUserInfoByUserId(@Param("id") int id, @Param("nickname") String nickname, @Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("phone") String phone, @Param("validTime") String validTime, @Param("updateTime") String updateTime, @Param("remark") String remark);

    @Update("UPDATE authority_user SET nickname= #{nickname}, username= #{username}, password= password, email= #{email}, phone= #{phone}, update_time= #{updateTime}, remark= #{remark} WHERE id= #{id}")
    int updateUserInfoByUserIdExcludeValidTime(@Param("id") int id, @Param("nickname") String nickname, @Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("phone") String phone, @Param("updateTime") String updateTime, @Param("remark") String remark);

    @Update("UPDATE authority_user SET nickname= #{nickname}, username= #{username}, email= #{email}, phone= #{phone}, update_time= #{updateTime}, remark= #{remark} WHERE id= #{id}")
    int updateUserInfoByUserIdExcludeValidTimeAndPassword(@Param("id") int id, @Param("nickname") String nickname, @Param("username") String username, @Param("") String email, @Param("phone") String phone, @Param("updateTime") String updateTime, @Param("remark") String remark);

    @Update("UPDATE authority_user SET nickname= #{nickname}, username= #{username}, email= #{email}, phone= #{phone}, valid_time= #{validTime}, update_time= #{updateTime}, remark= #{remark} WHERE id= #{id}")
    int updateUserInfoByUserIdExcludePassword(@Param("id") int id, @Param("nickname") String nickname, @Param("username") String username, @Param("email") String email, @Param("phone") String phone, @Param("validTime") String validTime, @Param("updateTime") String updateTime, @Param("remark") String remark);

    @Select("SELECT id AS id, username AS username, password AS password, nickname AS nickname, email AS email, phone AS phone, valid_time AS validTime, remark AS remark FROM authority_user WHERE username LIKE CONCAT('%',#{username},'%')    AND nickname LIKE CONCAT('%',#{nickname},'%') LIMIT  #{pageNum}, #{pageSize}")
    List<Map<String, Object>> findAllUserInfo(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("username") String username, @Param("nickname") String nickname);

    @Select("SELECT COUNT(id) FROM authority_user WHERE username LIKE  CONCAT('%',#{username},'%')    AND nickname LIKE  CONCAT('%',#{nickname},'%') ")
    int findAllUserInfoSize(@Param("username") String username, @Param("nickname") String nickname);

    @Select("SELECT id AS id, role_name AS roleName, role_name_CN AS roleNameCN, remark AS remark FROM authority_role WHERE id IN (SELECT role_id FROM authority_user_role WHERE user_id= #{userId})")
    List<Map<String, Object>> findRoleInfoByUserId(@Param("userId") int userId);

    @Select("SELECT COUNT(role_name) FROM authority_role WHERE role_name= #{roleName}")
    int roleNameIsExistence(@Param("roleName") String roleName);

    @Insert("INSERT INTO authority_role(role_name, role_name_CN, update_time, remark) VALUES ( #{roleName},  #{roleNameCN},  #{nowTime},  #{remark})")
    void addRoleInfo(@Param("roleName") String roleName, @Param("roleNameCN") String roleNameCN, @Param("nowTime") String nowTime, @Param("remark") String remark);

    @Insert("INSERT INTO authority_role_menu(role_id, menu_id, update_time) VALUES( #{roleId}, #{menuId}, #{updateTime})")
    int addMenuForRole(@Param("roleId") int roleId, @Param("menuId") int menuId, @Param("updateTime") String updateTime);

    @Delete("DELETE FROM authority_role_menu WHERE role_id= #{roleId}")
    int deleteMenusByRoleId(@Param("roleId") int roleId);

    @Delete("DELETE FROM authority_role WHERE id= #{roleId}")
    int deleteRoleInfoByRoleId(@Param("roleId") int roleId);

    @Update("UPDATE authority_role SET role_name= #{roleName}, role_name_CN= #{roleNameCN}, update_time= #{nowTime}, remark= #{remark} WHERE id= #{roleId}")
    int updateRoleInfoByRoleId(@Param("roleId") int roleId, @Param("roleName") String roleName, @Param("roleNameCN") String roleNameCN, @Param("nowTime") String nowTime, @Param("remark") String remark);

    @Select("SELECT id AS id, role_name AS roleName, role_name_CN AS roleNameCN, remark AS remark FROM authority_role WHERE role_name_CN LIKE  #{roleNameCN} LIMIT  #{pageNum}, #{pageSize}")
    List<Map<String, Object>> findAllRoleInfo(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("roleNameCN") String roleNameCN);

    @Select("SELECT COUNT(id) FROM authority_role WHERE role_name_CN LIKE  #{roleNameCN}")
    int findAllRoleInfoSize(@Param("roleNameCN") String roleNameCN);

    @Select(" SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id= #{roleId}) AND parent_id IN (0,1)")
    List<Map<String, Object>> findRootMenuInfoByRoleId(@Param("roleId") int roleId);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre FROM authority_menu WHERE id IN (SELECT menu_id FROM authority_role_menu WHERE role_id= #{roleId}) AND parent_id!=0")
    List<Map<String, Object>> findNotRootMenuInfoByRoleId(@Param("roleId") int roleId);

    @Select("SELECT COUNT(menu_name) FROM authority_menu WHERE menu_name= #{menuName} AND parent_id= #{parentId}")
    int menuNameIsExistence(@Param("menuName") String menuName, @Param("parentId") String parentId);

    @Insert("INSERT INTO authority_menu (url, menu_name, parent_id, update_time, remark, url_pre) VALUES ( #{url},  #{menuName},  #{parentId},  #{updateTime},  #{remark},  #{urlPre})")
    void addMenuInfo(@Param("url") String url, @Param("menuName") String menuName, @Param("parentId") String parentId, @Param("updateTime") String updateTime, @Param("remark") String remark, @Param("urlPre") String urlPre);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, remark AS remark, url_pre AS urlPre FROM authority_menu WHERE menu_name= #{menuName} AND parent_id= #{parentId}")
    Map<String, String> findMenuInfoByMenuName(@Param("menuName") String menuName, @Param("parentId") String parentId);

    @Delete("DELETE FROM authority_role_menu WHERE menu_id= #{menuId}")
    int deleteRolesByMenuId(@Param("menuId") int menuId);

    @Delete("DELETE FROM authority_menu WHERE id= #{menuId}")
    int deleteMenuInfoByMenuId(@Param("menuId") int menuId);

    @Update("UPDATE authority_menu SET url= #{url}, menu_name= #{menuName}, parent_id= #{parentId}, update_time= #{updateTime}, remark= #{remark}, url_pre= #{urlPre} WHERE id= #{id}")
    int updateMenuInfoByMenuId(@Param("id") int id, @Param("url") String url, @Param("menuName") String menuName, @Param("parentId") String parentId, @Param("updateTime") String updateTime, @Param("remark") String remark, @Param("urlPre") String urlPre);

    @Select("SELECT id AS id, url AS url, menu_name AS menuName, parent_id AS parentId, url_pre AS urlPre, remark AS remark FROM authority_menu WHERE parent_id=0")
    List<Map<String, Object>> findRootMenuInfo();

    @Select("SELECT COUNT(username) FROM authority_user WHERE id!= #{userId} AND username= #{username}")
    int isNotExistenceOfUpdateUsername(@Param("userId") int userId, @Param("username") String username);

    @Select("SELECT COUNT(role_name) FROM authority_role WHERE id!= #{roleId} AND role_name= #{roleName}")
    int isNotExistenceOfUpdateRoleName(@Param("roleId") int roleId, @Param("roleName") String roleName);

    @Select("SELECT COUNT(menu_name) FROM authority_menu WHERE id!= #{menuId} AND menu_name= #{menuName} AND parent_id= #{parentId}")
    int isNotExistenceOfUpdateMenuName(@Param("menuId") int menuId, @Param("menuName") String menuName, @Param("parentId") String parentId);

    @Select("SELECT id FROM authority_menu WHERE parent_id= #{parentId}")
    List<Integer> selectChildrenMenuIds(@Param("parentId") int parentId);

    @Select("SELECT nickname,username,phone,email FROM authority_user WHERE id= #{userId} AND username= #{username}")
    Map<String, String> getUserInfoByUserIdAndUsername(@Param("userId") String userId, @Param("username") String username);

    @Update("UPDATE authority_user SET nickname=#{nickname}, email=#{email}, phone=#{phone}, update_time=#{updateTime} WHERE username=#{username}")
    int updateUserBasicInfo(@Param("nickname") String nickname, @Param("username") String username, @Param("email") String email, @Param("phone") String phone, @Param("updateTime") String updateTime);

    @Select("SELECT `password` FROM authority_user WHERE username=#{username}")
    String getUserPasswordByUsername(@Param("username") String username);

    @Update("UPDATE authority_user SET `password`=#{newPassword}, update_time=#{updateTime} WHERE username=#{username}")
    int updateUserPasswordByUsername(@Param("username") String username, @Param("newPassword") String newPassword, @Param("updateTime") String updateTime);

    @Update("UPDATE authority_user SET nickname=#{nickname}, email=#{email}, phone=#{phone}, valid_time=#{validTime}, update_time=#{updateTime}, remark=#{remark} WHERE username=#{username}")
    int updateUserInfo(@Param("nickname") String nickname, @Param("username") String username, @Param("email") String email, @Param("phone") String phone, @Param("validTime") String validTime, @Param("updateTime") String updateTime, @Param("remark") String remark);

    @Update("UPDATE authority_user SET `password`=#{updateTime}, update_time=#{updateTime} WHERE id=#{userId} AND username=#{username}")
    int updateUserPassword(@Param("userId") String userId, @Param("username") String username, @Param("password") String password, @Param("updateTime") String updateTime);
}
