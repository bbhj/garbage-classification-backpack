import cn.czh0123.garbageclassification.GCBApplication;
import cn.czh0123.garbageclassification.pojo.domain.GarbageType;
import cn.czh0123.garbageclassification.service.IGarbageTypeService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: czh
 * @description:
 * @date: 2023/4/24 0:32
 */
@SpringBootTest(classes = GCBApplication.class)
@RunWith(SpringRunner.class)
public class TestImportGarbage {

    @Autowired
    private IGarbageTypeService garbageTypeService;

    @Test
    public void test() {
        String filePath = "E:\\Java_Code\\garbage-classification-backstage\\src\\main\\resources\\garbage.json";
        File file = new File(filePath);
        try {
            String jsonStr = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            System.out.println(jsonStr);
            List<GarbageType> garbageTypes = JSON.parseArray(jsonStr, GarbageType.class);
            List<GarbageType> list = new ArrayList<>();
            for (GarbageType garbageType : garbageTypes) {
                if (!garbageTypeService.hasGarbageByName(garbageType.getName())) {
                    garbageType.setDetail("我不是针对谁，我的意思是，在座的各位都是垃圾");
                    list.add(garbageType);
                }
            }
            garbageTypeService.saveBatch(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
