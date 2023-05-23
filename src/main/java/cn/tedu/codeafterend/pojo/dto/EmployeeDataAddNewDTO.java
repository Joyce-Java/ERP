package cn.tedu.codeafterend.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class EmployeeDataAddNewDTO implements Serializable {

    private Long id;
    /**
     * 員工姓名
     */
    private String employeeName;

    /**
     * 員工年齡
     */
    private String deptName;
    /**
     * 員工性別
     */
    private String gender;
    /**
     * 員工地址
     */
    private String address;
    /**
     * 員工薪資
     */
    private BigDecimal salary;
    /**
     * 員工身分證
     */
    private String idNumber;
    /**
     * 員工生日
     */
    private String birthday;
    /**
     * 入職時間
     */
    private String hiredate;
    /**
     * 離職時間
     */
    private String quitdate;
    /**
     * 員工電話
     */
    private String phoneNumber;
    /**
     * 員工學歷
     */
    private String educationBackground;
    /**
     * 銀行帳號
     */
    private String bankNumber;
    /**
     * 部門ID
     */
    private Long heaDeptId;
}
