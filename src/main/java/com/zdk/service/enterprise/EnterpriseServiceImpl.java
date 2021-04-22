package com.zdk.service.enterprise;

import com.zdk.dto.EnterpriseMeta;
import com.zdk.mapper.enterprise.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/4/21 18:58
 */
@Service("EnterpriseServiceImpl")
public class EnterpriseServiceImpl implements EnterpriseService{

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public int enterpriseLogin(String id, String password) {
        return enterpriseMapper.enterpriseLogin(id, password);
    }

    @Override
    public List<EnterpriseMeta> getEnterpriseList(Integer pageNum, Integer pageSize) {
        Map<String, Integer> map=new HashMap<>();
        map.put("startIndex", pageNum);
        map.put("pageSize", pageSize);
        return enterpriseMapper.getEnterpriseList(map);
    }

    @Override
    public List<EnterpriseMeta> fuzzyQueryEnterpriseList(String query, Integer pageNum, Integer pageSize) {
        Map map=new HashMap<>();
        map.put("query", query);
        map.put("startIndex", pageNum);
        map.put("pageSize", pageSize);
        return enterpriseMapper.fuzzyQueryEnterpriseList(map);
    }

    @Override
    public int enterpriseTotalPage() {
        return enterpriseMapper.enterpriseTotalPage();
    }
}
