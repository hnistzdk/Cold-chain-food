package com.zdk.mapper;

import com.zdk.pojo.Manifest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 16:58
 */
@Mapper
@Repository
public interface ManifestMapper {
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
