package com.lzj.data_stracture.chain_table_03.both;

import java.util.Random;
import java.util.Scanner;

/**
 * 双向链表结构演示案例
 */
public class DoubleChainTableDemo {
    public static void main(String[] args) {
        DoubleChainTable d = new DoubleChainTable();
        /*d.list();
        for (int i = 0; i < 5; i++) {
            int numb = new Random().nextInt(100);
            DoubleObjectDemo node = new DoubleObjectDemo(numb, "位置：" + numb);
            // 无序添加
//            d.add(node);
            // 有序添加
            d.sortAdd(node);
        }
        // 打印
        d.list();*/

        // 控制台操作
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("请输入操作，add：新增、del：删除、list：遍历");
            String operation = s.next();
            switch (operation) {
                case "add":
                    int numb = new Random().nextInt(100);
                    DoubleObjectDemo node = new DoubleObjectDemo(numb, "位置：" + numb);
                    d.add(node);
                    break;
                case "del":
                    System.out.println("请输入节点编号");
                    int no = s.nextInt();
                    d.del(no);
                    break;
                case "list":
                    d.list();
                    break;
                default:
                    break;
            }
        }

    }

}

/**
 * 双向链表类
 */
class DoubleChainTable {

    DoubleObjectDemo node;  // 节点对象

    /**
     * 添加节点
     *
     * @param node
     */
    public boolean add(DoubleObjectDemo node) {
        if (null == this.node) {
            this.node = node;
            return true;
        }
        DoubleObjectDemo temp = this.node;
        while (null != temp.next) {
            temp = temp.next;
        }
        temp.next = node;
        node.prev = temp;
        return true;
    }

    /**
     * 顺序添加节点
     *
     * @param node
     * @return
     */
    public boolean sortAdd(DoubleObjectDemo node) {
        if (null == this.node) {
            this.node = node;
            return true;
        }
        DoubleObjectDemo temp = this.node;
        while (true) {
            if (temp.no > node.no) {
                if (temp == this.node) {  // 当前节点为头节点
                    this.node = node;  // 重置头节点
                } else {
                    node.prev = temp.prev;  // 新增节点的前一个节点指向当前节点的前一个节点
                    temp.prev.next = node;  // 当前节点的上一个节点的下一个节点指向新增节点
                }
                node.next = temp;  // 新增节点的下一个节点指向当前节点
                temp.prev = node;  // 当前节点的上一个节点指向新增节点
                return true;
            } else {
                if (null == temp.next) {  // 已是最后一个节点
                    temp.next = node;
                    node.prev = temp;
                    return true;
                }
            }
            temp = temp.next;
        }
    }

    /**
     * 删除节点
     *
     * @param no 节点编号
     * @return
     */
    public boolean del(int no) {
        if (null == node) {
            System.out.println("还未添加任何节点。。。。");
            return false;
        }
        DoubleObjectDemo temp = node;
        while (null != temp) {
            if (temp.no == no) {
                if (temp == node) {  // 需要删除的是头节点
                    node = node.next;
                    return true;
                } else {
                    if (null == temp.next) {  // 需要删除的是最后一个节点
                        temp.prev.next = null;
                    } else {
                        temp.prev.next = temp.next;
                        temp.next.prev = temp.prev;
                    }
                    return true;
                }
            }
            temp = temp.next;
        }
        System.out.println(no + "节点不存在........");
        return false;
    }

    /**
     * 遍历双向链表
     */
    public void list() {
        if (null == node) {
            System.out.println("还未添加任何节点。。。。");
            return;
        }
        DoubleObjectDemo temp = node;
        while (null != temp) {
            System.out.println("node=" + temp + " prev=" + temp.prev + " next=" + temp.next);
            temp = temp.next;
        }
    }

}

/**
 * 节点对象
 */
class DoubleObjectDemo {

    /**
     * no  -->  编号
     * data  -->  数据
     * prev  -->  上一个节点指针
     * next  -->  下一个节点指针
     */
    Integer no;
    String data;
    DoubleObjectDemo prev;
    DoubleObjectDemo next;

    /**
     * 无参构造函数
     */
    public DoubleObjectDemo() {
    }

    /**
     * 有参构造函数
     */
    public DoubleObjectDemo(int no, String data) {
        this.no = no;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ObjectDeno{" +
                "no=" + no +
                ", data='" + data + '\'' +
                '}';
    }

}