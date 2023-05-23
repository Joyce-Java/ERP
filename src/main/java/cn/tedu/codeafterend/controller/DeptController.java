package cn.tedu.codeafterend.controller;

import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.service.IDeptService;
import cn.tedu.codeafterend.service.IEmployeeDataService;
import cn.tedu.codeafterend.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/depts")
@Api(tags = "02. 部門管理模塊")

public class DeptController {

    @Autowired
    IDeptService service;

    @ApiOperationSupport(order = 100)
    @ApiOperation("新增相册管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "編號", required = true, dataType = "long"),
            @ApiImplicitParam(name = "deptName", value = "部門名稱", required = true, dataType = "long"),
            @ApiImplicitParam(name = "deptPresentation", value = "部門簡介", required = true, dataType = "long"),
    })
    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/system/depts/add-new')")
    public JsonResult<Void> addNew(DeptAddNewDTO deptAddNewDTO) {
        log.debug("開始執行增加員工資料的請求,參數:{}", deptAddNewDTO);
        service.addNew(deptAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    @ApiImplicitParam(name = "id", value = "員工數據id", required = true, dataType = "long")
    @ApiOperation("刪除員工數據管理")
    @PreAuthorize("hasAuthority('/system/depts/delete')")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("開始執行刪除員工資料的請求,參數:{}", id);
        service.deleteById(id);
        return JsonResult.ok();
    }


    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢員工列表功能")
    @GetMapping("")
    @PreAuthorize("hasAuthority('/system/depts/read')")
    public JsonResult<Void> list() {
        List<DeptStandardVO> list = service.list();
        return JsonResult.ok(list);
    }

    //TODO 少一個修改功能

}
