package cn.tedu.codeafterend.service;

import cn.tedu.codeafterend.pojo.vo.SalaryManagementStandardVO;

import java.util.List;

public interface ISalaryManagementService {

    void calculateSalary();

    List<SalaryManagementStandardVO> list();

    List<SalaryManagementStandardVO>  getByMonthSelectList(String month);
}
