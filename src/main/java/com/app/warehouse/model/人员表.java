package com.app.warehouse.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 魏陈露
 * @since 2024-10-10
 */
@Getter
@Setter
  @TableName("软工2202_09_05_29人员表")
public class 人员表 implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("人员代码")
      private String 人员代码;

    private String 密码;

    private String 姓名;

    private String 性别;

    private LocalDate 出生日期;

    private String 身份证号;

    private String 籍贯;

    private String 家庭地址;

    private String 联系电话;

    private String 备注;
}
