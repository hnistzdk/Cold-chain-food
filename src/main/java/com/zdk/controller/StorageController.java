package com.zdk.controller;

import com.zdk.service.storage.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zdk
 * @date 2021/5/30 10:57
 */
@RestController
public class StorageController {
    @Autowired
    @Qualifier("StorageServiceImpl")
    StorageServiceImpl storageService;

}
