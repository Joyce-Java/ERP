package cn.tedu.codeafterend.controller;

import cn.tedu.codeafterend.pojo.dto.BankAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.BankStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.service.IBankService;
import cn.tedu.codeafterend.service.IDeptService;
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
@RequestMapping("/banks")
@Api(tags = "05. 銀行帳戶管理")
public class BankController {

    @Autowired
    IBankService service;

    @ApiOperationSupport(order = 100)
    @ApiOperation("新增帳戶管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "codeName", value = "銀行代號及對應名稱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "salary", value = "薪資", required = true, dataType = "int"),
            @ApiImplicitParam(name = "bankNumber", value = "銀行帳號", required = true, dataType = "String"),
            @ApiImplicitParam(name = "employeeId", value = "對應的員工編號", required = true, dataType = "int"),
    })
    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/system/bank/add-new')")
    public JsonResult<Void> addNew(BankAddNewDTO bankAddNewDTO) {
        log.debug("開始執行增加【銀行帳戶】資料的請求,參數:{}", bankAddNewDTO);
        service.addNew(bankAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    @ApiImplicitParam(name = "id", value = "員工數據id", required = true, dataType = "long")
    @ApiOperation("刪除員工帳戶管理")
    @PreAuthorize("hasAuthority('/system/bank/delete')")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("開始執行刪除【員工帳戶】的請求,參數:{}", id);
        service.deleteById(id);
        return JsonResult.ok();
    }


    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢員工帳戶列表功能")
    @GetMapping("")
    @PreAuthorize("hasAuthority('/system/banks/read')")
    public JsonResult<Void> list() {
        List<BankStandardVO> list = service.list();
        return JsonResult.ok(list);
    }

}
