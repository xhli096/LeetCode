package extend;

/**
 * @author: lixinghao
 * @date: 2020/7/2 9:19 下午
 * @Description:
 */
public class ParentClass {
    private int val;
    private String value;

    public ParentClass(int val, String value) {
        this.val = val;
        this.value = value;
    }

    public ParentClass() {
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
