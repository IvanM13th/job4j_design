package ru.job4j.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class References {
    public static void main(String[] args) throws InterruptedException {

        /**       strong references, не может быть собрана GC
         *существует, пока не действует метод или не занулена ссылка
         *
         */
        MyObject o = new MyObject();

        SoftReference<MyObject> softReference = new SoftReference<>(o);

        /**        //мы потеряли оригинальную ссылку, но можем вытащить его через weakReference
         //объект о хранится в объекте weakReference
         //удалится при первом GC
         //удобно для кэша
         *
         */

        WeakReference<MyObject> weakReference = new WeakReference<MyObject>(o);

        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(o, referenceQueue);
        o = null;
        System.out.println(softReference.get());
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
        System.gc();
        System.out.println("-------------------");
        System.out.println(softReference.get());
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 125000; i++) {
            list.add(i);
        }
        System.gc();
        System.out.println("-------------------");
        System.out.println(softReference.get());
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());

    }

    static class MyObject {

        @Override
        protected void finalize() throws Throwable {
            System.out.println("object was finalized");
        }
    }
}
