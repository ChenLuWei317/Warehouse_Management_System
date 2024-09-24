package com.app.warehouse.utils;



import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/warehouse?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("魏陈露") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\GitHub\\Warehouse_Management_System\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.app.warehouse") // 设置父包名
                            .entity("model") // 设置实体类包名
                            .mapper("dao") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .xml("mappers"); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();//entity中使用lombok
                    builder.mapperBuilder().enableMapperAnnotation().build();//mapper接口中默认添加mapper注解
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("软工2202_09_05_29单号计数","软工2202_09_05_29计量单位","软工2202_09_05_29进出仓表","软工2202_09_05_29权限管理","软工2202_09_05_29人员表","软工2202_09_05_29物料表")
                            .addTablePrefix("软工2202_09_05_29"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}

