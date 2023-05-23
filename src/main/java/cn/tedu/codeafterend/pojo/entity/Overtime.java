package cn.tedu.codeafterend.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 加班時數實體類
 */
@Data
public class Overtime implements Serializable {
    /**
     * 編號
     */
    private Long id;
    /**
     * 平日加班(時數)
     */
    private Double weekdayOvertime;
    /**
     * 休假日加班(時數)
     */
    private Double holidayOvertime;
    /**
     * 例假加班(時數)
     */
    private Double legalHolidayOvertime;
    /**
     * 國定假日加班(時數)
     */
    private Double statutoryOvertime;
    /**
     * 補休時數
     */
    private Double compTime;
    /**
     * 員工編號
     */
    private Long EmployeeId;
    /**
     * 數據創建時間
     */
    private LocalDateTime gmtCreate;
    /**
     * 開始加班時間
     */
    private String startTime;

    /**
     * 結束加班時間
     */
    private String endTime;

}
