package cn.tedu.codeafterend.service.impl;

import cn.tedu.codeafterend.ex.ServiceException;
import cn.tedu.codeafterend.mapper.AbsenceTrackerMapper;
import cn.tedu.codeafterend.mapper.BankMapper;
import cn.tedu.codeafterend.mapper.EmployeeDataMapper;
import cn.tedu.codeafterend.mapper.OvertimeMapper;
import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.EmployeeData;
import cn.tedu.codeafterend.pojo.entity.Overtime;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.service.IEmployeeDataService;
import cn.tedu.codeafterend.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements IEmployeeDataService {
    @Autowired
    EmployeeDataMapper employeeDataMapper;

    @Autowired
    AbsenceTrackerMapper absenceTrackerMapper;

    @Autowired
    OvertimeMapper overtimeMapper;

    @Autowired
    BankMapper bankMapper;

    @Override
    public void addNew(EmployeeDataAddNewDTO employeeDataAddNewDTO) {
        String name = employeeDataAddNewDTO.getEmployeeName();
        int result = employeeDataMapper.countName(name);
        log.debug("查到相同的名稱共{}筆", result);

        if (result != 0) {
            String massage = "添加失敗,該員工已存在,請重新輸入";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }

        EmployeeData employeeData = new EmployeeData();
        BeanUtils.copyProperties(employeeDataAddNewDTO, employeeData);

        int insert = employeeDataMapper.insert(employeeData);
        if (insert != 1 ){
            String message = "添加失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }

    }

    @Override
    public void delete(Long id) {
        EmployeeDataStandardVO result = employeeDataMapper.getStandardById(id);
        if (result == null) {
            String message = "刪除失敗，嘗試刪除的數據不存在";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        absenceTrackerMapper.deleteByEmployee(id);
        overtimeMapper.deleteByEmployee(id);
        bankMapper.deleteByEmployee(id);

        log.debug("開始執行【刪除員工數據】業務");
        int rows = employeeDataMapper.delete(id);
        if (rows != 1) {
            String message = "刪除失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public List<EmployeeDataStandardVO> list() {
        log.debug("開始處理【查詢員工列表】業務");

        return employeeDataMapper.list();
    }

    @Override
    public EmployeeDataStandardVO selectById(Long id) {
        log.debug("開始處理【根據員工id查詢資料】業務");

        return employeeDataMapper.getStandardById(id);
    }

    @Override
    public void update(EmployeeDataAddNewDTO employeeDataAddNewDTO) {
        EmployeeData employeeData = new EmployeeData();
        BeanUtils.copyProperties(employeeDataAddNewDTO,employeeData);

        int row = employeeDataMapper.updateById(employeeData);

        if (row < 1) {
            String message = "修改失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public List<DeptStandardVO> selectDept() {
        return employeeDataMapper.selectDept();
    }

    @Override
    public List<EmployeeDataStandardVO> listByQuitdate() {
        return  employeeDataMapper.listByQuitdate();
    }
}
