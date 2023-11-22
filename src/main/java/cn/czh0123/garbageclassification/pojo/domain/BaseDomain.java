package cn.czh0123.garbageclassification.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author czh
 */
@Data
public class BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
}
