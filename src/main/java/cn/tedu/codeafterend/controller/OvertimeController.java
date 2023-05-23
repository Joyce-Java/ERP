package cn.tedu.codeafterend.controller;

import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.OvertimeAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.CodeNumberStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import cn.tedu.codeafterend.service.ICodeNumberService;
import cn.tedu.codeafterend.service.IEmployeeDataService;
import cn.tedu.codeafterend.service.IOvertimeService;
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
@RequestMapping("/overtimes")
@Api(tags = "06. 加班時數管理模塊")
public class OvertimeController {
    @Autowired
    IOvertimeService iOvertimeService;



    @ApiOperationSupport(order = 100)
    @ApiOperation("新增加班時數資料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "weekdayOvertime", value = "平日加班(時數)", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "holidayOvertime", value = "休假日加班(時數)", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "legalHolidayOvertime", value = "例假加班(時數)", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "statutoryOvertime", value = "國定假日加班(時數)", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "EmployeeId", value = "員工編號", required = true, dataType = "string"),
            @ApiImplicitParam(name = "overtimeData", value = "加班時間", required = true, dataType = "string"),
    })
    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/system/overtimes/add-new')")
    public JsonResult<Void> addNew(OvertimeAddNewDTO overtimeAddNewDTO) {
        log.debug("開始執行增加班時數的請求,參數:{}", overtimeAddNewDTO);
        iOvertimeService.addNew(overtimeAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    @ApiImplicitParam(name = "id", value = "員工數據id", required = true, dataType = "long")
    @ApiOperation("刪除加班時數管理")
    @PreAuthorize("hasAuthority('/system/overtimes/delete')")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("開始執行刪除加班時數的請求,參數:{}", id);
        iOvertimeService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 420)
    @ApiOperation("修改員工加班時數功能")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('/system/overtimes/update')")
    public JsonResult<Void> update(OvertimeAddNewDTO overtimeAddNewDTO) {
        iOvertimeService.updateById(overtimeAddNewDTO);
        return JsonResult.ok();
    }


    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢加班時數功能")
    @GetMapping("")
    @PreAuthorize("hasAuthority('/system/overtimes/read')")
    public JsonResult<Void> list() {
        List<OvertimeStandardVO> list = iOvertimeService.list();
        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 421)
    @ApiOperation("根據月份查詢對應時數")
    @GetMapping("/getAllById/month")
    @PreAuthorize("hasAuthority('/system/overtimes/read')")
    public JsonResult<Void> getByMonthSelectList(@RequestParam String month) {
        List<OvertimeStandardVO> list = iOvertimeService.getByMonthSelectList(month);
        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 421)
    @ApiOperation("根據ID,月份查詢對應時數")
    @GetMapping("/getAllByIdAndMonth/{id:[0-9]+}/month")
    @PreAuthorize("hasAuthority('/system/overtimes/read')")
    public JsonResult<Void> getAllById(@PathVariable Long id,@RequestParam String month) {
        List<OvertimeStandardVO> list = iOvertimeService.getAllById(id,month);

        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢請假時數功能")
    @GetMapping("/selectById/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/system/overtimes/read')")
    public JsonResult<Void> selectById(@PathVariable Long id) {
        OvertimeStandardVO overtimeStandardVO = iOvertimeService.selectById(id);
        return JsonResult.ok(overtimeStandardVO);
    }

    @ApiOperationSupport(order = 422)
    @ApiOperation("查詢")
    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('/system/overtimes/read')")
    public JsonResult<Void> selectEmployees() {
        List<EmployeeDataStandardVO> employeeDataStandardVOS = iOvertimeService.selectEmployees();

        return JsonResult.ok(employeeDataStandardVOS);
    }

}
