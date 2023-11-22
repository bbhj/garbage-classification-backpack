package cn.czh0123.garbageclassification.util;


import java.io.File;
import java.net.URLEncoder;

/**
* 通用物体和场景识别
*/
public class AdvancedGeneral {

    public static String advancedGeneral(File file) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
        try {
            // 本地文件路径
//            String filePath = "E:\\Java_Code\\garbage-classification-backstage\\src\\main\\resources\\static\\image\\frAwHW8C3S6Adadd2604d4809538bdc28d3ce49649b7.jpg";
            byte[] imgData = FileUtil.readFileByBytes(file);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = BaiduTokenUtil.getToken();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
//            {"result_num":5,
//             "result":[
//                  {"keyword":"打火机","score":0.013874,"root":"商品-日用品"},
//                  {"keyword":"化妆品","score":0.008363,"root":"美妆-香水彩妆"},
//                  {"keyword":"护肤品","score":0.005569,"root":"商品-护肤品"},
//                  {"keyword":"瓶子","score":0.002784,"root":"商品-容器"},
//                  {"keyword":"包装袋/盒","score":0.0000070,"root":"非自然图像-图像素材"}
//              ],
//              "log_id":1613443010729390698}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试
     * @param args
     */
/*    public static void main(String[] args) {
        AdvancedGeneral.advancedGeneral("E:\\Java_Code\\garbage-classification-backstage\\src\\main\\resources\\static\\image\\1.jpg");
    }*/
}
