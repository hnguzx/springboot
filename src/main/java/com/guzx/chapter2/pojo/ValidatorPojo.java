package com.guzx.chapter2.pojo;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class ValidatorPojo {

    @NotNull(message = "id不能为空")
    private Long id;

    @Future(message = "需要一个将来的时间") // 只能是将来的时间
//    @Past(message = "需要一个过去的时间") // 只能是过去的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 日期格式化转换
    @NotNull(message = "时间不能为空")
    private Date date;

    @NotNull
    @DecimalMax(value = "99999") // 设置最大值
    @DecimalMin(value = "0.01") // 设置最小值
    private Double doubleValue;

    @NotNull
    @Min(value = 1, message = "最小值为1")
    @Max(value = 100, message = "最大值为100")
    private Integer integer;

    @Range(min = 1, max = 888, message = "范围为1-888") // 限定范围
    private Long range;

    @Email(message = "邮箱格式不对")
    private String email;

    @Size(min = 10, max = 30, message = "字符串长度要求在10到30之间")
    private String size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
}
