package com.zpain.service.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author zhangjun
 * @date 2021/11/15  16:13
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {

//        AutoGenerator mpg = new AutoGenerator();
//        GlobalConfig gc = new GlobalConfig();
//        gc.setOutputDir("C:\\code\\nacos\\service" + "/src/main/java");
//        gc.setAuthor("Zpain");
//        gc.setOpen(false);
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        mpg.setGlobalConfig(gc);
//
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/zpain_test?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        mpg.setDataSource(dsc);
//
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.zpain.service");
//        pc.setEntity("pojo");
//        pc.setMapper("mapper");
//        mpg.setPackageInfo(pc);
//
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setController(null);
//        templateConfig.setService("");
//        templateConfig.setEntityKt("");
//        templateConfig.setServiceImpl("");
//        mpg.setTemplate(templateConfig);
//
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setEntityLombokModel(true);
//        strategy.setEntityTableFieldAnnotationEnable(true);
//        mpg.setStrategy(strategy);
//        mpg.execute();
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/zpain_test?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8"
                , "root", "root")
                .globalConfig(builder -> {
                    builder.author("Zpain")
                            .fileOverride()
                            .disableOpenDir()
                            .outputDir("C:\\code\\nacos\\service" + "/src/main/java");
                }).packageConfig(builder -> {
            builder.parent("com.zpain.service")
                    .entity("pojo")
                    .mapper("mapper");
//                    .xml("resources/mapper");
        }).templateConfig(builder -> {
            builder.controller(null)
                    .service(null)
                    .entityKt(null)
                    .serviceImpl(null);
        }).strategyConfig(builder -> {
            builder.entityBuilder().naming(NamingStrategy.underline_to_camel)
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .enableLombok()
                    .enableTableFieldAnnotation();
        }).execute();
    }
}
