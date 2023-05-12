package com.lzj.data_strcture.chain_table_03.single;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * 单向链表数据结构演示案例
 */
public class SingleChainTableDemo {
    public static void main(String[] args) {
        SingleChainTable singleChainTable = new SingleChainTable();
        SingleChainTable p5 = new SingleChainTable();
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;  // 记录是否结束
        int i = 0;  // 记录位置
        while (!flag) {
            System.out.println("请输入对应操作编号：A：添加、SA：顺序添加、L：遍历、U：修改、D：删除、P1：总数、P2：倒数第k个节点、P3：反转、P4：末尾打印、B：结束");
            String b = scanner.next();  // 记录操作
            switch (b) {
                case "A":  // 添加
                    i++;
                    singleChainTable.add(new SingleChainTable.ObjectDeno(i, "位置" + i));
                    break;
                case "SA":  // 顺序添加
                    int sa = new Random().nextInt(200);
                    singleChainTable.sortAdd(new SingleChainTable.ObjectDeno(sa, "位置" + sa));
                    int psa = new Random().nextInt(200);
                    p5.sortAdd(new SingleChainTable.ObjectDeno(psa, "位置" + psa));
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
                    /*
                    练习题：
                    1.求单链表中有效节点个数；
                    2.查找单链表中倒数第k个节点；
                    3.单链表的反转；
                    4.从尾到头打印单链表。要求，方式1：反向遍历、方式2：使用Stack栈；
                    5.合并两个有序的单链表，合并后依然有序。
                    */
                case "P1":  // 练习题1
                    int count = singleChainTable.p1();
                    System.out.println("有效节点个数：" + count);
                    break;
                case "P2":  // 练习题2
                    int k = new Random().nextInt(20);
                    SingleChainTable.ObjectDeno objectDeno = singleChainTable.p2(k);
                    System.out.println("倒数第" + k + "个节点：" + objectDeno);
                    break;
                case "P3":  // 练习题3
                    singleChainTable.p3();
                    break;
                case "P4":  // 练习题4
                    singleChainTable.p4();
                    break;
                case "P5":  // 练习题5
                    p5.list();
                    singleChainTable.p5(p5);
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

    }

}

class SingleChainTable {

    /**
     * 头节点
     */
    ObjectDeno head = new ObjectDeno();

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

    /**
     * 查询链表中有效节点数
     *
     * @return
     */
    public int p1() {
        int count = 0;
        for (ObjectDeno temp = head.next; null != temp; temp = temp.next) {
            count++;
        }
        return count;
    }

    /**
     * 查找单链表中倒数第k个节点
     *
     * @param k
     * @return
     */
    public ObjectDeno p2(int k) {
        // 判断链表是否已有节点
        ObjectDeno temp = head;
        if (null == temp.next) {  // 无节点
            System.out.println("还未添加任何节点。。。");
            return null;
        }
        // 判断参数是否有效
        if (k <= 0 || k > p1()) {
            System.out.println("参数值越界。。。");
            return null;
        }
        // 找到第(p1() - k)个节点，此节点的下一个节点即为倒数第k个节点
        for (int i = (p1() - k); i > 0; i--) {
            temp = temp.next;
        }
        return temp.next;
    }

    /**
     * 单链表反转
     */
    public void p3() {
        // 判断链表是否已有节点
        if (null == head.next) {  // 无节点
            System.out.println("还未添加任何节点。。。");
            return;
        }
        /*
        反转思路：
        1、正序遍历节点，将临时头节点的下一个节点不断指向被遍历的节点，被遍历的节点的下一个节点不断指向临时头节点的下一个节点
        2、遍历完后，将链表的头节点的下一个节点指向临时头节点的下一个节点
        3、将临时头节点的下一个节点指向null，方便GC
         */
        ObjectDeno tempHead = new ObjectDeno();  // 临时头节点
        ObjectDeno prev;  // 记录上一个被遍历的节点
        for (ObjectDeno temp = head.next; null != temp; ) {
            prev = temp;  // 指向当前被遍历的节点
            temp = temp.next;  // 指向下一个需要遍历的节点
            prev.next = tempHead.next;  // 将当前被遍历的节点的下一个节点指向临时头节点的下一个节点
            tempHead.next = prev;  // 将临时头节点的下一个节点指向当前被遍历的节点
        }
        head.next = tempHead.next;  // 将链表的头节点的下一个节点指向临时头节点的下一个节点
        tempHead.next = null;  // 将临时头节点的下一个节点指向null，方便GC
        System.out.println("反转完成。。。。");
    }

    /**
     * 从尾到头打印单链表。要求，方式1：反向遍历、方式2：使用Stack栈
     */
    public void p4() {
        // 判断链表是否已有节点
        if (null == head.next) {  // 无节点
            System.out.println("还未添加任何节点。。。");
            return;
        }
        // 方式一：循环遍历末尾节点
        ObjectDeno lastNode = null;  // 记录最后一个遍历的节点
        ObjectDeno temp;  // 记录当前被遍历的节点
        boolean flag = false;  // 记录是否结束循环
        System.out.println("方式一打印（循环打印）：");
        while (!flag) {
            // 重置当前被遍历的节点为头节点，继续遍历打印下一个末尾节点
            temp = head;
            // 找到末尾节点
            while (null != temp.next && temp.next != lastNode) {
                temp = temp.next;
            }
            // 将末尾节点指向最后一个遍历的节点
            lastNode = temp;
            // 打印
            System.out.println(lastNode);
            // 判断是否已遍历出第一个节点
            if (lastNode == head.next) {
                flag = true;  // 最后一个被遍历的节点是第一个节点，结束遍历
            }
        }
        System.out.println("方式二打印（栈打印）：");
        Stack<ObjectDeno> s = new Stack<>();
        int count = 0;
        for (ObjectDeno v = head.next; null != v; v = v.next) {
            count++;
            s.add(v);
        }
        for (int i = 1; i <= count; i++) {
            System.out.println(s.pop());
        }
    }

    /**
     * 合并两个有序的链表
     */
    public void p5(SingleChainTable merge) {
        // 判断链表是否已有节点
        if (null == head.next || null == merge.head) {  // 无节点
            System.out.println("还未添加任何节点。。。");
            return;
        }
        ObjectDeno changeBeforeNext;  // 记录变换前的下一个节点
        ObjectDeno changeAfterNext;  // 记录变化后的下一个节点
        ObjectDeno recordMaster = head.next;  // 记录主链表连续的最后一个节点
        boolean flag = false;  // 记录是否结束所有循环
        for (ObjectDeno temp = recordMaster; null != temp; temp = temp.next.next) {
            ObjectDeno recordMerge = merge.head.next;  // 记录merge连续的最后一个节点
            for (ObjectDeno mergeTemp = recordMerge; null != mergeTemp; mergeTemp = mergeTemp.next) {
                if (temp.no > mergeTemp.no) {
                    // 往前移动
                    recordMaster = mergeTemp = mergeTemp.next;
                } else if (temp.next.no > mergeTemp.no) {
                    // 往前移动
                    recordMaster = temp = temp.next;
                } else {
                    changeBeforeNext = temp.next;
                    changeAfterNext = mergeTemp.next;
                    temp.next = mergeTemp;
                    mergeTemp.next = changeBeforeNext;
                    merge.head.next = changeAfterNext;
                    break;  // 结束当前循环
                }
                if (null == mergeTemp.next){
                    flag = true;
                }
            }
            if (flag){
                break;
            }
        }
    }

}