package com.zdk.service.enterprise;

import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.EditMeta;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.pojo.EnterpriseUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/21 18:58
 */
public interface EnterpriseService {
    /**
     *企业用户的登录
     * @param  id：账号
     * @param  password：密码
     * @param email:修改密码时接口的重用
     * @return 登录成功的结果>0则成功
     */
    EnterpriseUser enterpriseLogin(String id, String password,String email);

    /**
     *获取企业用户的列表
     * @param  pageNum 从第几条数据开始获取
     * @param  pageSize 分页的每页的条数
     * @return 返回类型为AdminMeta的list
     */
    List<EnterpriseMeta> getEnterpriseList(Integer pageNum, Integer pageSize);

    /**
     *模糊查询企业用户的列表
     * @param  query:模糊查询条件
     * @param  pageNum:开始条
     * @param  pageSize:页条数
     * @return 返回类型为AdminMeta的list
     */
    List<EnterpriseMeta> fuzzyQueryEnterpriseList(String query, Integer pageNum, Integer pageSize);

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
    int removeEnterprise(String id);

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
    EditMeta showEnterprise(String id);

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
    int updateLoginInfo(@Param("id") String id, @Param("date") String date);

    /**
     *修改企业用户密码
     * @param  enterpriseUser:企业用户对象
     * @return 返回修改条数
     */
    int modifyEnterprisePwd(AddEnterpriseMeta enterpriseUser);
}
