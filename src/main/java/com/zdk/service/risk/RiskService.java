package com.zdk.service.risk;

import com.zdk.pojo.Risk;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/30 10:54
 */
public interface RiskService {
    /**
     * 获取所有风险监测信息
     * @param map
     * @return List<Risk>
     */
    List<Risk> getRiskList(Map map);

    /**
     * 获取所有风险监测信息条数
     * @return int
     */
    int getRiskCount();
}
