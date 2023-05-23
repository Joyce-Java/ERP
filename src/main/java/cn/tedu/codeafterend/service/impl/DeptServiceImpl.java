package cn.tedu.codeafterend.service.impl;

import cn.tedu.codeafterend.ex.ServiceException;
import cn.tedu.codeafterend.mapper.DeptMapper;
import cn.tedu.codeafterend.mapper.EmployeeDataMapper;
import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.entity.EmployeeData;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.service.IDeptService;
import cn.tedu.codeafterend.service.IEmployeeDataService;
import cn.tedu.codeafterend.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements IDeptService {
    @Autowired
    DeptMapper deptMapper;

    @Autowired
    EmployeeDataMapper employeeDataMapper;

    @Override
    public void addNew(DeptAddNewDTO deptAddNewDTO) {
        String name = deptAddNewDTO.getDeptName();
        int result = deptMapper.countName(name);
        log.debug("查到相同的名稱共{}筆", result);

        if (result != 0) {
            String massage = "添加失敗,該部門名稱已存在,請重新輸入";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }

        Dept dept = new Dept();
        BeanUtils.copyProperties(deptAddNewDTO, dept);
        deptMapper.insert(dept);
    }

    @Override
    public void deleteById(Long id) {
        DeptStandardVO result = deptMapper.getStandardById(id);
        if (result == null){
            String message = "刪除失敗，嘗試刪除的數據不存在";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        List<EmployeeDataStandardVO> list = employeeDataMapper.list();
        for (EmployeeDataStandardVO employeeDataStandardVO:list){
            Long heaDeptId = employeeDataStandardVO.getHeaDeptId();
            System.out.println("heaDeptId="+heaDeptId);
            if (id == heaDeptId){
                String message = "刪除失敗，部門中還有員工,不可刪除部門";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
            }

        }
        log.debug("開始執行【刪除部門數據】業務");
        int rows = deptMapper.delete(id);
        if (rows != 1){
            String message = "刪除失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public List<DeptStandardVO> list() {
        log.debug("開始處理【查詢員工列表】業務");
        return deptMapper.list();
    }
}
