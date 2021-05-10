package com.zdk.mapper.enterprise;

import com.zdk.dto.EditMeta;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.pojo.EnterpriseUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/4/21 18:59
 */
public interface EnterpriseMapper {
    /**
     *企业用户的登录
     * @param  id:账号
     * @param  password:密码
     * @param email:修改密码时接口的重用
     * @return 登录成功的结果不为空则成功
     */
    EnterpriseUser enterpriseLogin(@Param("id") String id, @Param("password") String password,@Param("email") String email);

    /**
     *获取企业用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<EnterpriseMeta> getEnterpriseList(Map map);

    /**
     *模糊查询企业用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<EnterpriseMeta> fuzzyQueryEnterpriseList(Map map);
    /**
     *获取企业用户的人数
     * @return 人数
     */
    int enterpriseTotalPage();

    /**
     *删除企业用户
     * @param  id:id
     * @return 返回类型条数
     */
    int removeEnterprise(@Param("id") String id);

    /**
     *添加注册企业用户
     * @param  enterpriseUser:添加时dto对象
     * @return 返回类型条数
     */
    int addEnterprise(EnterpriseUser enterpriseUser);

    /**
     *显示企业用户编辑信息
     * @param  id:企业用户id
     * @return 返回编辑对象
     */
    EditMeta showEnterpriseUser(String id);

    /**
     *修改企业用户信息
     * @param  user:编辑企业用户对象
     * @return 返回修改条数
     */
    int modifyEnterpriseUser(EditMeta user);

    /**
     *改变登录次数和最后登录时间
     * @param  id:用户id
     * @param date:当前日期
     * @return 返回类型条数
     */
    int updateLoginInfo(@Param("id") String id,@Param("date") String date);

    /**
     *修改企业用户密码
     * @param  map:id和新密码
     * @return 返回修改条数
     */
    int modifyEnterprisePwd(Map map);
}
