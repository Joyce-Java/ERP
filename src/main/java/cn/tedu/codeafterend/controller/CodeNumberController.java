package cn.tedu.codeafterend.controller;

import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.CodeNumberStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.service.ICodeNumberService;
import cn.tedu.codeafterend.service.IDeptService;
import cn.tedu.codeafterend.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/codeNumbers")
@Api(tags = "04. 銀行代號管理模塊")

public class CodeNumberController {
    @Autowired
  ICodeNumberService iCodeNumberService;

    @ApiOperationSupport(order = 420)
    @ApiOperation("查詢銀行代號列表功能")
    @GetMapping("")
    public JsonResult<Void> list() {
        List<CodeNumberStandardVO> list = iCodeNumberService.list();
        return JsonResult.ok(list);
    }

}
