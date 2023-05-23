package cn.tedu.codeafterend.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 銀行代碼與名稱的實體類
 *
 * @author Joyce
 * @version 0.0.1
 */
@Data
public class CodeNumber implements Serializable {
    /**
     * 編號
     */
    private Long id;
    /**
     * 銀行代號及對應名稱
     */
    private String codeName;


}
