package cn.czh0123.garbageclassification.query;


import lombok.Getter;
import lombok.Setter;

/**
* 用户查询参数封装对象
*/
@Setter
@Getter
public class UserQuery extends  QueryObject{
    private String openId;
    private String username;
}
