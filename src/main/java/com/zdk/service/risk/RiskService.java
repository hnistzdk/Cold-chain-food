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

    /**
     * 增加食品风险检测信息
     * @param risk
     * @return int
     */
    int addRisk(Risk risk);

    /**
     * 删除食品风险检测信息
     * @param id
     * @return int
     */
    int deleteRisk(Integer id);

    /**
     * 修改食品检测信息
     * @param risk
     * @return int
     */
    int modifyRisks(Risk risk);
}
