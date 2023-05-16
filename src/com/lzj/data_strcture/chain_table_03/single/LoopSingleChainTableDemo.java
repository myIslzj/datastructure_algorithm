package com.lzj.data_strcture.chain_table_03.single;

/**
 * 环形单链表数据结构演示案例
 */
public class LoopSingleChainTableDemo {

    public static void main(String[] args) {

        LoopSingleChainTable l = new LoopSingleChainTable();
        // 添加
        for (int i = 1; i <= 5; i++) {
            LoopObjectDemo o = new LoopObjectDemo(i, "位置：" + i);
            l.add(o);
        }
        // 打印
        l.list();

        // 约瑟夫问题
        l.JosephuIssue(2, 2);

    }

}


/**
 * 无头节点链表
 */
class LoopSingleChainTable {

    /**
     * 节点对象
     */
    LoopObjectDemo node;

    /**
     * 添加节点
     *
     * @return
     */
    public boolean add(LoopObjectDemo node) {
        // 判断链表是否已有节点
        if (null == this.node) {  // 链表无节点，添加节点，并将当前节点下一个指向它自己形成环
            this.node = node;
            this.node.next = node;
            return true;
        }
        // 链表已有节点，遍历找到未节点
        LoopObjectDemo temp = this.node;
        while (temp.next != this.node) {
            temp = temp.next;
        }
        temp.next = node;  // 尾节点下一个节点指向新增节点
        node.next = this.node;  // 新增节点的下一个节点指向头节点
        return true;
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (null == node) {
            System.out.println("链表还未添加任何节点！！！");
        }
        LoopObjectDemo temp = node;
        while (true) {
            System.out.println(temp);
            if (temp.next == node) {
//                System.out.println("节点" + temp.no + "的下一个节点\n" + temp.next);
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 约瑟夫问题，从第k个人开始数，数到m的那个人出列，得到一个出列序列表
     *
     * @param k
     * @param m
     */
    public void JosephuIssue(int k, int m) {
        // 判断链表是否有节点
        if (null == node) {
            System.out.println("链表还未添加任何节点！！！");
            return;
        }
        // 参数校验
        if (k < 1 || k > getSize() || m < 1) {
            System.out.println("参数不符合要求，，，，");
            return;
        }

        LoopObjectDemo temp = node;  // 记录需要出队的人
        LoopObjectDemo prev = node;  // 记录需要出队的前一个节点
        // 特殊情况：k=1, m=1
        if (1 == k && k == m) {
            do {
                System.out.println("特殊队列：" + temp);
                temp = temp.next;
            } while (temp != node);
            return;
        }
        // 找到第k个人
        while (temp.no != k) {
            prev = temp;
            temp = temp.next;
        }
        // 循环打印出队节点
        while (true) {
            // 数到第m个人出队
            for (int i = 0; i < m - 1; i++) {
                prev = temp;
                temp = temp.next;
            }
            // 判断需要出队的节点是否为第一个节点
            System.out.println("出队节点：" + temp);
            if (temp == temp.next) {
                return;
            }
            prev.next = temp.next;
            temp = temp.next;
        }

    }

    /**
     * 获取链表节点个数
     *
     * @return
     */
    public int getSize() {
        if (null == node) {
            return 0;
        }
        int count = 1;
        LoopObjectDemo temp = node;
        while (temp.next != node) {
            temp = temp.next;
            count++;
        }
        return count;
    }

}


/**
 * 节点对象
 */
class LoopObjectDemo {

    /**
     * no  -->  编号
     * data  -->  数据
     * next  -->  下一个节点指针
     */
    Integer no;
    String data;
    LoopObjectDemo next;

    /**
     * 无参构造函数
     */
    public LoopObjectDemo() {
    }

    /**
     * 有参构造函数
     */
    public LoopObjectDemo(int no, String data) {
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