package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2020/2/15 16:01
 * @Created by chenqiao
 */
public class Main {

    public static void main(String[] args) {
        A aProxy = new AProxy();

        Class[] intefaces = new Class[]{A.class};

        A o = (A) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), intefaces, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }

                return method.invoke(aProxy, args);
            }
        });

        o.print();

        System.out.println(o.toString());

    }

}

interface A {
    String a = "aaa";
    String print();
    String toString();
}

class AProxy implements A{

    @Override
    public String print() {
        String result = "A Proxy print";
        System.out.println(result);
        return result;
    }

    @Override
    public String toString() {
        return "AProxy toString";
    }
}
