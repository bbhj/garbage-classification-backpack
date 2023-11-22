package cn.czh0123.garbageclassification;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author czh
 */
@MapperScan("cn.czh0123.garbageclassification.mapper")
@SpringBootApplication
public class GCBApplication {
    public static void main(String[] args) {
        SpringApplication.run(GCBApplication.class, args);
    }
}