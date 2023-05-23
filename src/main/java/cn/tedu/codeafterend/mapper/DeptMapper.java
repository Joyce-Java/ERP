package cn.tedu.codeafterend.mapper;

import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.entity.EmployeeData;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    /**
     * 插入部門資料
     *
     * @param dept 要插入的部門參數
     * @return 受影響的行數
     */
    int insert(Dept dept);

    /**
     * 刪除單筆部門資料
     *
     * @param id 要刪除的員工資料編號
     * @return 返回刪除的行數
     */
    int delete(Long id);

    /**
     * 根據ID修改部門資料
     *
     * @param dept 根據用戶傳入內容修改對應資料
     * @return 修改的筆數
     */
    int updateById(Dept dept);

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
    DeptStandardVO getStandardById(Long id);

    /**
     * 查詢部門名是否重複
     *
     * @param name 傳入需要比對的名稱
     * @return 返回受查詢到的筆數
     */
    int countName(String name);

    /**
     * 查詢部門資料列表
     *
     * @return 返回查詢的列表
     */
    List<DeptStandardVO> list();

}
