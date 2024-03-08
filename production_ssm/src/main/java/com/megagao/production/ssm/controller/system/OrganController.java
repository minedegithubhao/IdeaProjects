package com.megagao.production.ssm.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/organ")
public class OrganController {

    // 地区数据，这里用一个简单的List模拟
    private List<Region> regionData = Arrays.asList(
            new Region(1, "地区1", Arrays.asList(
                    new Region(101, "地区1-1"),
                    new Region(102, "地区1-2")
            )),
            new Region(2, "地区2", Arrays.asList(
                    new Region(201, "地区2-1"),
                    new Region(202, "地区2-2")
            ))
            // 可以根据实际需求提供更多的地区数据
    );

    // 处理地区数据的接口
    @RequestMapping("/list")
    @ResponseBody
    public List<Region> getRegionData() {
        return regionData;
    }

    // 地区类
    private static class Region {
        private int id;
        private String text;
        private List<Region> children;

        public Region(int id, String text) {
            this.id = id;
            this.text = text;
        }

        public Region(int id, String text, List<Region> children) {
            this.id = id;
            this.text = text;
            this.children = children;
        }

        public int getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public List<Region> getChildren() {
            return children;
        }
    }
}
