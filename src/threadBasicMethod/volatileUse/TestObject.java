package threadBasicMethod.volatileUse;

public class TestObject {

    private volatile int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
