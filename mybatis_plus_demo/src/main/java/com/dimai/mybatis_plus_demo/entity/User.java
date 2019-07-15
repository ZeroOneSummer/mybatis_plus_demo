package com.dimai.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author z.o.s
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER")
public class User implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Long age;

    /**
     * 邮箱
     */
    private String email;


    public interface Iface{}
    public interface Client{}

}
