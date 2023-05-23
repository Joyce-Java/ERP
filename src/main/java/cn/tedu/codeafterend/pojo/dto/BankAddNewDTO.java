package cn.tedu.codeafterend.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BankAddNewDTO implements Serializable {
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
     * 對應的員工編號
     */
    private Long employeeId;
}
