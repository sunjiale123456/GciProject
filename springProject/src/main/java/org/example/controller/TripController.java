package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.mapper.PlanMapper;
import org.example.mapper.TripMapper;
import org.example.pojo.TripData;
import org.example.service.Calculate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/op")
public class TripController {

    @Resource
    TripMapper tripMapper;
    @Resource
    PlanMapper planMapper;

    @GetMapping("list")
    public Object list(String name) throws JsonProcessingException {
        //xx/?name=xxx  传递参数
        System.out.println(name);
        return name;
    }
    @GetMapping("trip")
    public List<TripData> getTripColumn()  {
        return tripMapper.findAllDoris();
    }
    @GetMapping("plan")
    public List<TripData> getPlanColumn()  {
        return planMapper.findAllMysql();
    }
//    @GetMapping("/trigger/{json}")
//    public ResponseEntity<String>  trigger(@PathVariable("json")String json)  {
//        System.out.println(json);
//        // 处理业务逻辑
//         new Calculate(json);
//        // 返回HTTP响应
//        return new ResponseEntity<>(json, HttpStatus.OK);
//    }
    @PostMapping("/trigger")
    public ResponseEntity<String>  tri(@RequestBody String json)  {
        // 处理业务逻辑
        Calculate calculate = new Calculate(json);
        calculate.init();
        // 返回HTTP响应
        return new ResponseEntity<>(json+"\n -->  代码正在开发中 。。", HttpStatus.OK);
    }
}


