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
 * @since 2024-09-24
 */
@Getter
@Setter
  @TableName("软工2202_09_05_29单号计数")
public class 单号计数 implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("日期")
      private LocalDate 日期;

    private Integer 进仓计数;

    private Integer 出仓计数;
}
