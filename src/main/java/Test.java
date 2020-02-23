import singleton.EnumSingleton;
import singleton.TestReference;

public class Test {

    public static void main(String[] args) {
        System.out.println("hello world");


        System.out.println(EnumSingleton.INSTANCE);
        System.out.println(EnumSingleton.getInstance());

        TestReference testReference = new TestReference();
        testReference.test();
    }
}
