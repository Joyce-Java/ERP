package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.dto.AbsenceTrackerAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.AbsenceTrackerVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IAbsenceTrackerService {
    /**
     *
     * @param absenceTrackerAddNewDTO
     */
    void addNew(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO);

    void deleteById(Long id);

    void updateById(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO);

    List<AbsenceTrackerVO> list();

    List<AbsenceTrackerVO> getAllById( Long id, String month);

    List<AbsenceTrackerVO>  getByMonthSelectList(String month);

    AbsenceTrackerVO selectById(Long id);

    List<EmployeeDataStandardVO> selectEmployees();


}
