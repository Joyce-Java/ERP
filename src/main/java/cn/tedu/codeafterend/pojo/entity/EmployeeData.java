package cn.tedu.codeafterend.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 員工資料的實體類
 *
 * @author Joyce
 * @version 0.0.1
 */
@Data
public class EmployeeData implements Serializable {
    /**
     * 資料編號
     */
    private Long id;
    /**
     * 員工姓名
     */
    private String employeeName;
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
     * 輸入該筆資料的時間
     */
    private LocalDateTime inputData;
    /**
     * 部門ID
     */
    private Long heaDeptId;
}
