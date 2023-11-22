package cn.czh0123.garbageclassification.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryObject {
    /**
     * 当前分页
     */
    private Integer currentPage = 1;
    /**
     * 分页大小
     */
    private Integer pageSize = 5;

//    private String keyword;
}
