package cn.tedu.codeafterend.controller;

import cn.tedu.codeafterend.pojo.vo.SalaryManagementStandardVO;
import cn.tedu.codeafterend.service.ISalaryManagementService;
import cn.tedu.codeafterend.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/salaryManagement")
@Api(tags = "07. 薪資計算管理")
public class SalaryManagementController {

    @Autowired
    ISalaryManagementService iSalaryManagementService ;

    @ApiOperationSupport(order = 420)
    @ApiOperation("計算員工薪資功能")
    @GetMapping("")
    @PreAuthorize("hasAuthority('/system/salaryManagement/add-new')")
    public JsonResult<Void> calculate() {
        iSalaryManagementService.calculateSalary();
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 422)
    @ApiOperation("查詢前月薪資列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('/system/salaryManagement/read')")
    public JsonResult<Void> list() {
        List<SalaryManagementStandardVO> list = iSalaryManagementService.list();
        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 421)
    @ApiOperation("查詢前月薪資列表")
    @PostMapping("/getByMonthSelectList/{month}")
    @PreAuthorize("hasAuthority('/system/salaryManagement/read')")
    public JsonResult<Void> getByMonthSelectList(@PathVariable String month) {
        List<SalaryManagementStandardVO> list = iSalaryManagementService.getByMonthSelectList(month);
        return JsonResult.ok(list);
    }
}
