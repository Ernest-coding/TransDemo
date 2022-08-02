package com.ernest.init;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Description 代码生成器
 * @Author Ernest
 * @Date 2022/6/2 9:02
 */
public class CodeBulider {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://ernest.work:3306/trans_dt?serverTimezone=GMT%2B8", "root", "766188487")
                .globalConfig(builder -> {
                    builder.author("Ernest") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\code_of_JAVA_2021-7\\duan\\TransDemo\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ernest") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "D:\\code_of_JAVA_2021-7\\duan\\TransDemo\\src\\main\\java\\com\\ernest\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("arrival_port_info") // 设置需要生成的表名
                            .addInclude("cargo_info")
                            .addInclude("cargo_tax_dictionary")
                            .addInclude("conveyance")
                            .addInclude("customer")
                            .addInclude("customs_declaration")
                            .addInclude("manager")
                            .addInclude("signal_trans")
                            .addInclude("way_bill")
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
