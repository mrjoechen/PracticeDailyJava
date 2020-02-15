package proxy;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

//                testProxyGenetate(intefaces);

                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }

                return method.invoke(aProxy, args);
            }


            @Override
            public String toString() {
                return "hahaha";
            }
        });

        o.print();

        System.out.println(o);

    }

    public static void testProxyGenetate(Class[] classes) {

        Class<?> aClass = null;
        byte[] newProxyClass = null;
        try {
            aClass = Class.forName("sun.misc.ProxyGenerator");
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++){
                declaredMethods[i].setAccessible(true);
                System.out.println(declaredMethods[i].getName());
            }
            Method generateProxyClassMethod = aClass.getDeclaredMethod("generateProxyClass", String.class, Class[].class);
            generateProxyClassMethod.setAccessible(true);
            newProxyClass = (byte[]) generateProxyClassMethod.invoke(null, "$Proxy0", classes);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        byte[] newProxyClass = ProxyGenerator.generateProxyClass("$Proxy0", classes);
//        System.out.println(newProxyClass);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("./$Proxy0.class"));
            try {
                fileOutputStream.write(newProxyClass);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
