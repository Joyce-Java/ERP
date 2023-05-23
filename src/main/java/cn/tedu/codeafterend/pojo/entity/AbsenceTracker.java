package cn.tedu.codeafterend.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AbsenceTracker implements Serializable {

    /**
     * 編號
     */
    private Long id;
    /**
     * 事假
     */
    private Double personalLeave;
    /**
     * 病假
     */
    private Double sickLeave;
    /**
     * 生理假
     */
    private Double menstrualLeave;
    /**
     * 產假
     */
    private Double maternityLeave;
    /**
     * 陪產假
     */
    private Double paternityLeave;
    /**
     * 婚假
     */
    private Double marriageLeave;
    /**
     * 喪假
     */
    private Double bereavementLeave;
    /**
     * 侍產假
     */
    private Double familyCareLeave;

    /**
     * 父親陪產假
     */
    private Double paternityLeaveForChildcare;
    /**
     * 住院看護假
     */
    private Double hospitalCareLeave;

    /**
     * 開始請假時間
     */
    private String startTime;

    /**
     * 結束請假時間
     */
    private String endTime;
    /**
     * 員工編號
     */
    private Long employeeId;


}
