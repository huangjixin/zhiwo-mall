package com.zwotech.common.utils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by szsonic on 2017/2/24.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};
    //这里写上需要解密的key值

    @Override
    protected String convertProperty(String propertyName, String propertyValue)
    {

        //如果在加密属性名单中发现该属性
        if (isEncryptProp(propertyName))
        {
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            return decryptValue;
        }else {
            return propertyValue;
        }

    }

    /**
     * 循环遍历所有的key，是否需要解密
     * @param propertyName 需要解密的key
     * @return 
     */
    private boolean isEncryptProp(String propertyName)
    {
        for (String encryptName : encryptPropNames)
        {
            if (encryptName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }
}