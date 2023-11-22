package cn.czh0123.garbageclassification.query;


import lombok.Getter;
import lombok.Setter;

/**
* 垃圾分类查询参数封装对象
*/
@Setter
@Getter
public class GarbageTypeQuery extends  QueryObject{
    private String name;
    private Long type;
}
