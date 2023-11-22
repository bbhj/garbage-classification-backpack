import cn.czh0123.garbageclassification.util.BaiduTokenUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestBaidu {

    @Test
    public void testGetToken() throws IOException {
        System.out.println(BaiduTokenUtil.getToken());
    }

    @Test
    public void test() {
        System.out.print(new String(new byte[]{-26, -106,-80,-27,-71,-76,-27,-65,-85,-28,-71,-112}));

        int i = 5;
        int j = 10;
        System.out.println(i + ~j);
        System.out.println();
        int a=13;
        a=a/5;
        System.out.println(a);
    }
}
