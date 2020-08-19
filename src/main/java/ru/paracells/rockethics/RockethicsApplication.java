package ru.paracells.rockethics;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.paracells.rockethics.model.Cat;

@SpringBootApplication
@MappedTypes(Cat.class)
@MapperScan("ru.paracells.rockethics.repository")
public class RockethicsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RockethicsApplication.class, args);
    }

}
