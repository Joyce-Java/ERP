package cn.tedu.codeafterend.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BankStandardVO implements Serializable {
    /**
     * 編號
     */
    private Long id;
    /**
     * 銀行代號及對應名稱
     */
    private String codeName;
    /**
     * 薪資
     */
    private Integer salary;
    /**
     * 銀行帳號
     */
    private String bankNumber;
    /**
     * 勞保金額
     */
    private int laborInsurance;
    /**
     * 健保金額
     */
    private int nationalHealthInsurance;
    /**
     * 對應的員工編號
     */
    private Long employeeId;
    /**
     * 姓名
     */
    private String employeeName;
}
