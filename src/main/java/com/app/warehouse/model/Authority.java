package com.app.warehouse.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
  @TableName("软工2202_09_05_29权限管理")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("人员代码")
      private String 人员代码;

    private Integer 人员档案管理;

    private Integer 物料档案管理;

    private Integer 进出仓管理;

    private Integer 权限管理;

    private Integer 统计打印;

    public Authority(String 人员代码, Integer 人员档案管理, Integer 物料档案管理, Integer 进出仓管理, Integer 权限管理, Integer 统计打印) {
        this.人员代码 = 人员代码;
        this.人员档案管理 = 人员档案管理;
        this.物料档案管理 = 物料档案管理;
        this.进出仓管理 = 进出仓管理;
        this.权限管理 = 权限管理;
        this.统计打印 = 统计打印;
    }

    public String get人员代码() {
        return 人员代码;
    }

    public void set人员代码(String 人员代码) {
        this.人员代码 = 人员代码;
    }

    public Integer get人员档案管理() {
        return 人员档案管理;
    }

    public void set人员档案管理(Integer 人员档案管理) {
        this.人员档案管理 = 人员档案管理;
    }

    public Integer get物料档案管理() {
        return 物料档案管理;
    }

    public void set物料档案管理(Integer 物料档案管理) {
        this.物料档案管理 = 物料档案管理;
    }

    public Integer get进出仓管理() {
        return 进出仓管理;
    }

    public void set进出仓管理(Integer 进出仓管理) {
        this.进出仓管理 = 进出仓管理;
    }

    public Integer get权限管理() {
        return 权限管理;
    }

    public void set权限管理(Integer 权限管理) {
        this.权限管理 = 权限管理;
    }

    public Integer get统计打印() {
        return 统计打印;
    }

    public void set统计打印(Integer 统计打印) {
        this.统计打印 = 统计打印;
    }
}
