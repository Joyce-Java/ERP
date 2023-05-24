package cn.tedu.codeafterend.mapper;

import cn.tedu.codeafterend.pojo.entity.Bank;
import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.vo.BankStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankMapper {
    /**
     * 插入銀行資料
     *
     * @param bank 要插入的銀行參數
     * @return 受影響的行數
     */
    int insert(Bank bank);

    /**
     * 刪除單筆銀行資料
     *
     * @param id 要刪除的銀行資料編號
     * @return 返回刪除的行數
     */
    int delete(Long id);

    /**
     * 根據ID修改銀行資料
     *
     * @param bank 根據用戶傳入內容修改對應資料
     * @return 修改的筆數
     */
    int updateById(Bank bank);

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
    BankStandardVO getStandardById(Long id);

    /**
     * 查詢銀行名是否重複
     *
     * @param employeeId 傳入需要比對的名稱
     * @return 返回受查詢到的筆數
     */
    int countName(Long employeeId);

    /**
     * 關聯查詢現有員工名稱
     *
     * @return 返回查詢的列表
     */
    List<EmployeeDataStandardVO> getEmployeeName();

    /**
     * 查詢銀行資料列表
     *
     * @return 返回查詢的列表
     */
    List<BankStandardVO> list();

    /**
     * 根據員工id刪除對應資料
     *
     * @param id 員工id
     * @return 返回刪除的筆數
     */
    int deleteByEmployee(Long id);
}
