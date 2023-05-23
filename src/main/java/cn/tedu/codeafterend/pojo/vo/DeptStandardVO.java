package cn.tedu.codeafterend.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DeptStandardVO implements Serializable {
    /**
     * 編號
     */
    private Long id;
    /**
     * 部門名稱
     */
    private String deptName;

    /**
     * 部門簡介
     */
    private String deptPresentation;

}
