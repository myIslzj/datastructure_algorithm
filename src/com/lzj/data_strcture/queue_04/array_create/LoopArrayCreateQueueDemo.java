package com.lzj.data_strcture.queue_04.array_create;

import java.util.Random;
import java.util.Scanner;

/**
 * 数组实现环形队列数据结构演示案例
 * 实现方式一：取莫
 * （1）设置一个辅助位，该位置是动态的
 * （2）front,rear默认为0
 * （3）队列满时：(rear + 1) % maxSize = front
 * （4）队列为空：rear == front
 * （5）队列有效数据个数：(rear + maxSize - front) % maxSize
 * 缺点：rear不断增大，存在数值越界风险，不能将队列添满
 * 实现方式二：front、rear比对法
 * （1）不需要设置辅助位
 * （2）初始化时：front = 0, rear = 0
 * （3）isLoop 记录是否已形成环
 * （4）队列为空时：false == isLoop && front == rear
 * （5）可添加元素时： front < rear
 * （6）front、rear等于maxSize后重置为0
 * （7）队列已满时：isLoop == false && rear - front == maxSize 或 isLoop == true && front == rear
 */
public class LoopArrayCreateQueueDemo {
    public static void main(String[] args) {

        // 实现方式一
//        LoopArrayCreateQueue_1 queue = new LoopArrayCreateQueue_1(5);
        // 实现方式二
        LoopArrayCreateQueue_2 queue = new LoopArrayCreateQueue_2(5);
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("请输入操作，1：入队、2：出队");
            int i = s.nextInt();
            switch (i) {
                case 1:
                    int num = (new Random().nextInt(5)) + 1;
                    queue.enq(num);
                    break;
                case 2:
                    queue.deq();
                    break;
                default:
                    break;
            }
        }

    }
}

/**
 * 实现方式一
 */
class LoopArrayCreateQueue_1 {

    int[] arr;  // 数组对象
    int maxSize;  // 最大长度
    int front = 0;  // 出队元素下标，初始化时0表示无可出队元素（默认为辅助位，该位置是动态的）
    int rear = 0;  // 入队元素下标，默认0号位

    public LoopArrayCreateQueue_1(int maxSize) {
        if (maxSize < 1) {
            System.out.println("最大长度无效。。。。");
            return;
        }
        this.maxSize = maxSize;
        arr = new int[this.maxSize + 1];
    }

    /**
     * 元素入队
     *
     * @param e
     * @return
     */
    public boolean enq(int e) {
        // 判断队列是否已满
        if (arr == null || (rear + 1) % maxSize == front) {
            System.out.println("队列已满。。。。");
            return false;
        }
        arr[rear] = e;  // 元素入队
        rear++;  // 入队下标后移，准备下一个入队元素的位置
        return true;
    }

    /**
     * 元素出队
     *
     * @return
     */
    public boolean deq() {
        // 判断是否有可出队元素
        if (front == -1 || front == maxSize) {
            System.out.println("队列无可出队元素。。。。");
            return false;
        }
        System.out.println(arr[front] + "出队");
        front++;
        return true;
    }


}
/**
 * 实现方式二
 */
class LoopArrayCreateQueue_2 {

    int[] arr;  // 数组对象
    int maxSize;  // 最大长度
    int front = 0;  // 出队元素下标，默认0号位
    int rear = 0;  // 入队元素下标，默认0号位
    boolean isLoop;  // 是否已形成环

    public LoopArrayCreateQueue_2(int maxSize) {
        if (maxSize < 1) {
            System.out.println("最大长度无效。。。。");
            return;
        }
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    /**
     * 元素入队
     *
     * @param e
     * @return
     */
    public boolean enq(int e) {
        if (rear == maxSize){  // 已添加到末尾
            rear = 0;  // 重置入队位为起始位置，形成环
            isLoop = true;  // 标志已形成环
        }
        // 判断队列是否已满
        if (arr == null || isLoop && rear == front) {
            System.out.println("队列已满。。。。");
            return false;
        }
        arr[rear++] = e;  // 元素入队，入队下标后移，准备下一个入队元素的位置
        System.out.println("入队元素：" + e + " ++后的下标：" + rear);
        return true;
    }

    /**
     * 元素出队
     *
     * @return
     */
    public boolean deq() {
        // 判断是否有可出队元素
        if (arr == null || (!isLoop && front == rear)) {
            System.out.println("队列无可出队元素。。。。");
            return false;
        }
        System.out.println(arr[front] + "出队");
        if (++front == maxSize){  // 已读取到末尾
            front = 0;  // 重置出队位为起始位置，形成环
            isLoop = false;  // 标志环解除
        }
        return true;
    }

}