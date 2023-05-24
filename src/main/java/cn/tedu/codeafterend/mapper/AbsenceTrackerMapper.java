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

    /**
     * 新增出缺勤資料
     *
     * @param absenceTracker 出缺勤資料
     */
    int insert(AbsenceTracker absenceTracker);

    /**
     * 根據ID刪除資料
     *
     * @param id 資料id
     */
    int deleteById(Long id);

    /**
     * 根據ID修改資料
     *
     * @param absenceTracker 要修改的資料
     */
    int updateById(AbsenceTracker absenceTracker);

    /**
     * 查詢所有出缺勤內容
     *
     * @return 返回所有出缺勤資料
     */
    List<AbsenceTrackerVO> list();

    /**
     * 根據ID查詢資料並判斷是否需要根據月份查詢
     *
     * @param id    員工id
     * @param month 月份
     * @return 返回查詢資料
     */
    List<AbsenceTrackerVO> getAllById(@Param("id") Long id, @Param("month") String month);

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
    AbsenceTrackerVO getStandardById(Long id);

    /**
     * 查詢員工資料
     *
     * @return 返回所有員工資料
     */
    List<EmployeeDataStandardVO> selectEmployees();

    /**
     * 根據員工id刪除出缺勤資料
     *
     * @return 返回刪除筆數
     */
    int deleteByEmployee(Long id);

    /**
     * 根據員工id統計每個員工前一個月的請假時數
     *
     * @param id 員工id
     * @return 返回計算的資料
     */
    AbsenceTrackerVO getByAbsenceTrackerCalculate(Long id);


}
