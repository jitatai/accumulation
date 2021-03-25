package com.jt.web.threads;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/23 10:10
 */
public class TheadPoolTest {
    /**
     * 继承体系：Executor 顶级接口 唯一方法execute 设计理念，屏蔽线程创建，调度等过程，只关注任务执行本身
     * ExecutorService 继承Executor 丰富线程池定义 1.规定线程池状态 2.新增submit系列接口(未实现) 返回Future
     * AbstractExecutorService 实现ExecutorService 初步实现submit(R/C)方法 1.接收R/C 2.包装成FutureTask 3.execute让子类实现 4.返回异步结果FutureTask  ScheduledExecutorService 继承ExecutorService
     * ThreadPoolExecutor 实现AbstractExecutorService 实现了execute方法 调用addWorker方法 1.构建Worker对象：thread + futureTask 2.加入任务队列 3.调用new Thread(Work).start --> work.run
     * --> work.runWorker --> futureTask.run --> futureTask = callable + outcome --> callable.call()把结果设置回futureTask.outcome newTaskFor把任务包装成FutureTask  ForkJoinPool
     */
}
