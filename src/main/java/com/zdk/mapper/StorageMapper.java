package com.zdk.mapper;

import com.zdk.pojo.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/30 10:55
 */
@Repository
@Mapper
public interface StorageMapper {
    /**
     *获得站点信息
     * @param map
     * @return List
     */
    List<Storage> getStorage(Map map);

    /**
     *删除站点信息
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

    /**
     *获取信息条数
     * @return int
     */
    int getStorageCount();
}
