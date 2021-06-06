package com.zdk.utils;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/**
 * @author zdk
 * @date 2021/6/6 20:59
 */
@Component
public class EncryptableUtil {

    public static final String ENCODED_PASSWORD_HINT="{cipher}";

    @Bean
    public static EncryptablePropertyDetector encryptablePropertyDetector(){
        return new EncryptablePropertyDetector() {
            //判断是否已加密
            @Override
            public boolean isEncrypted(String s) {
                if(s!=null){
                    return s.startsWith(ENCODED_PASSWORD_HINT);
                }
                return false;
            }

            //解密
            @Override
            public String unwrapEncryptedValue(String s) {
                return s.substring(ENCODED_PASSWORD_HINT.length());
            }
        };
    }

    //进行加密

    @Bean
    public static EncryptablePropertyResolver encryptablePropertyResolver() {
        return s -> {
            if (null != s && s.startsWith(ENCODED_PASSWORD_HINT)) {
                return new String(Base64Utils.decode(s.substring(ENCODED_PASSWORD_HINT.length()).getBytes()));
            }
            return s;
        };
    }
}
