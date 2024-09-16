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
 * @since 2024-09-16
 */
@Getter
@Setter
  @TableName("软工2202_09_05_29进出仓表")
public class 进出仓表 implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("单号")
      private String 单号;

    private Integer 次数;

    private Integer 类型;

      //@TableId("物料代码")
      private String 物料代码;

    private String 操作人员代码;

    private LocalDate 日期;

    private Integer 数量;

    private String 备注;
}
