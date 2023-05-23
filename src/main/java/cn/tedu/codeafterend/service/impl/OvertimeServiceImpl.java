package cn.tedu.codeafterend.service.impl;

import cn.tedu.codeafterend.config.MybatisConfiguration;
import cn.tedu.codeafterend.ex.ServiceException;
import cn.tedu.codeafterend.mapper.DeptMapper;
import cn.tedu.codeafterend.mapper.OvertimeMapper;
import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.OvertimeAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.entity.EmployeeData;
import cn.tedu.codeafterend.pojo.entity.Overtime;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import cn.tedu.codeafterend.service.IDeptService;
import cn.tedu.codeafterend.service.IOvertimeService;
import cn.tedu.codeafterend.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OvertimeServiceImpl implements IOvertimeService {
    @Autowired
    OvertimeMapper overtimeMapper;


    @Override
    public void addNew(OvertimeAddNewDTO overtimeAddNewDTO) {

        Overtime overtime = new Overtime();

        BeanUtils.copyProperties(overtimeAddNewDTO, overtime);
        int insert = overtimeMapper.insert(overtime);
        if (insert != 1 ){
            String message = "添加失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

   @Override
    public void deleteById(Long id) {
        OvertimeStandardVO result = overtimeMapper.getStandardById(id);
        if (result == null){
            String message = "刪除失敗，嘗試刪除的數據不存在";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        log.debug("開始執行【刪除加班時數數據】業務");
        int rows = overtimeMapper.delete(id);
        if (rows != 1){
            String message = "刪除失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public void updateById(OvertimeAddNewDTO overtimeAddNewDTO) {
        Overtime overtime = new Overtime();
        BeanUtils.copyProperties(overtimeAddNewDTO,overtime);
        int row = overtimeMapper.updateById(overtime);

        //TODO 驗證
        if (row >= 1) {

        }
    }

    @Override
    public List<OvertimeStandardVO> list() {
        log.debug("開始處理【查詢加班時數列表】業務");
        return overtimeMapper.list();
    }

    @Override
    public List<OvertimeStandardVO> getByMonthSelectList(String month) {
        return overtimeMapper.getByMonthSelectList(month);
    }

    @Override
    public OvertimeStandardVO selectById(Long id) {
        log.debug("開始處理【根據ID查詢加班時數列表】業務");

        return overtimeMapper.getStandardById(id);
    }

    @Override
    public List<OvertimeStandardVO> getAllById(Long id,String month) {
        log.debug("開始處理【根據ID/月份查詢加班時數列表】業務");
        System.out.println("id="+id);
        return overtimeMapper.getAllById(id,month);
    }

    @Override
    public List<EmployeeDataStandardVO> selectEmployees() {
        return overtimeMapper.selectEmployees();
    }


}
