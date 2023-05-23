package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.dto.EmployeeDataAddNewDTO;
import cn.tedu.codeafterend.pojo.vo.DeptStandardVO;
import cn.tedu.codeafterend.pojo.vo.EmployeeDataStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IEmployeeDataService {

    void addNew(EmployeeDataAddNewDTO employeeDataAddNewDTO);

    void delete(Long id);

    List<EmployeeDataStandardVO> list();

    EmployeeDataStandardVO selectById(Long id);

    void update(EmployeeDataAddNewDTO employeeDataAddNewDTO);

    List<DeptStandardVO> selectDept();

    List<EmployeeDataStandardVO> listByQuitdate();
}
