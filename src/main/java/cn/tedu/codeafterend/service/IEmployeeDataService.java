package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IEmployeeDataService {

    /**
     * 插入員工資料
     *
     * @param employeeDataAddNewDTO 員工資料數據
     */
    void addNew(EmployeeDataAddNewDTO employeeDataAddNewDTO);

    /**
     * 刪除單筆員工資料
     *
     * @param id 要刪除的員工資料編號
     */
    void delete(Long id);

    /**
     * 根據ID修改員工資料
     *
     * @param employeeDataAddNewDTO 根據用戶傳入內容修改對應資料
     */
    void update(EmployeeDataAddNewDTO employeeDataAddNewDTO);

    /**
     * 查詢部門列表
     * @return 返回部門列表
     */
    List<DeptStandardVO> selectDept();

    /**
     * 查詢員工資料列表
     *
     * @return 查詢員工資料列表
     */
    List<EmployeeDataStandardVO> list();

    /**
     * 根據id標準查詢
     *
     * @param id 查詢的id
     * @return 返回查詢數據
     */
    EmployeeDataStandardVO selectById(Long id);

    /**
     * 根據離職員工查出列表
     *
     * @return 返回離職員工列表
     */
    List<EmployeeDataStandardVO> listByQuitdate();
}
