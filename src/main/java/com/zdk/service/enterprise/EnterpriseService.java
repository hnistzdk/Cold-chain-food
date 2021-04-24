package com.zdk.service.enterprise;

import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EnterpriseMeta;

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
     * @return 登录成功的结果>0则成功
     */
    int enterpriseLogin(String id, String password);

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
     * @param  id
     * @return 返回类型条数
     */
    int removeEnterprise(String id);

    /**
     *添加企业用户
     * @param  enterpriseUser:添加时dto对象
     * @return 返回类型条数
     */
    int addEnterprise(AddEnterpriseMeta enterpriseUser);
}
