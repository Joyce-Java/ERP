package cn.tedu.codeafterend.mapper;

import cn.tedu.codeafterend.pojo.entity.AbsenceTracker;
import cn.tedu.codeafterend.pojo.entity.Overtime;
import cn.tedu.codeafterend.pojo.vo.AbsenceTrackerVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceTrackerMapper {

    int insert(AbsenceTracker absenceTracker);

    int deleteById(Long id);

    int updateById(AbsenceTracker absenceTracker);

    List<AbsenceTrackerVO> list();

    List<AbsenceTrackerVO>  getAllById(@Param("id")Long id, @Param("month")String month);

    List<AbsenceTrackerVO>  getByMonthSelectList(String month);

    AbsenceTrackerVO getStandardById(Long id);

    List<EmployeeDataStandardVO> selectEmployees();

    int deleteByEmployee(Long id);

    AbsenceTrackerVO   getByAbsenceTrackerCalculate(Long id);


}
