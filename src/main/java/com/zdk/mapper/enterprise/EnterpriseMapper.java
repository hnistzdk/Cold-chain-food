package com.zdk.mapper.enterprise;

import com.zdk.dto.AdminMeta;
import com.zdk.dto.EnterpriseMeta;
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
     * @return 登录成功的结果>0则成功
     */
    int enterpriseLogin(@Param("id") String id, @Param("password") String password);

    /**
     *获取企业用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<EnterpriseMeta> getEnterpriseList(Map<String,Integer> map);

    /**
     *模糊查询企业用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<EnterpriseMeta> fuzzyQueryEnterpriseList(Map<String,Integer> map);
    /**
     *获取企业用户的人数
     * @return 人数
     */
    int enterpriseTotalPage();
}
