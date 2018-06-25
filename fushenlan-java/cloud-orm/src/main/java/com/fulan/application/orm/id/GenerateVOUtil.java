package com.fulan.application.orm.id;

import com.fulan.application.session.SessionUtil;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.application.util.util.IdAnnon;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @Description: 动态添加实体类或者集合的变量值
 * @author: guiyang
 * @date: 2017/11/21 16:34
 */
@Component
public class GenerateVOUtil {

    /**
     * 创建时间
     */
    private static final String CREATE_TIME = "createTime";
    private static final String GMT_TIME = "gmtCreate";
    /**
     * 修改时间
     */
    private static final String UPDATE_TIME = "updateTime";
    private static final String MODIFY_TIME = "modifyTime";
    private static final String GMT_MODIFIED = "gmtModified";
    /**
     * 创建人id
     */
    private static final String CREATE_BY_ID = "createById";
    private static final String CREATE_USER = "createUser";
    /**
     * 修改人id
     */
    private static final String UPDATE_BY_ID = "updateById";
    private static final String UPDATE_USER = "updateUser";
    private static final String MODIFY_USER = "modifyUser";


    public static <T> Long generate(T t) {
        Class<?> tClass = t.getClass();
        Field[] fields = tClass.getDeclaredFields();
        Long uuid = null;
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                String filedName = field.getName();
                if(field.isAnnotationPresent(IdAnnon.class)) {
                    if (value == null) {
                        uuid = SpringUtil.getBean(IdGenerator.class).generate();
                        field.set(t,uuid);
                    }
                }

                if(value == null) {
                    if (CREATE_TIME.equals(filedName)||GMT_TIME.equals(filedName)){
                        field.set(t, new Date());
                    }
                    if (CREATE_BY_ID.equals(filedName)||CREATE_USER.equals(filedName)) {
                        field.set(t, SessionUtil.getAccountId());
                    }
                }
                if (UPDATE_TIME.equals(filedName)||MODIFY_TIME.equals(filedName)||
                        GMT_MODIFIED.equals(filedName)) {
                    field.set(t, new Date());
                }
                if (UPDATE_BY_ID.equals(filedName)||UPDATE_USER.equals(filedName)
                         ||MODIFY_USER.equals(filedName)) {
                    field.set(t, SessionUtil.getAccountId());
                }
            }
        } catch (IllegalAccessException e) {
               e.printStackTrace();
        }
        return uuid;
    }

    public static <T> void generate(List<T> list) {
        for (T t :list) {
            generate(t);
        }
    }

}