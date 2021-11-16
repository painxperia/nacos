package com.zpain.service.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zpain
 * @since 2021-11-16
 */
@Getter
@Setter
@TableName("test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;


}
