package cn.tedu.codeafterend.controller;

import cn.tedu.codeafterend.pojo.dto.AbsenceTrackerAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.OvertimeAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.AbsenceTrackerVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import cn.tedu.codeafterend.service.IAbsenceTrackerService;
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
@RequestMapping("/absenceTracker")
@Api(tags = "07. 請假時數管理模塊")
public class AbsenceTrackerController {
    @Autowired
    IAbsenceTrackerService iAbsenceTrackerService;

    @ApiOperationSupport(order = 100)
    @ApiOperation("新增請假時數資料")
    @ApiImplicitParams({
         //TODO 內容尚未完整
    })
    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/system/absenceTracker/add-new')")
    public JsonResult<Void> addNew(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO) {
        log.debug("開始執行增加請假時數的請求,參數:{}", absenceTrackerAddNewDTO);
        iAbsenceTrackerService.addNew(absenceTrackerAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 100)
    @ApiOperation("新增請假時數資料")
    @ApiImplicitParams({
            //TODO 內容尚未完整
    })
    @GetMapping("")
    @PreAuthorize("hasAuthority('/system/absenceTracker/read')")
    public JsonResult<Void> list() {
        List<AbsenceTrackerVO> list = iAbsenceTrackerService.list();

        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 420)
    @ApiOperation("修改請假時數功能")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('/system/absenceTracker/update')")
    public JsonResult<Void> update(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO) {
        iAbsenceTrackerService.updateById(absenceTrackerAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    @ApiImplicitParam(name = "id", value = "員工數據id", required = true, dataType = "long")
    @ApiOperation("刪除出缺勤資料管理")
    @PreAuthorize("hasAuthority('/system/absenceTracker/delete')")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("開始執行刪除【出缺勤資料】的請求,參數:{}", id);
        iAbsenceTrackerService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 421)
    @ApiOperation("根據ID,月份查詢對應時數")
    @GetMapping("/getAllByIdAndMonth/{id:[0-9]+}/month")
    @PreAuthorize("hasAuthority('/system/absenceTracker/read')")
    public JsonResult<Void> getAllByIdAndMonth(@PathVariable Long id,@RequestParam String month) {
        List<AbsenceTrackerVO> list = iAbsenceTrackerService.getAllById(id, month);
        return JsonResult.ok(list);

    }

    @ApiOperationSupport(order = 421)
    @ApiOperation("根據月份查詢對應時數")
    @GetMapping("/getAllById/month")
    @PreAuthorize("hasAuthority('/system/absenceTracker/read')")
    public JsonResult<Void> getByMonthSelectList(@RequestParam String month) {
        List<AbsenceTrackerVO> list = iAbsenceTrackerService.getByMonthSelectList(month);
        return JsonResult.ok(list);
    }

    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢加班時數功能")
    @GetMapping("/selectById/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/system/absenceTracker/read')")
    public JsonResult<Void> selectById(@PathVariable Long id) {
        AbsenceTrackerVO absenceTrackerVO = iAbsenceTrackerService.selectById(id);

        return JsonResult.ok(absenceTrackerVO);
    }

    @ApiOperationSupport(order = 422)
    @ApiOperation("查詢員工姓名")
    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('/system/absenceTracker/read')")
    public JsonResult<Void> selectEmployees() {
        List<EmployeeDataStandardVO> employeeDataStandardVOS = iAbsenceTrackerService.selectEmployees();

        return JsonResult.ok(employeeDataStandardVOS);
    }
}
