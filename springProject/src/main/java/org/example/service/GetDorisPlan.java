package org.example.service;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.example.pojo.JsonData;
import org.example.pojo.PlanData;
import org.example.utils.DorisConnectionManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Data
public class GetDorisPlan {
    public List<PlanData> getResult(JsonData jsonData) {
        List<PlanData> planDataList = new ArrayList<>();
        // 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        try {
            Connection connection = DorisConnectionManager.getConnection(jsonData.getDataBase());
            String sql = "SELECT * FROM ods_dy_dispatch_plan WHERE `route_code`='"+jsonData.getRouteCode()+
                    "' AND `city_id`='"+jsonData.getCityId()+"'" + " AND `intelligent_id`='"+jsonData.getIntelligentId()+"'";

            // 创建ResultSetHandler，将查询结果转换为JavaBean对象列表
            ResultSetHandler<List<PlanData>> handler = new BeanListHandler<>(PlanData.class);

            // 执行查询
            planDataList = queryRunner.query(connection, sql, handler);

            // 遍历打印查询结果
//            for (PlanData planData : planDataList) {
//                System.out.println(planData);
//            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planDataList;
    }
}
