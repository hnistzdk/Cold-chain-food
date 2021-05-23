package com.zdk.service.mainfest;

import com.zdk.mapper.ManifestMapper;
import com.zdk.pojo.Manifest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 17:05
 */
@Service("ManifestServiceImpl")
public class ManifestServiceImpl implements ManifestService{
    @Autowired
    ManifestMapper manifestMapper;

    @Override
    public List<Manifest> queryManifest(String manifestId) {
        return manifestMapper.queryManifest(manifestId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addManifest(Manifest manifest) {
        return manifestMapper.addManifest(manifest);
    }
}
