import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import singleton.EnumSingleton;
import reference.TestReference;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        System.out.println("hello world");


        System.out.println(EnumSingleton.INSTANCE);
        System.out.println(EnumSingleton.getInstance());

        TestReference testReference = new TestReference();
        testReference.test();


        List<Fruit> fruits = new ArrayList<>();

        Fruit fruit = new Banana();
        Apple apple = new Apple();
        fruits.add(fruit);
        fruits.add(apple);

//        List<Fruit> fruitss = new ArrayList<Apple>();

        List<? extends Fruit> fruitList = new ArrayList<>();
//        fruitList.add(fruit);
//        fruitList.add(apple);
        Fruit fruit1 = fruitList.get(0);


        List<? super Fruit> fruitList1 = new ArrayList<Object>();
        fruitList1.add(new Apple());
        Object object = fruitList1.get(0);

    }



    static class Fruit{

        void price(){

        }
    }

    static class Apple extends Fruit{

        @Override
        public void price() {

        }
    }

    static class Banana extends Fruit{

        @Override
        public void price() {

        }
    }
}
