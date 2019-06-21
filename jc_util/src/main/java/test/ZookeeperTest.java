/**
 * Created by jingchun.zhang on 2019/6/20.
 */
package test;

import com.jc.util.BaseZookeeper;
import java.util.List;
import org.apache.zookeeper.KeeperException;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/6/20
 */
public class ZookeeperTest {

    static BaseZookeeper zookeeper;

    static {
        zookeeper = new BaseZookeeper();
        try {
            zookeeper.connectZookeeper("localhost:2181");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ZookeeperTest zookeeperTest = new ZookeeperTest();
        zookeeperTest.t2();

    }

    private void t2() throws Exception {
        String s = zookeeper.createNodeWithTemp("/temp","temp create node");
        System.out.println(s);

        Thread.sleep(20*1000);
//        zookeeper.closeConnection();
        System.out.println("end...");
    }

    private void t1() throws KeeperException, InterruptedException {
        String path = "";
        path = "/";
        List<String> children = zookeeper.getChildren(path);
        for (String s: children){
            System.out.println(s);
        }
    }

}
