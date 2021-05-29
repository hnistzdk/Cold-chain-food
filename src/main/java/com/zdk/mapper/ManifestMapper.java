package com.zdk.mapper;

import com.zdk.pojo.Manifest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 16:58
 */
@Mapper
@Repository
public interface ManifestMapper {
    /**
     * 查询货单
     * @param  map:参数map
     * @return
     */
    List<Manifest> queryManifest(Map map);

    /**
     * 增加货单
     * @param  manifest
     * @return
     */
    int addManifest(Manifest manifest);

    /**
     * 获取订单总数
     * @return 条数
     */
    int manifestCount();

    /**
     * 根据id获取货单
     * @param manifestId
     * @return Manifest
     */
    Manifest getManifestById(String manifestId);

    /**
     * 根据货单号修改货单信息
     * @param manifest
     * @return int
     */
    int modifyManifest(Manifest manifest);

    /**
     * 根据货单号删除货单
     * @param manifestId
     * @return int
     */
    int deleteManifest(String manifestId);
}
