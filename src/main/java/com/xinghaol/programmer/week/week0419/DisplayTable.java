package com.xinghaol.programmer.week.week0419;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/4/19 10:52 上午
 * @Description:
 */
public class DisplayTable {
    public List<List<String>> displayTable(List<List<String>> orders) {
        if (orders.size() <= 0) {
            return new ArrayList<>();
        }

        Map<String, Map<String, Integer>> foodMap = new HashMap<>();
        for (List<String> foodItem : orders) {
            String tableNumber = foodItem.get(1);
            String food = foodItem.get(2);
            if (foodMap.get(tableNumber) == null) {
                Map<String, Integer> integerMap = new HashMap<>();
                integerMap.put(food, 1);
                foodMap.put(tableNumber, integerMap);
            } else {
                Map<String, Integer> integerMap = foodMap.get(tableNumber);
                if (integerMap.containsKey(food)) {
                    integerMap.put(food, integerMap.get(food) + 1);
                } else {
                    integerMap.put(food, 1);
                    foodMap.put(tableNumber, integerMap);
                }
            }
        }

        List<String> foods = new ArrayList<>();
        List<Integer> tableNumberList = new ArrayList<>();
        for (String tableNumber : foodMap.keySet()) {
            tableNumberList.add(Integer.parseInt(tableNumber));
            Map<String, Integer> integerMap = foodMap.get(tableNumber);
            for (String food : integerMap.keySet()) {
                if (!foods.contains(food)) {
                    foods.add(food);
                }
            }
        }
        Collections.sort(foods);
        Collections.sort(tableNumberList);

        List<List<String>> result = new ArrayList<>();

        // 构造表头
        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(foods);
        result.add(title);

        // 统计数据
        for (Integer tableNumber : tableNumberList) {
            Map<String, Integer> integerMap = foodMap.get(tableNumber + "");
            System.out.println(integerMap);
            List<String> list = new ArrayList<>();
            list.add(tableNumber + "");
            for (String food : foods) {
                Integer integer = integerMap.get(food);
                if (integer != null) {
                    list.add(integer + "");
                } else {
                    list.add("0");
                }
            }

            result.add(list);
        }

        return result;
    }

    private class Message {
        private int tableNumber;
        private String foodItem;
        private int count;

        public Message(int tableNumber, String foodItem) {
            this.tableNumber = tableNumber;
            this.foodItem = foodItem;
        }
    }

    @Test
    public void test() {
        List<List<String>> param = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("David");
        list1.add("3");
        list1.add("Ceviche");
        param.add(list1);

        List<String> list2 = new ArrayList<>();
        list2.add("Corina");
        list2.add("10");
        list2.add("Beef Burrito");
        param.add(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("David");
        list3.add("3");
        list3.add("Fried Chicken");
        param.add(list3);

        List<String> list4 = new ArrayList<>();
        list4.add("Carla");
        list4.add("5");
        list4.add("Water");
        param.add(list4);

        List<String> list5 = new ArrayList<>();
        list5.add("Carla");
        list5.add("5");
        list5.add("Ceviche");
        param.add(list5);

        List<String> list6 = new ArrayList<>();
        list6.add("Rous");
        list6.add("3");
        list6.add("Ceviche");
        param.add(list6);

        List<List<String>> lists = displayTable(param);
        System.out.println(JSON.toJSONString(lists));
    }
}
