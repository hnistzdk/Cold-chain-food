package com.zdk.mapper;

import com.zdk.pojo.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/30 10:55
 */
@Repository
@Mapper
public interface StorageMapper {
    /**
     *删除角色
     * @param id
     * @return List
     */
    List<Storage> getStorage(Integer id);
}
