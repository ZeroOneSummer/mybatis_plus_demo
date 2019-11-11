package com.dimai.mybatis_plus_demo.mybatis.biz;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dimai.mybatis_plus_demo.mybatis.annotion.TableAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER")
@TableAware("user")  //用于自动生成sql表名
public class BizUser implements Serializable {
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
}
