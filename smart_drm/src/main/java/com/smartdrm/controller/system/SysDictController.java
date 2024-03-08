package com.smartdrm.controller.system;

import com.smartdrm.common.AjaxResult;
import com.smartdrm.entity.system.SysDictData;
import com.smartdrm.entity.system.SysDictType;
import com.smartdrm.entity.system.SysUserParam;
import com.smartdrm.service.system.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 数据字典
 * @author cxdpc
 * @date 2024/1/26 14:57
 */
@Controller
@RequestMapping("/system/sysDict")
public class SysDictController {

    @Autowired
    SysDictDataService sysDictDataService;

    @RequestMapping("/index")
    public String user(){
        return "system/dict";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public AjaxResult getDataGrid(SysUserParam param){
        List<SysDictType> sysUsers = sysDictDataService.getDataGrid(param);
        int count = sysDictDataService.getDataGridCount(param);
        return AjaxResult.success(sysUsers, count);
    }

    @RequestMapping("/dictDataGrid")
    @ResponseBody
    public AjaxResult dictDataGrid(String dictType){
        List<SysDictData> sysUsers = sysDictDataService.dictDataGrid(dictType);
        int count = sysDictDataService.getDictDataGridCount(dictType);
        return AjaxResult.success(sysUsers, count);
    }
}
