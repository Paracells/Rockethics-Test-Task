package ru.paracells.rockethics.repository;


import org.apache.ibatis.annotations.*;
import ru.paracells.rockethics.model.Cat;

import java.util.List;

@Mapper
public interface CatsMapper {

    @Select("select * from cats")
    List<Cat> findAll();

    @Select("select name,age from cats where id=#{id}")
    @Results(value = {
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    Cat findById(Long id);

    @Delete("delete from cats where id=#{id}")
    Boolean deleteById(Long id);

    @Insert("insert into cats(name,age) values(#{name},#{age})")
    Integer save(Cat cat);

}
