package cn.tedu.codeafterend.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeNumberStandardVO implements Serializable {
    /**
     * 編號
     */
    private Long id;
    /**
     * 銀行代號及對應名稱
     */
    private String codeName;

}
