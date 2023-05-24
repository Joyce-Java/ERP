package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.CodeNumberStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface ICodeNumberService {

    /**
     * 查詢銀行代號資料列表
     *
     * @return 返回查詢的列表
     */
    List<CodeNumberStandardVO> list();
}
