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
     * 新增出缺勤資料
     *
     * @param absenceTrackerAddNewDTO 出缺勤資料
     */
    void addNew(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO);

    /**
     * 根據ID刪除資料
     *
     * @param id 資料id
     */
    void deleteById(Long id);

    /**
     * 根據ID修改資料
     *
     * @param absenceTrackerAddNewDTO 要修改的資料
     */
    void updateById(AbsenceTrackerAddNewDTO absenceTrackerAddNewDTO);

    /**
     * 查詢所有出缺勤內容
     *
     * @return 返回出缺勤資料
     */
    List<AbsenceTrackerVO> list();

    /**
     * 根據ID查詢資料並判斷是否需要根據月份查詢
     *
     * @param id 員工id
     * @param month 月份
     * @return 返回查詢資料
     */
    List<AbsenceTrackerVO> getAllById(Long id, String month);

    /**
     * 根據月份查詢出缺勤資料
     *
     * @param month 查詢月份
     * @return 返回根據月份查詢的資料
     */
    List<AbsenceTrackerVO> getByMonthSelectList(String month);

    /**
     * 根據id查詢出缺勤資料
     *
     * @param id 查詢id
     * @return 返回根據id查詢的資料
     */
    AbsenceTrackerVO selectById(Long id);

    /**
     * 查詢員工資料
     *
     * @return 返回所有員工資料
     */
    List<EmployeeDataStandardVO> selectEmployees();


}
