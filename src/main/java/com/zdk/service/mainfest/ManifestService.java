package com.zdk.service.mainfest;

import com.zdk.pojo.Manifest;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 17:05
 */
public interface ManifestService {
    /**
     * 查询货单
     * @param  manifestId:货单号
     * @return
     */
    List<Manifest> queryManifest(String manifestId);
    /**
     * 增加货单
     * @param  manifest
     * @return
     */
    int addManifest(Manifest manifest);
}
