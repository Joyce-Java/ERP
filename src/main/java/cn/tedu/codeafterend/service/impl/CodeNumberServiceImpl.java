package cn.tedu.codeafterend.service.impl;

import cn.tedu.codeafterend.ex.ServiceException;
import cn.tedu.codeafterend.mapper.CodeNumberMapper;
import cn.tedu.codeafterend.mapper.DeptMapper;
import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.vo.CodeNumberStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.service.ICodeNumberService;
import cn.tedu.codeafterend.service.IDeptService;
import cn.tedu.codeafterend.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CodeNumberServiceImpl implements ICodeNumberService {
    @Autowired
    CodeNumberMapper deptMapper;


    @Override
    public List<CodeNumberStandardVO> list() {
        log.debug("開始處理【查詢銀行代號列表】業務");
        return deptMapper.list();
    }
}
