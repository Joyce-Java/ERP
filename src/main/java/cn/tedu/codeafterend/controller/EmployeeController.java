package cn.tedu.codeafterend.controller;

import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
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
@RequestMapping("/employees")
@Api(tags = "01. 員工資料管理模塊")

public class EmployeeController {

    @Autowired
    IEmployeeDataService service;

    @ApiOperationSupport(order = 100)
    @ApiOperation("新增員工資料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "編號", required = true, dataType = "long"),
            @ApiImplicitParam(name = "employeeName", value = "員工姓名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "heaDeptId", value = "部門", required = true, dataType = "string"),
            @ApiImplicitParam(name = "salary", value = "薪水", required = true, dataType = "bigDecimal"),
            @ApiImplicitParam(name = "gender", value = "性別", required = true, dataType = "string"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "string"),
            @ApiImplicitParam(name = "idNumber", value = "身分證字號", required = true, dataType = "string"),
            @ApiImplicitParam(name = "birthday", value = "生日", required = true, dataType = "string"),
            @ApiImplicitParam(name = "phoneNumber", value = "電話號碼", required = true, dataType = "string"),
            @ApiImplicitParam(name = "educationBackground", value = "學歷", required = true, dataType = "string"),
            @ApiImplicitParam(name = "bankNumber", value = "銀行帳戶", required = true, dataType = "string"),
            @ApiImplicitParam(name = "hiredate", value = "聘用日期", required = true, dataType = "string")
    })
    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/system/employees/add-new')")
    public JsonResult<Void> addNew(EmployeeDataAddNewDTO employeeDataAddNewDTO) {
        System.out.println(employeeDataAddNewDTO.getBirthday());
        log.debug("開始執行增加員工資料的請求,參數:{}", employeeDataAddNewDTO);
        service.addNew(employeeDataAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    @ApiImplicitParam(name = "id", value = "員工數據id", required = true, dataType = "long")
    @ApiOperation("刪除員工數據管理")
    @PreAuthorize("hasAuthority('/system/employees/delete')")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("開始執行刪除員工資料的請求,參數:{}", id);
        service.delete(id);
        return JsonResult.ok();
    }


    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢員工列表功能")
    @GetMapping("")
    @PreAuthorize("hasAuthority('/system/employees/read')")
    public JsonResult<Void> list() {
        List<EmployeeDataStandardVO> list = service.list();

        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 421)
    @ApiOperation("根據id查員工資料")
    @GetMapping("/selectById/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/system/employees/read')")
    public JsonResult<Void> selectById(@PathVariable Long id) {
        EmployeeDataStandardVO list = service.selectById(id);
        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 422)
    @ApiOperation("根據id修改員工資料")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('/system/employees/update')")
    public JsonResult<Void> update(EmployeeDataAddNewDTO employeeDataAddNewDTO) {
        System.out.println("test="+employeeDataAddNewDTO);
         service.update(employeeDataAddNewDTO);

        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 422)
    @ApiOperation("查詢員工列表功能")
    @GetMapping("/dept")
    @PreAuthorize("hasAuthority('/system/employees/read')")
    public JsonResult<Void> selectDept() {
        List<DeptStandardVO> list = service.selectDept();

        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢離職員工列表功能")
    @GetMapping("/listByQuitdate")
    @PreAuthorize("hasAuthority('/system/employees/read')")
    public JsonResult<Void> listByQuitdate() {
        List<EmployeeDataStandardVO> list = service.listByQuitdate();

        return JsonResult.ok(list);
    }
}
