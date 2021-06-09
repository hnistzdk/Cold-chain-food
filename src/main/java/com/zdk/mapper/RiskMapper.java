package com.zdk.mapper;

import com.zdk.pojo.Risk;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/30 10:55
 */
@Repository
@Mapper
public interface RiskMapper {
    /**
     * 获取所有风险监测信息
     * @param map
     * @return List<Risk>
     */
    List<Risk> getRiskList(Map map);

    /**
     * 获取所有风险监测信息条数
     * @param query
     * @return int
     */
    int getRiskCount(String query);

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

    /**
     * 模糊查询食品检测信息
     * @param map
     * @return int
     */
    List<Risk> fuzzyQueryRiskFood(Map map);
}
