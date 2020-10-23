package com.leetcode.train.interview;


/**
 * <p>手写单例模式</p>
 * 线程安全的单例实现类
 * 步骤一：私有化构造函数
 * 步骤二：私有化静态实例
 * 步骤三：通过静态getInstance() 方法获取单例的唯一实现类 ！！！ 注意线程安全的设置
 *
 * @author : sunchao
 * date : 2020-08-24 10:17
 **/
public class Singleton {

    /** *私有化构造函数 */
    private Singleton(){}

    /** 私有化静态实例，此处放置为了延迟加载*/
    private static Singleton instance = null;

    /**
     * 通过Singleton.getInstance方法暴露外部，实现单例模式的加载
     * @return 内存中唯一的singleton instance
     */
    public static Singleton getInstance() {
        if(instance == null) {
            synInit();
        }
        return instance;
    }

    /**
     * 线程安全的实例初始化函数
     */
    private static synchronized void synInit() {
        if(instance == null) {
            instance = new Singleton();
        }

    }



}
