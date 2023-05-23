package cn.tedu.codeafterend.pojo.entity;

import lombok.Data;

@Data
public class SalaryManagement {
    /**
     * id
     */
    private Long id;
    /**
     * 加班費
     */
    private int overtimePay;
    /**
     * 請假扣款金額
     */
    private int leaveDeductionAmount;
    /**
     * 薪資
     */
    private int payroll;
    /**
     * 勞保金額
     */
    private int laborInsurance;
    /**
     * 健保金額
     */
    private int nationalHealthInsurance;
    /**
     * 實際發薪月份
     */
    private String actualMonth;
    /**
     * 員工id
     */
    private Long employeeId;
}
