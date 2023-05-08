package com.lzj.data_strcture.chain_table_03.single;

import java.util.Random;
import java.util.Scanner;

/**
 * 单向链表数据结构演示案例
 */
public class SingleChainTableDemo {
    public static void main(String[] args) {
        SingleChainTable singleChainTable = new SingleChainTable();
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;  // 记录是否结束
        int i = 0;  // 记录位置
        while (!flag) {
            System.out.println("请输入对应操作编号：A：添加、SA：顺序添加、L：遍历、U：修改、D：删除、B：结束");
            String b = scanner.next();  // 记录操作
            switch (b) {
                case "A":  // 添加
                    i++;
                    singleChainTable.add(new SingleChainTable.ObjectDeno(i, "位置" + i));
                    break;
                case "SA":  // 顺序添加
                    int sa = new Random().nextInt(20);
                    singleChainTable.sortAdd(new SingleChainTable.ObjectDeno(sa, "位置" + sa));
                    break;
                case "L":  // 遍历
                    singleChainTable.list();
                    break;
                case "U":  // 修改
                    int site = new Random().nextInt(3);
                    singleChainTable.upd(new SingleChainTable.ObjectDeno(site, "修改位置" + site));
                    break;
                case "D":  // 删除
                    int dsite = new Random().nextInt(3);
                    singleChainTable.del(dsite + 3);
                    break;
                case "B":
                    flag = true;
                    System.out.println("正在退出。。。。。");
                    break;
                default:
                    break;
            }
        }

        /*
        练习题：
        1.求单链表中有效节点个数；
        2.查找单链表中倒数第k个节点；
        3.单链表的反转；
        4.从尾到头打印单链表。要求，方式1：反向遍历、方式2：使用Stack栈；
        5.合并两个有序的单链表，合并后依然有序。
        */
        // P1



    }

}

class SingleChainTable {

    /**
     * 头节点
     */
    static ObjectDeno head = new ObjectDeno();

    /**
     * 节点对象
     */
    static class ObjectDeno {

        /**
         * no  -->  编号
         * data  -->  数据
         * next  -->  下一个节点指针
         */
        Integer no;
        String data;
        ObjectDeno next;

        /**
         * 无参构造函数
         */
        public ObjectDeno() {
        }

        /**
         * 有参构造函数
         */
        public ObjectDeno(int no, String data) {
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

    /**
     * 新增节点
     *
     * @param node 新节点
     * @return
     */
    public boolean add(ObjectDeno node) {
        // 判断链表是否已有节点
        if (null == head.next) {  // 无节点
            head.next = node;
            System.out.println("已添加。。。。");
            return true;
        }
        // 已有节点，找到链表最后一个节点
        ObjectDeno temp = head;
        while (null != temp.next) {  // 当前节点下一个节点为null代表已是最后一个节点
            temp = temp.next;
        }
        // 将最后一个节点的下一个节点指向新增的节点
        temp.next = node;
        System.out.println("已添加。。。。");
        return true;
    }

    /**
     * 排序添加节点
     */
    public boolean sortAdd(ObjectDeno node) {
        // 判断链表是否已有节点
        if (null == head.next) {  // 无节点
            head.next = node;
            System.out.println("已顺序添加。。。。");
            return true;
        }
        // 已有节点，找到新增节点合适的位置，根据编号排序
        ObjectDeno prev = head;  // 记录需要被替换位置的节点的上一个节点
        ObjectDeno temp;  // 记录当前被遍历的节点
        for (temp = head.next; null != temp; temp = temp.next) {
            if (temp.no > node.no) {  // 当前节点编号大于新增节点编号，表示已找到新增节点的位置
                break;
            } else if (temp.no < node.no) {  // 当前节点编号小于新增节点编号，继续查找
                // 将当前节点赋值给prev，继续匹配下一个节点
                prev = temp;
            } else {
                System.out.println("节点编号已存在：" + node.no);
                return false;
            }
        }
        // 判断停止遍历的位置的节点的上一个节点的下一个节点是否为null
        if (null != prev.next) {  // 不为null，表示不是最后一个节点
            // 将新增节点的下一个节点指向结束遍历的节点
            node.next = temp;
        }
        // 将被替换的节点的上一个节点的下一个节点指向新节点
        prev.next = node;
        System.out.println("已顺序添加。。。。");
        return true;
    }

    /**
     * 遍历链表
     */
    public void list() {
        // 判断链表是否已有节点
        if (null == head.next) {  // 无节点
            System.out.println("当前链表还未添加节点，，，，，");
            return;
        }
        // 遍历
        for (ObjectDeno temp = head.next; null != temp; temp = temp.next) {
            System.out.println(temp);
        }
    }

    /**
     * 修改节点，根据编号修改
     *
     * @param node
     * @return
     */
    public boolean upd(ObjectDeno node) {
        // 判断链表是否已有节点
        if (null == head.next) {  // 无节点
            System.out.println("还未添加任何节点。。。");
            return false;
        }
        // 已有节点，找到需要修改的节点
        for (ObjectDeno temp = head.next; null != temp; temp = temp.next) {
            if (temp.no == node.no) {
                temp.data = node.data;
                System.out.println("已修改。。。。");
                return true;
            }
        }
        System.out.println("未找到匹配的节点。。。。");
        return false;
    }

    /**
     * 删除节点
     *
     * @param no 编号
     * @return
     */
    public boolean del(int no) {
        // 判断链表是否已有节点
        if (null == head.next) {  // 无节点
            System.out.println("还未添加任何节点。。。");
            return false;
        }
        // 已有节点，找到需要删除的节点
        ObjectDeno prev = head;  // 记录需要删除节点的上一个节点
        for (ObjectDeno temp = head.next; null != temp; temp = temp.next) {
            if (temp.no == no) {  // 已找到需要删除的节点
                // 将需要删除的节点的上一个节点的下一个节点指向需要删除节点的下一个节点
                prev.next = temp.next;
                // 将需要删除节点的下一个节点置为null，方便GC回收
                temp.next = null;
                System.out.println("已删除。。。");
                return true;
            } else {
                // 将当前节点赋值给prev，继续匹配下一个节点
                prev = temp;
            }
        }
        System.out.println("未匹配到需要删除的节点。。。。");
        return false;
    }

}