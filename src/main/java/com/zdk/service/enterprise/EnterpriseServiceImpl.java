package com.zdk.service.enterprise;

import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.EditMeta;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.mapper.EnterpriseMapper;
import com.zdk.pojo.EnterpriseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public EnterpriseUser enterpriseLogin(String id, String password,String email) {
        return enterpriseMapper.enterpriseLogin(id, password,email);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeEnterprise(String id) {
        return enterpriseMapper.removeEnterprise(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addEnterprise(EnterpriseUser enterpriseUser) {
        return enterpriseMapper.addEnterprise(enterpriseUser);
    }

    @Override
    public EditMeta showEnterprise(String id) {
        return enterpriseMapper.showEnterpriseUser(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyEnterpriseUser(EditMeta user) {
        return enterpriseMapper.modifyEnterpriseUser(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateLoginInfo(String id, String date) {
        return enterpriseMapper.updateLoginInfo(id, date);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyEnterprisePwd(AddEnterpriseMeta enterpriseUser) {
        HashMap map=new HashMap();
        map.put("id", enterpriseUser.getId());
        map.put("pwd", enterpriseUser.getPwd());
        return enterpriseMapper.modifyEnterprisePwd(map);
    }
}
