package com.zdk.mapper;

import com.zdk.pojo.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 13:44
 */
@Mapper
@Repository
public interface FoodMapper {
    /**
     *添加食品
     * @param  food:账号
     * @return 成功条数
     */
    int addFood(Food food);
    /**
     *删除食品
     * @param  id:食品id
     * @return 成功条数
     */
    int deleteFoodById(Integer id);
    /**
     *修改食品信息
     * @param  food:账号
     * @return 成功条数
     */
    int modifyFood(Food food);
    /**
     *查询食品信息
     * @param  map:参数键值对
     * @return Food的list
     */
    List<Food> queryFoodBy(Map map);

    /**
     *查询食品总记录数量
     * @param query
     * @return 总记录条数
     */
    int foodCount(String query);

    /**
     *分页查询食品信息
     * @param map
     * @return list
     */
    List<Food> getFood(Map map);

    /**
     *模糊查询食品信息
     * @param map
     * @return list
     */
    List<Food> fuzzyQueryFood(Map map);

    /**
     *获取食品信息显示
     * @param id
     * @return Food
     */
    Food getFoodById(Integer id);
}
