package com.jt.projects.entitylayering.utils;
import com.jt.projects.entitylayering.entity.dos.DepartmentDO;
import com.jt.projects.entitylayering.entity.dos.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:13
 */
@Slf4j
public class MyBeanUtils {
    public static final <T> List<T> copyList(List<?> list,Class<T> targetClass){
        if (list == null){
            return Collections.emptyList();
        }
        List<T> copyList = new ArrayList<>();
        list.forEach(source->{
            copyList.add(copyBean(source,targetClass));
        });
        return copyList;
    }

    private static <T> T copyBean(Object source, Class<T> targetClass) {
        try {
            T t = targetClass.newInstance();
            BeanUtils.copyProperties(source,t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用序列化和反序列化将数据所有对象重新创建
     * @param list
     * @param <T>
     * @return
     */
    public static final <T> List<T> deepCopyList(List<T> list){

        /*ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper
                    .readValue(objectMapper.writeValueAsString(list), new TypeReference<List<T>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;*/
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(list);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        List<UserDO> userDOList = new ArrayList<>();
        userDOList.add(new UserDO(1L,"jiatai",new DepartmentDO(1L,"资管")));
        List<UserDO> copyList = deepCopyList(userDOList);
        System.out.println(userDOList);
        System.out.println(copyList);
        copyList.get(0).getDepartment().setDepartmentName("理财");
        System.out.println(userDOList);
        System.out.println(copyList);
    }
}
