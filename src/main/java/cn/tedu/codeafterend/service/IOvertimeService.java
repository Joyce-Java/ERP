package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.dto.BankAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.OvertimeAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.BankStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import cn.tedu.codeafterend.pojo.vo.OvertimeStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IOvertimeService {
    /**
     *
     * @param overtimeAddNewDTO
     */
    void addNew(OvertimeAddNewDTO overtimeAddNewDTO);

    /**
     * 根據id刪除資料
     * @param id
     */
    void deleteById(Long id);

    void updateById(OvertimeAddNewDTO id);

    /**
     * 加班資料列表
     * @return
     */
    List<OvertimeStandardVO> list();

    List<OvertimeStandardVO>  getByMonthSelectList(String month);

    OvertimeStandardVO selectById(Long id);

    List<OvertimeStandardVO> getAllById(Long id,String month);
    List<EmployeeDataStandardVO> selectEmployees();
}
