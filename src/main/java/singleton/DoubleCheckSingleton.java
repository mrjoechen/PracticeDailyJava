package singleton;

/**
 * @Classname DoubleCheckSingleton
 * @Description 双重校验单例 支持并发  缺点：不支持反射 和 序列化
 * @Date 2019-12-31 14:00
 * @Created by chenqiao
 */
public class DoubleCheckSingleton {

    private volatile static DoubleCheckSingleton doubleCheckSingleton;

    private DoubleCheckSingleton() {

    }


    public static DoubleCheckSingleton getInstance() {

        if (doubleCheckSingleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (doubleCheckSingleton == null) {
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }

        return doubleCheckSingleton;
    }

}
