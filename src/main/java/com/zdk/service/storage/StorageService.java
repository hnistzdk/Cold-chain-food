package com.zdk.service.storage;

import com.zdk.pojo.Storage;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/30 10:54
 */
public interface StorageService {
    /**
     *删除角色
     * @param id
     * @return List
     */
    List<Storage> getStorage(Integer id);
}
