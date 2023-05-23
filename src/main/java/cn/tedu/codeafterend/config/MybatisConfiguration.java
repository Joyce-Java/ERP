package cn.tedu.codeafterend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@MapperScan("cn.tedu.codeafterend.mapper")
public class MybatisConfiguration {


    public MybatisConfiguration() {
        System.out.println("創建配置類:MybatisConfiguration");
    }

}
