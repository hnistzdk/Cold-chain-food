package com.zdk.service.mainfest;

import com.zdk.pojo.Manifest;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 17:05
 */
public interface ManifestService {
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
}
