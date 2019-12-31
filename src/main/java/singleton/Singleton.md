### 单例模式

#### 饿汉式

> 写法简单，类装载时即完成了对象实例化， 避免了线程同步问题，但没有实现懒加载，有可能造成内存浪费

```

public class Singleton {

    private static Singleton INSTANCE;

    private Singleton(){}

    public static Singleton getInstance(){
        return  INSTANCE;
    }
}
    
```


```

public class Singleton {

    private static Singleton singleton;
    
    static{
        singleton = new Singleton();
    }

    private Singleton(){}

    public static Singleton getInstance(){
        return  singleton;
    }
}
    
```


#### 懒汉式

> 调用时才实例化对象，实现了懒加载，但只能在单线程场景下使用，存在线程安全问题 （singleton == null 判断语句）

```

public class Singleton {

    private static Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){

        if (singleton == null){
            singleton = new Singleton();
        }

        return  singleton;
    }
}
    
```

> 升级版，加入 `synchronized` 关键字 ，避免了线程安全问题，缺点是效率低，每个线程调用的时候都要进行同步，多个线程不能同时访问，大多时候是没有必要的

```

public class Singleton {

    private static Singleton singleton;

    private Singleton(){}

    public static synchronized Singleton getInstance(){

        if (singleton == null){
            singleton = new Singleton();
        }

        return  singleton;
    }
}
    
```

#### 双重检查式

> 两次检查能保证线程安全，延迟加载，也能避免效率问题.


> `volatile` 避免了重排序的发生

>  ` singleton = new Singleton(); ` 在JVM中可能被拆成三步操作，

1. 给 singleton 分配内存空间
2. 调用 Singleton 构造函数初始化 singleton
3. 将 singleton 对象指向分配的内存空间（执行完这部操作， singleton 就不是 null 了）
可能存在指令重排序的优化，打乱 2，3 步操作的顺序， 可能是 1-2-3 ，也可能是 1-3-2 。
如果 1-3-2 ，可能存在的情况：一个线程 执行完 3 操作，另一个线程运行至第一次校验处，由于singleton已经不是 null, 直接返回，但 singleton 初始化操作还未完成，这时候使用单例对象可能会发生异常


```

public class Singleton {

    private static volatile Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){

        if (singleton == null){
            synchronized (Singleton.class) {
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }

        return  singleton;
    }
}
    
```

#### 静态内部类式

> 类加载时，其内部类不会同时加载，内部类加载当且仅当某个静态成员（静态域 构造器 静态方法）被调用时发生

```

public class Singleton {

    private Singleton(){}

    private static final class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
    
```

#### 枚举式

> 写法简单，避免了线程同步问题，还能防止反序列化 和 反射创建新的对象 来破坏单例对象

```

public enum Singleton {

    INSTANCE;

    public void whateverMethod(){
        
    }
}


```