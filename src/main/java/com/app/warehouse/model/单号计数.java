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

    public 单号计数(LocalDate 日期, Integer 进仓计数, Integer 出仓计数) {
        this.日期 = 日期;
        this.进仓计数 = 进仓计数;
        this.出仓计数 = 出仓计数;
    }

    public LocalDate get日期() {
        return 日期;
    }

    public void set日期(LocalDate 日期) {
        this.日期 = 日期;
    }

    public Integer get进仓计数() {
        return 进仓计数;
    }

    public void set进仓计数(Integer 进仓计数) {
        this.进仓计数 = 进仓计数;
    }

    public Integer get出仓计数() {
        return 出仓计数;
    }

    public void set出仓计数(Integer 出仓计数) {
        this.出仓计数 = 出仓计数;
    }
}
