package com.zdk.service.storage;

import com.zdk.pojo.Storage;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/30 10:54
 */
public interface StorageService {
    /**
     *获得站点信息
     * @param map
     * @return List
     */
    List<Storage> getStorage(Map map);

    /**
     *获得站点信息
     * @param id
     * @return int
     */
    int deleteStorage(int id);

    /**
     *修改站点信息
     * @param storage
     * @return int
     */
    int modifyStorage(Storage storage);
}
