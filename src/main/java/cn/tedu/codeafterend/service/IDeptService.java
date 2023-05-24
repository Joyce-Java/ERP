package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.dto.DeptAddNewDTO;
import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IDeptService {

    /**
     * 添加部門
     *
     * @param deptAddNewDTO 封裝添加部門的數據
     */
    void addNew(DeptAddNewDTO deptAddNewDTO);

    /**
     * 根據id刪除部門
     *
     * @param id 資料id
     */
    void deleteById(Long id);


    /**
     * 查詢部門資料列表
     *
     * @return 返回查詢的列表
     */
    List<DeptStandardVO> list();
}
