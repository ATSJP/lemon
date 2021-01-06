package com.lemon.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author atsjp 2020/1/18
 */
public class Test01 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExecutorService executorService = new ThreadPoolExecutor(20, 20, 0L, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>());

    private volatile Integer num = 0;

    @Test
    public void test1() {
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread() + ",start:" + num);
                for (int j = 0; j < 2; j++) {
                    num++;
                }
                System.out.println(Thread.currentThread() + ",end:" + num);
            });
        }
        System.out.println(Thread.currentThread() + ",all:" + num);
    }

    private static final ThreadLocal<Integer> threadLocalNum = new ThreadLocal<>();

    @Test
    public void test2() {
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                threadLocalNum.set(0);
                System.out.println(Thread.currentThread() + ",start:" + threadLocalNum.get());
                for (int j = 0; j < 2; j++) {
                    Integer temp = threadLocalNum.get();
                    threadLocalNum.set(++temp);
                }
                System.out.println(Thread.currentThread() + ",end:" + threadLocalNum.get());
            });
        }
        System.out.println(Thread.currentThread() + ",all:" + threadLocalNum.get());
    }

    private ExecutorService singleExecutorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>());

    @Test
    public void test3() {
        threadLocalNum.set(1);
        for (int i = 0; i < 1000; i++) {
            singleExecutorService.execute(() -> {
                System.out.println(Thread.currentThread() + ",get:" + threadLocalNum.get());
                // localVariable.remove();
            });
        }
    }

    static class LocalVariable {
        private Long[] a = new Long[4 * 1024 * 1024];
    }

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES,
        new LinkedBlockingQueue<>());

    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    /**
     * jvm参数 -Xms100m -Xmx100m
     *
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        for (int i = 0; i < 5000; ++i) {
            poolExecutor.execute(() -> {
                localVariable.set(new LocalVariable());
                System.out.println("use local varaible:" + localVariable.get());
                //        localVariable.remove();
            });
            Thread.sleep(1000);
        }
        System.out.println("pool execute over");
    }

    @Test
    public void test5() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        String value = Optional.ofNullable(map).map(item -> item.get("key1")).orElse("");
        System.out.println(value);
    }

    @Test
    public void test6() {
        List<String> list = new LinkedList<>();
        list.add("key1");
        list.add("key2");
        list.add("key3");
        List<String> result = null;
        try {
            result = list.stream().filter(item -> {
                return true;
            }).collect(Collectors.toList());
        } catch (Exception e) {
           System.out.println(" e.printStackTrace()" + e.getMessage() + " " + (e instanceof NullPointerException));
           e.printStackTrace();
        }
        result.forEach(System.out::println);
    }
}















