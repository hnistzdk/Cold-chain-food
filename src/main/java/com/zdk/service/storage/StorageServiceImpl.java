package com.zdk.service.storage;

import com.zdk.mapper.StorageMapper;
import com.zdk.pojo.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/30 10:54
 */
@Service("StorageServiceImpl")
public class StorageServiceImpl implements StorageService{
    @Autowired
    StorageMapper storageMapper;

    @Override
    public List<Storage> getStorage(Integer id) {
        return storageMapper.getStorage(id);
    }
}
