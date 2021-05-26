package com.zdk.service.right;

import com.zdk.mapper.RightMapper;
import com.zdk.pojo.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/17 19:19
 */
@Service("RightServiceImpl")
public class RightServiceImpl implements RightService{

    @Autowired
    private RightMapper rightMapper;

    @Override
    public List<Right> getRights(Map map) {
        return rightMapper.getRights(map);
    }

    @Override
    public int getRightsCount() {
        return rightMapper.getRightsCount();
    }
}
