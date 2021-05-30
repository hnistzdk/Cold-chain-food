package com.zdk.service.storage;

import com.zdk.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zdk
 * @date 2021/5/30 10:54
 */
@Service("StorageServiceImpl")
public class StorageServiceImpl implements StorageService{
    @Autowired
    StorageMapper storageMapper;
}
