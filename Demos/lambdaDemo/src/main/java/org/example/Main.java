package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构树
 * @author $USER
 * @date ${YEAR}-${MONTH}-${DAY} ${TIME}
 */
public class Main {
    public static List<Map<String, Object>> buildTree(List<Map<String, String>> list) {
        // 创建一个映射以便通过id快速查找map
        Map<String, Map<String, Object>> idMap = new HashMap<>();
        for (Map<String, String> item : list) {
            Map<String, Object> newItem = new HashMap<>(item);
            idMap.put(item.get("id"), newItem);
        }

        // 存放根节点
        List<Map<String, Object>> tree = new ArrayList<>();

        // 构建树结构
        for (Map<String, String> item : list) {
            String parentId = item.get("parentId");
            Map<String, Object> currentItem = idMap.get(item.get("id"));

            if (parentId == null || parentId.isEmpty()) {
                tree.add(currentItem); // 根节点
            } else {
                Map<String, Object> parentItem = idMap.get(parentId);
                if (parentItem != null) {
                    if (!parentItem.containsKey("children")) {
                        parentItem.put("children", new ArrayList<Map<String, Object>>());
                    }
                    ((List<Map<String, Object>>) parentItem.get("children")).add(currentItem);
                }
            }
        }

        return tree;
    }

    public static void main(String[] args) {

        List<Map<String, String>> list = new ArrayList<>();
        String json = "[" +
                "{\"id\": \"1\", \"text\": \"总部\", \"parentId\": null}," +
                "{\"id\": \"2\", \"text\": \"研发部\", \"parentId\": \"1\"}," +
                "{\"id\": \"3\", \"text\": \"市场部\", \"parentId\": \"1\"}," +
                "{\"id\": \"4\", \"text\": \"财务部\", \"parentId\": \"1\"}," +
                "{\"id\": \"5\", \"text\": \"安卓开发组\", \"parentId\": \"2\"}," +
                "{\"id\": \"6\", \"text\": \"前端开发组\", \"parentId\": \"2\"}," +
                "{\"id\": \"7\", \"text\": \"市场调研组\", \"parentId\": \"3\"}," +
                "{\"id\": \"8\", \"text\": \"市场推广组\", \"parentId\": \"3\"}" +
                "]";

        ObjectMapper mapper = new ObjectMapper();
        try {
            list = mapper.readValue(json, new TypeReference<List<Map<String, String>>>() {});
            // 使用list变量
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> tree = buildTree(list);

        // 输出构建的树结构
        System.out.println(tree);
    }
}