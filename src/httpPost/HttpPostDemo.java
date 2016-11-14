package httpPost;

/**
 * Created by konglie on 4/1/16.
 */
public class HttpPostDemo {
    public HttpPostDemo() {
        MCrypt mc = new MCrypt();
        String sample = "{\"nama\":\"ekawan\",\"telp\":\"07179100946\",\"indihome\":\"111721110928\",\"email\":\"ekawankelvin@gmail.com\",\"handphone\":\"082299797577\"}";

        String url = "http://kurungkurawal.com/indihome/knife.php";
        try {
            String res = HttpPost.doPost(url, "r=" + mc.encrypt(sample) + "&p=" + mc.encrypt(Math.random() + ""));

            System.out.println(res);
            System.out.println(mc.decrypt(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
