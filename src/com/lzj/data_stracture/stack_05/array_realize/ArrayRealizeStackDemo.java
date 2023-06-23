package com.lzj.data_stracture.stack_05.array_realize;

import java.util.Scanner;

/**
 * 数组实现栈结构演示案例
 */
public class ArrayRealizeStackDemo {
    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        boolean loop = true;  // 控制退出
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("操作 1：添加、2：取出、3：遍历、4：退出");
            int i = scanner.nextInt();
            switch (i){
                case 1:
                    System.out.println("请输入需要添加的值");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 2:
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    stack.list();
                    break;
                case 4:
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }
}


/**
 * 数组实现的栈结构
 */
class ArrayStack {

    private int maxSize;  // 最大长度
    private int[] data;  // 数据
    private int top;  // 栈顶索引，初始化为-1

    /**
     * 初始化栈空间
     *
     * @param size 空间大小
     */
    public ArrayStack(int size) {
        maxSize = size;
        top = -1;
        data = new int[maxSize];
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        if (top == maxSize - 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isNull() {
        if (top == - 1) {
            return true;
        }
        return false;
    }

    /**
     * 元素入栈
     * @param value
     * @return
     */
    public boolean push(int value){
        // 判断栈是否已满
        if (isFull()){
            System.out.println("栈空间已满。。");
            return false;
        }
        // 入栈
        data[++top] = value;
        return true;
    }

    /**
     * 元素出栈
     * @return
     */
    public int pop(){
        // 判断栈是否为空
        if (isNull()){
            throw new RuntimeException("栈内无数据。。");
        }
        // 出栈
        return data[top--];
    }

    /**
     * 遍历元素
     */
    public void list(){
        // 判断栈是否为空
        if (isNull()){
            throw new RuntimeException("栈内无数据。。");
        }
        // 打印元素
        for (int i = top; i > -1; i--){
            System.out.println(data[i]);
        }
    }

}