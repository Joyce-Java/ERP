package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.dto.BankAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.BankStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IBankService {
    /**
     * 添加銀行資料
     *
     * @param deptAddNewDTO 封裝添加部門的數據
     */
    void addNew(BankAddNewDTO deptAddNewDTO);

    /**
     * 根據id刪除資料
     *
     * @param id 資料id
     */
    void deleteById(Long id);

    /**
     * 員工帳戶資料列表
     *
     * @return 返回相關資料
     */
    List<BankStandardVO> list();
}
