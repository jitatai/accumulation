package com.jt.accumulation.jpa;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/1 10:31
 */
public class BaseDao<T> {
    private static BasicDataSource dataSource;
    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/trd?serverTimezone=UTC");
        dataSource.setUsername("xams");
        dataSource.setPassword("xpar");
    }

    private Class<T> beanClass;
    // 得到jdbcTemplate
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    BaseDao(){
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        beanClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }


    public void add(T bean) throws IllegalAccessException {
        Field[] declaredFields = beanClass.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        Table table = bean.getClass().getAnnotation(Table.class);
        stringBuilder.append(table.value());
        stringBuilder.append("(");
        for (int i = 0; i < declaredFields.length; i++) {
            stringBuilder.append(declaredFields[i].getName());
            if (i < declaredFields.length - 1){
                stringBuilder.append(",");
            }
        }

        stringBuilder.append(") VALUES(");
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            stringBuilder.append(declaredFields[i].get(bean));
            if (i < declaredFields.length - 1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");

        jdbcTemplate.execute(stringBuilder.toString());
        System.out.println(stringBuilder);
    }
}
