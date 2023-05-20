package com.lzj.data_strcture.queue_04.array_create;

import java.util.Random;
import java.util.Scanner;

/**
 * 数组实现队列数据结构演示案例
 */
public class ArrayCreateQueueDemo {
    public static void main(String[] args) {

        ArrayCreateQueue queue = new ArrayCreateQueue(5);

        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("请输入操作，1：入队、2：出队");
            int i = s.nextInt();
            switch (i) {
                case 1:
                    int num = new Random().nextInt(5);
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
 * 数组创建的队列类
 */
class ArrayCreateQueue {

    int[] arr;  // 数组对象
    int maxSize;  // 最大长度
    int front = -1;  // 出队元素下标，-1表示无可出队元素
    int rear = 0;  // 入队元素下标，默认0号位

    public ArrayCreateQueue(int maxSize) {
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
        // 判断队列是否已满
        if (rear == maxSize) {
            System.out.println("队列已满。。。。");
            return false;
        }
        arr[rear] = e;  // 元素入队
        rear++;  // 入队下标后移，准备下一个入队元素的位置
        if (-1 == front) {  // 设置可出队元素下标
            front = 0;
        }
        return true;
    }

    /**
     * 元素出队
     *
     * @return
     */
    public boolean deq() {
        // 判断是否有可出队元素
        if (front == -1 || front == rear) {
            System.out.println("队列无可出队元素。。。。");
            return false;
        }
        System.out.println(arr[front] + "出队");
        front++;
        return true;
    }


}