package cn.tedu.codeafterend.service.impl;

import cn.tedu.codeafterend.ex.ServiceException;
import cn.tedu.codeafterend.mapper.AbsenceTrackerMapper;
import cn.tedu.codeafterend.pojo.dto.AbsenceTrackerAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.AbsenceTracker;
import cn.tedu.codeafterend.pojo.vo.AbsenceTrackerVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import cn.tedu.codeafterend.service.IAbsenceTrackerService;
import cn.tedu.codeafterend.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AbsenceTrackerServiceImpl implements IAbsenceTrackerService {
    @Autowired
    AbsenceTrackerMapper absenceTrackerMapper;


    @Override
    public void addNew(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO) {

        AbsenceTracker absenceTracker = new AbsenceTracker();

        BeanUtils.copyProperties(absenceTrackerAddNewDTO, absenceTracker);
        int insert = absenceTrackerMapper.insert(absenceTracker);
        if (insert != 1 ){
            String message = "添加失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    public void deleteById(Long id) {
        int delete = absenceTrackerMapper.deleteById(id);
        if (delete != 1 ){
            String message = "刪除失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public void updateById(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO) {
        AbsenceTracker absenceTracker = new AbsenceTracker();

        BeanUtils.copyProperties(absenceTrackerAddNewDTO, absenceTracker);
        int row = absenceTrackerMapper.updateById(absenceTracker);

        if (row != 1 ){
            String message = "修改失敗，伺服器繁忙，請稍後再嘗試。";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE,message);
        }
    }

    @Override
    public List<AbsenceTrackerVO> list() {
        return absenceTrackerMapper.list();
    }

    @Override
    public List<AbsenceTrackerVO> getAllById(Long id, String month) {
        List<AbsenceTrackerVO> allByIds = absenceTrackerMapper.getAllById(id, month);

        return allByIds;
    }

    @Override
    public List<AbsenceTrackerVO> getByMonthSelectList(String month) {
        return absenceTrackerMapper.getByMonthSelectList(month);
    }

    @Override
    public AbsenceTrackerVO selectById(Long id) {
        return absenceTrackerMapper.getStandardById(id);
    }

    @Override
    public List<EmployeeDataStandardVO> selectEmployees() {
        return absenceTrackerMapper.selectEmployees();
    }


}
