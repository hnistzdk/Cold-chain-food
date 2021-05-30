package com.zdk.service.risk;

import com.zdk.mapper.RiskMapper;
import com.zdk.pojo.Risk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/30 10:54
 */
@Service("RiskServiceImpl")
public class RiskServiceImpl implements RiskService{
    @Autowired
    RiskMapper riskMapper;
    @Override
    public List<Risk> getRiskList(Map map) {
        return riskMapper.getRiskList(map);
    }

    @Override
    public int getRiskCount() {
        return riskMapper.getRiskCount();
    }
}
