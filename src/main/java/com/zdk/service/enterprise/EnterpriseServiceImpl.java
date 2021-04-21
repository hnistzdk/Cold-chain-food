package com.zdk.service.enterprise;

import com.zdk.mapper.enterprise.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
