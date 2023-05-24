package cn.tedu.codeafterend.mapper;

import cn.tedu.codeafterend.pojo.entity.Dept;
import cn.tedu.codeafterend.pojo.vo.CodeNumberStandardVO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeNumberMapper {

    /**
     * 根據id標準查詢
     *
     * @param id 查詢的id
     * @return 返回查詢數據
     */
    CodeNumberStandardVO getStandardById(Long id);


    /**
     * 查詢銀行代號資料列表
     *
     * @return 返回查詢的列表
     */
    List<CodeNumberStandardVO> list();

}
