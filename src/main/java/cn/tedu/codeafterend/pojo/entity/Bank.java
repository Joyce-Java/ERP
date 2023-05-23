package cn.tedu.codeafterend.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 銀行的實體類
 *
 * @author Joyce
 * @version 0.0.1
 */
@Data
public class Bank implements Serializable {
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
