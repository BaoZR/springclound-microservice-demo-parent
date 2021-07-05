package cn.demo.order.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


public class CodeGeneratorUtil {


  public static void main(String[] args) {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();
    //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    // 生成文件的输出目录
    System.out.println(System.getProperty("user.dir") + "/temp");
    gc.setOutputDir(System.getProperty("user.dir") + "/temp");
    // 指定生成的主键的ID类型
    gc.setIdType(null);
    // 开发人员
    gc.setAuthor("");
    // 是否打开输出目录
    gc.setOpen(false);
    // 是否在xml中添加二级缓存配置
    gc.setEnableCache(false);
    // 关闭 swagger2 模式
    gc.setSwagger2(false);
    // 实体命名方式
    gc.setEntityName("%sEntity");
    // mapper 命名方式
    gc.setMapperName("%sMapper");
    // Mapper xml 命名方式
    gc.setXmlName("%sMapper");
    // service 命名方式
    gc.setServiceName("%sService");
    // service impl 命名方式
    gc.setServiceImplName("%sServiceImpl");
    // controller 命名方式
    gc.setControllerName("%sController");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    // mysql 5.x
    dsc.setDriverName("com.mysql.jdbc.Driver");
    // mysql 8.0
    // dsc.setDriverName("com.mysql.cj.jdbc.Driver");

    // todo  需要修改成自己的
    dsc.setUrl("jdbc:mysql://192.168.101.60:3306/demo_order?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
    dsc.setUsername("cjl");
    dsc.setPassword("cjl1234");


    // 设置数据源
    mpg.setDataSource(dsc);
    // 包配置
    PackageConfig pc = new PackageConfig();
    // 父包名 , todo 需要修改
    pc.setParent("cn.demo.order");
    // 模块名
    pc.setModuleName("");
    // Entity包名
    pc.setEntity("entity");
    // Mapper包名
    pc.setMapper("mapper");
    // Controller包名
    pc.setController("controller");
    // Service包名
    pc.setService("service");
    mpg.setPackageInfo(pc);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    // underline_to_camel：转驼峰，no_change：无变化
    // 数据库表映射到实体的命名策略
    strategy.setNaming(NamingStrategy.underline_to_camel);
    // 数据库表字段映射到实体的命名策略
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    // 【实体】是否为lombok模型（默认 false）
    strategy.setEntityLombokModel(true);
    // 实体增加  @TableId 注解
    strategy.setEntityTableFieldAnnotationEnable(true);

    // 生成 @RestController 控制器
    strategy.setRestControllerStyle(true);
    // 实体父类
    //strategy.setSuperEntityClass(BaseEntity.class);
    // 实体父类中的字段，多个用逗号分隔
    //strategy.setSuperEntityColumns("id");
    // 表名称
    strategy.setInclude("order_base".split(","));
    // 驼峰转连字符
    strategy.setControllerMappingHyphenStyle(true);
    // 表前缀
    //strategy.setTablePrefix("t_");
    mpg.setStrategy(strategy);

    mpg.execute();
  }

}
