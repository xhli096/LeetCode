import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: lixinghao
 * @date: 2020/5/25 2:51 下午
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        /*List<Integer> orderCountList = Lists.newArrayList(1,3,6,10);
        List<Integer> awardCountList = Lists.newArrayList(0,30,40,80);
        Integer[] result = new Integer[10 + 1];
        result[0] = 0;
        for (int i = 0; i < orderCountList.size(); i++) {
            result[orderCountList.get(i)] = awardCountList.get(i);
        }

        for (int i = 1; i < result.length; i++) {
            if (result[i] == null) {
                result[i] = result[i - 1];
            }
        }
        Thread thread = new Thread();
        System.out.println(JSON.toJSONString(result));*/

        /*List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String s : list) {
            if ("1".equals(s)) {
                list.remove(s);
            }
        }
        System.out.println(JSON.toJSONString(list));*/

        /*SubClass subClass = new SubClass("1", 2, new Date());
        ParentClass parentClass = subClass;
        System.out.println(JSON.toJSONString(subClass));
        System.out.println(JSON.toJSONString(parentClass));

        ParentClass parentClass1 = new ParentClass(1, "112233");
        SubClass subClass1 = (SubClass) parentClass1;
        System.out.println(JSON.toJSONString(parentClass1));
        System.out.println(JSON.toJSONString(subClass1));*/
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
