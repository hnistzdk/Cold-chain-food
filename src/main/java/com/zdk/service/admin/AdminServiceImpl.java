package com.zdk.service.admin;

import com.zdk.dto.AdminMeta;
import com.zdk.mapper.AdminMapper;
import com.zdk.pojo.AdminAndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/4/21 18:42
 */
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminAndUser adminLogin(String id, String password) {
        return adminMapper.adminLogin(id, password);
    }

    @Override
    public List<AdminMeta> getAdminList(Integer pageNum, Integer pageSize) {
        Map<String, Integer> map=new HashMap<>();
        map.put("startIndex", pageNum);
        map.put("pageSize", pageSize);
        return adminMapper.getAdminList(map);
    }
    @Override
    public int adminTotalPage() {
        return adminMapper.adminTotalPage();
    }

    @Override
    public List<AdminMeta> fuzzyQueryAdminList(String query, Integer pageNum, Integer pageSize) {
        Map map=new HashMap<>();
        map.put("query", query);
        map.put("startIndex", pageNum);
        map.put("pageSize", pageSize);
        return adminMapper.fuzzyQueryAdminList(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeAdmin(String id) {
        return adminMapper.removeAdmin(id);
    }

    @Override
    public AdminAndUser getAdminById(String id) {
        return adminMapper.getAdminById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateLoginInfo(String id, String date) {
        return adminMapper.updateLoginInfo(id, date);
    }
}
