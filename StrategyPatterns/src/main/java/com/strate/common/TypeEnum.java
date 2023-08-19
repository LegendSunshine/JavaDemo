package com.strate.common;

/**
 * 类名称: TypeEnum
 * 类描述: 枚举类型
 *
 * @author legend
 * @since 2023/08/19
 */
public enum TypeEnum {

    /**其他类型*/
    OTHER_TYPE(1000,"其他类型"),
    /**手机类型*/
    PHONE_TYPE(1002,"手机类型")
    ;

    private final Integer typeCode;

    private final String type;

    TypeEnum(Integer typeCode, String type) {
        this.typeCode = typeCode;
        this.type = type;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public String getType() {
        return type;
    }
}
