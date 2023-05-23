package cn.tedu.codeafterend.mapper;

import cn.tedu.codeafterend.pojo.dto.OvertimeAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.entity.Overtime;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeMapper {
    /**
     * 插入加班資料
     *
     * @param overtime 要插入的加班時數參數
     * @return 受影響的行數
     */
    int insert(Overtime overtime);

    /**
     * 刪除單筆加班資料
     *
     * @param id 要刪除的加班時數編號
     * @return 返回刪除的行數
     */
    int delete(Long id);

    /**
     * 根據ID修改加班資料
     *
     * @param overtime 根據用戶傳入內容修改對應資料
     * @return 修改的筆數
     */
    int updateById(Overtime overtime);

    /**
     * 查詢數據庫資料的筆數
     *
     * @return 資料庫總筆數
     */
    int count();

    /**
     * 根據id標準查詢
     *
     * @param id 查詢的id
     * @return 返回查詢數據
     */
    OvertimeStandardVO getStandardById(Long id);

    List<OvertimeStandardVO>  getByMonthSelectList(String month);

    List<OvertimeStandardVO>  getAllById(@Param("id") Long id, @Param("month") String month);

    List<OvertimeStandardVO>   getByOvertimeCalculate(Long id);

    /**
     * 查詢加班資料列表
     *
     * @return 返回查詢的列表
     */
    List<OvertimeStandardVO> list();

    List<EmployeeDataStandardVO>  selectEmployees();

    int deleteByEmployee(Long id);

}
