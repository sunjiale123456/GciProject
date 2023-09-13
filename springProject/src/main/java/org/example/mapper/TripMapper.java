package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.TripData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository   //加这个注解，spring就会自动识别是一个组件，自动会帮我们new一个类，之后我们去VideoSetvice接口定义方法
public interface TripMapper {
    @Select("select * from ods_dy_triplog where triplog_begin_time is not null limit 100")
    List<TripData> findAllDoris();
}

