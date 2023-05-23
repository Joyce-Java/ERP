package cn.tedu.codeafterend.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 部門的實體類
 *
 * @author Joyce
 * @version 0.0.1
 */
@Data
public class Dept implements Serializable {
    /**
     * 編號
     */
    private Long id;
    /**
     * 員工名稱
     */
    private String deptName;

    /**
     * 部門簡介
     */
    private String deptPresentation;
}
