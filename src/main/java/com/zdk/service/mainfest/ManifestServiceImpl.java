package com.zdk.service.mainfest;

import com.sun.mail.imap.protocol.ID;
import com.zdk.dto.SelectFoodMeta;
import com.zdk.mapper.ManifestMapper;
import com.zdk.pojo.Manifest;
import com.zdk.pojo.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 17:05
 */
@Service("ManifestServiceImpl")
public class ManifestServiceImpl implements ManifestService{
    @Autowired
    ManifestMapper manifestMapper;

    @Override
    public List<Manifest> queryManifest(Map map) {
        return manifestMapper.queryManifest(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addManifest(Manifest manifest) {
        return manifestMapper.addManifest(manifest);
    }

    @Override
    public int manifestCount() {
        return manifestMapper.manifestCount();
    }

    @Override
    public Manifest getManifestById(String manifestId) {
        return manifestMapper.getManifestById(manifestId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyManifest(Manifest manifest) {
        return manifestMapper.modifyManifest(manifest);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteManifest(String manifestId) {
        return manifestMapper.deleteManifest(manifestId);
    }

    @Override
    public List<SelectFoodMeta> getAllFoodName() {
        return manifestMapper.getAllFoodName();
    }

    @Override
    public List<Storage> getAllStorageName() {
        return manifestMapper.getAllStorageName();
    }

    @Override
    public List<Manifest> getManifestBySendOrGet(Map map) {
        return manifestMapper.getManifestBySendOrGet(map);
    }

    @Override
    public int getManifestBySendOrGetCount(Map map) {
        return manifestMapper.getManifestBySendOrGetCount(map);
    }

    @Override
    public int updateReceivedStatus(String id) {
        return manifestMapper.updateReceivedStatus(id);
    }
}
