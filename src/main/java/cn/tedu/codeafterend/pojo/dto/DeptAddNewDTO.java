package cn.tedu.codeafterend.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DeptAddNewDTO implements Serializable {
    /**
     * 員工名稱
     */
    private String deptName;

    /**
     * 部門簡介
     */
    private String deptPresentation;
}
