package cn.czh0123.garbageclassification.pojo.vo;

import cn.czh0123.garbageclassification.pojo.domain.GarbageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarbageTypeVo extends GarbageType {
    private List<GarbageType> garbageTypes = new ArrayList<>();
}
