package cn.tedu.codeafterend.service.impl;

import cn.tedu.codeafterend.ex.ServiceException;
import cn.tedu.codeafterend.mapper.BankMapper;
import cn.tedu.codeafterend.mapper.DeptMapper;
import cn.tedu.codeafterend.pojo.dto.BankAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.Bank;
import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.vo.BankStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.service.IBankService;
import cn.tedu.codeafterend.service.IDeptService;
import cn.tedu.codeafterend.utils.LaborLnsurance;
import cn.tedu.codeafterend.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BankServiceImpl implements IBankService {
    @Autowired
    BankMapper bankMapper;

    @Override
    public void addNew(BankAddNewDTO bankAddNewDTO) {
        Long employeeId = bankAddNewDTO.getEmployeeId();
        int result = bankMapper.countName(employeeId);
        log.debug("查到相同的名稱共{}筆", result);

        if (result != 0) {
            String massage = "添加失敗,該員工已有對應帳戶,請重新輸入";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }


        Bank bank = new Bank();
        BeanUtils.copyProperties(bankAddNewDTO, bank);
        int laborResult = LaborLnsurance.laborInsuranceCalculation(bank.getSalary(), 30);
        int nationalResult = LaborLnsurance.nationalHealthInsurance(bank.getSalary());
        bank.setLaborInsurance(laborResult);
        bank.setNationalHealthInsurance(nationalResult);
        bankMapper.insert(bank);
    }



    @Override
    public void deleteById(Long id) {
        BankStandardVO result = bankMapper.getStandardById(id);
        if (result == null){
            String message = "刪除失敗，嘗試刪除的數據不存在";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        log.debug("開始執行【刪除部門數據】業務");
        int rows = bankMapper.delete(id);
        if (rows != 1){
            String message = "刪除失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public List<BankStandardVO> list() {
        log.debug("開始處理【查詢員工帳戶列表】業務");
        return bankMapper.list();
    }
}
