package com.zdk.service.storage;

import com.zdk.mapper.StorageMapper;
import com.zdk.pojo.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/30 10:54
 */
@Service("StorageServiceImpl")
public class StorageServiceImpl implements StorageService{
    @Autowired
    StorageMapper storageMapper;

    @Override
    public List<Storage> getStorage(Map map) {
        return storageMapper.getStorage(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteStorage(int id) {
        return storageMapper.deleteStorage(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyStorage(Storage storage) {
        return storageMapper.modifyStorage(storage);
    }

    @Override
    public int getStorageCount() {
        return storageMapper.getStorageCount();
    }

    @Override
    public int addStorage(String uid, String storageArea) {
        return storageMapper.addStorage(uid, storageArea);
    }
}
