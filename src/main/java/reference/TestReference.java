package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Classname TestReference
 * @Description TODO
 * @Date 2020/2/23 16:07
 * @Created by chenqiao
 */
public class TestReference {


    static Object o = null;
    static ReferenceQueue mReferenceQueue;
    private WeakReference weakReference;

    public void test(){

        mReferenceQueue = new ReferenceQueue<>();
// 定义一个对象
        o = new Object();
// 定义一个弱引用对象引用 o,并指定引用队列为 mReferenceQueue
        weakReference = new WeakReference<Object>(o, mReferenceQueue);
// 去掉强引用
        o = null;
// 触发应用进行垃圾回收
        Runtime.getRuntime().gc();
// hack: 延时100ms,等待gc完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reference ref = null;
// 遍历 mReferenceQueue，取出所有弱引用
        while ((ref = mReferenceQueue.poll()) != null) {
            System.out.println("============ \n ref in queue");
        }
    }
}
