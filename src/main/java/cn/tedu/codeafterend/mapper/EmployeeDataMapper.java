package cn.tedu.codeafterend.mapper;

import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.entity.EmployeeData;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDataMapper {
    /**
     * 插入員工資料
     *
     * @param employeeData 員工資料數據
     * @return 受影響的行數
     */
    int insert(EmployeeData employeeData);

    /**
     * 刪除單筆員工資料
     *
     * @param id 要刪除的員工資料編號
     * @return 返回刪除的行數
     */
    int delete(Long id);

    /**
     * 根據ID修改員工資料
     *
     * @param employeeData 根據用戶傳入內容修改對應資料
     * @return 修改的筆數
     */
    int updateById(EmployeeData employeeData);

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
    EmployeeDataStandardVO getStandardById(Long id);

    /**
     * 查詢用戶名是否重複
     *
     * @param name 傳入需要比對的用戶名
     * @return
     */
    int countName(String name);

    /**
     * 查詢員工資料列表
     *
     * @return 查詢員工資料列表
     */
    List<EmployeeDataStandardVO> list();

    List<EmployeeDataStandardVO> selectAllId();

    List<DeptStandardVO> selectDept();

    List<EmployeeDataStandardVO> listByQuitdate();

}
