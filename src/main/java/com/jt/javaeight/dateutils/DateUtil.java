package com.jt.javaeight.dateutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch概念
 *
 * CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）。
 *
 * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成一些任务，然后在CountDownLatch上等待的线程就可以恢复执行接下来的任务。
 *
 * CountDownLatch的用法
 *
 * CountDownLatch典型用法：1、某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为new CountDownLatch(n)，每当一个任务线程执行完毕，就将计数器减1 countdownLatch.countDown()，当计数器的值变为0时，在CountDownLatch上await()的线程就会被唤醒。一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
 *
 * CountDownLatch典型用法：2、实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，将其计算器初始化为1，多个线程在开始执行任务前首先countdownlatch.await()，当主线程调用countDown()时，计数器变为0，多个线程同时被唤醒。
 *
 * CountDownLatch的不足
 *
 * CountDownLatch是一次性的，计算器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用。
 *
 *
 *
 * CountDownLatch（倒计时计算器）使用说明
 *
 * 方法说明
 *
 * public void countDown()
 *
 * 　　递减锁存器的计数，如果计数到达零，则释放所有等待的线程。如果当前计数大于零，则将计数减少.
 *
 * public boolean await(long timeout,TimeUnit unit) throws InterruptedException
 *
 * 　　使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断或超出了指定的等待时间。如果当前计数为零，则此方法立刻返回true值。
 *
 * 　　如果当前计数大于零，则出于线程调度目的，将禁用当前线程，且在发生以下三种情况之一前，该线程将一直出于休眠状态：
 *
 * 　　由于调用countDown()方法，计数到达零；或者其他某个线程中断当前线程；或者已超出指定的等待时间。
 *
 * 如果计数到达零，则该方法返回true值。
 * 如果当前线程，在进入此方法时已经设置了该线程的中断状态；或者在等待时被中断，则抛出InterruptedException，并且清除当前线程的已中断状态。
 * 如果超出了指定的等待时间，则返回值为false。如果该时间小于等于零，则该方法根本不会等待。
 * 参数：
 *
 * 　　timeout-要等待的最长时间
 *
 * 　　unit-timeout 参数的时间单位
 *
 * 返回：
 *
 * 　　如果计数到达零，则返回true；如果在计数到达零之前超过了等待时间，则返回false
 *
 * 抛出：
 *
 * 　　InterruptedException-如果当前线程在等待时被中断
 */
public final class DateUtil {

    private DateUtil() {
    }

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat(DEFAULT_DATE_PATTERN));

    public static SimpleDateFormat getFormatter(String pattern) {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        simpleDateFormat.applyPattern(getPattern(pattern));
        return simpleDateFormat;
    }

    public static SimpleDateFormat getFormatter() {
        return threadLocal.get();
    }

    private static String getPattern(String pattern) {
        if (pattern != null && !"".equals(pattern)) {
            return pattern;
        }
        return DEFAULT_DATE_PATTERN;
    }


    /**
     * 测试案例，分别测试
     * 1.多线程共用一个SimpleDateFormat进行parse，会抛异常
     * 2.多线程内各自new SimpleDateFormat，不会抛异常
     * 3.引入ThreadLocal
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
//        testException();
//        testFormatterWithNew();
        testFormatterWithTL();
    }


    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static void testException() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Date parse = formatter.parse("2020-12-05 16:40:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void testFormatterWithNew() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parse = formatter.parse("2020-12-05 16:40:00");
                    countDownLatch.countDown();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "毫秒");
    }

    private static void testFormatterWithTL() throws InterruptedException {
        // https://www.cnblogs.com/Lee_xy_z/p/10470181.html
        CountDownLatch countDownLatch = new CountDownLatch(100000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                try {
                    Date parse = DateUtil.getFormatter().parse("2020-12-05 16:40:00");
                    countDownLatch.countDown();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "毫秒");
    }
}