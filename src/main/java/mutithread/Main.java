package mutithread;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2020/3/4 13:39
 * @Created by chenqiao
 */
public class Main {


    public static void main(String[] args) {
        MyThread thread=new MyThread();
        thread.start();
        thread.interrupt();
        System.out.println("调用thread.isInterrupted()："+thread.isInterrupted());//true isInterrupted 只是判断状态，不会重置
        //测试interrupted（）函数
        // interrupted 和 currentThread 都是 静态方法 执行的对象都是当前线程， 这里的 thread.interrupted() 其实就是 判断当前 main线程 是否处于中断状态 跟 thread没有关系
        //正确用法应该是 在 Mythread 中判断 interrupted
        System.out.println("第一次调用thread.interrupted()："+thread.interrupted());//false 因为主线程并没有被操作 interrupt
        System.out.println("第二次调用thread.interrupted()："+thread.interrupted());//false
        System.out.println(Thread.currentThread());

    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println("i=" + (i + 1));
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //注 抛出中断异常 会重置中断状态， 调用 interrupted 会重置当前线程状态
                    System.out.println("当前"+Thread.currentThread() + "thread是否中断 isInterrupted ："+isInterrupted());
                    System.out.println("当前"+Thread.currentThread() + "thread是否中断 interrupted :"+interrupted());
                    System.out.println("当前"+Thread.currentThread() + "thread是否存活："+isAlive());
                    this.interrupt();
                    System.out.println("当前"+Thread.currentThread() + "thread是否中断 isInterrupted:"+this.isInterrupted());
                    System.out.println("当前"+Thread.currentThread() + "thread是否中断 interrupted 1:"+interrupted());
                    System.out.println("当前"+Thread.currentThread() + "thread是否中断 interrupted 2:"+interrupted());

                    /**
                     * java.lang.InterruptedException: sleep interrupted
                     * 	at java.lang.Thread.sleep(Native Method)
                     * 	at mutithread.Main$MyThread.run(Main.java:32)
                     * 当前Thread[Thread-0,5,main]thread是否中断 isInterrupted ：false
                     * 当前Thread[Thread-0,5,main]thread是否中断 interrupted :false
                     * 当前Thread[Thread-0,5,main]thread是否存活：true
                     * 当前Thread[Thread-0,5,main]thread是否中断 isInterrupted:true
                     * 当前Thread[Thread-0,5,main]thread是否中断 interrupted 1:true
                     * 当前Thread[Thread-0,5,main]thread是否中断 interrupted 2:false
                     */
                    break;
                }
            }


        }
    }

}
