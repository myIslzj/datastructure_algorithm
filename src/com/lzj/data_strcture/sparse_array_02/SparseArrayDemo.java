package com.lzj.data_strcture.sparse_array_02;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组结构演示案例（二维数组与稀疏数组互转）
 * 案例：保存五子棋游戏结构（忽略无效坐标，0：代表无棋子、1：代表白棋、2：代表黑棋）
 */
public class SparseArrayDemo {
    public static void main(String[] args) throws IOException {

        System.out.println("------------------- 以下为二维数组转稀疏数组 ------------------------");
        arrayTurnSparsearray();

        System.out.println("------------------- 以下为稀疏数组转二维数组 ------------------------");
        sparsearrayTurnArray();

    }

    /**
     * 将二维数组转换为稀疏数组，并将数据写入到本地磁盘中
     *
     * @throws IOException
     */
    private static void arrayTurnSparsearray() throws IOException {
        // 创建 11 * 11 的二维棋盘
        int[][] chess = new int[11][11];
        // 添加一个白棋、一个黑棋
        chess[1][4] = 1;
        chess[2][5] = 2;
        chess[2][6] = 2;
        // 打印
        System.out.println("二维数组：");
        for (int[] ints : chess) {
            for (int i : ints) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        /*
           将二维数组转换为稀疏数组
           行数：有效数据（sum）+ 1
           列数：3
           第一行记录原二维数组的行数、列数和有效值个数
           其余行数记录有效值所在行、列和值
         */
        // 获取有效值个数
        int sum = 0;
        for (int[] ints : chess) {
            for (int i : ints) {
                if (0 != i) {
                    sum++;
                }
            }
        }
        // 创建稀疏数组
        int[][] sparseChess = new int[sum + 1][3];
        // 添加第一行数据
        sparseChess[0][0] = chess.length;  // 二维数组行数
        sparseChess[0][1] = chess[0].length;  // 二维数组列数
        sparseChess[0][2] = sum;  // 二维数组有效数据个数
        // 将有效数据添加到稀疏数组中
        int index = 0;  // 记录稀疏数组的数据行数下标
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if (chess[i][j] != 0) {
                    ++index;  // 下标后移
                    sparseChess[index][0] = i;
                    sparseChess[index][1] = j;
                    sparseChess[index][2] = chess[i][j];
                }
            }
        }
        // 打印稀疏数组
        System.out.println("稀疏数组：");
        for (int[] ints : sparseChess) {
            for (int i : ints) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
        /*
           将稀疏数组数据保存到磁盘
         */
        // 创建文件对象
        File file = new File("C:/Users/LiZhuJun/Desktop/map.txt");
        // 创建文件输出流，将数据写入到文件
        FileOutputStream fos = new FileOutputStream(file);
        // 创建带缓冲的字符输出流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        // 将二维数组数据写入到字符输出流中
        for (int[] ints : sparseChess) {
            for (int i : ints) {
                bw.write(String.valueOf(i));
                bw.write("\t");
            }
            // 换行
            bw.newLine();
        }
        // 刷新流
        bw.flush();
        // 关闭流
        bw.close();
        System.out.println("二维数组已写入本地文件：" + file.getAbsolutePath());
    }

    /**
     * 从本地磁盘中读取稀疏数组数据，并转换为二维数组
     */
    private static void sparsearrayTurnArray() throws IOException {
        /*
           从磁盘中读取出稀疏数组数据
         */
        System.out.println("从本地文件：C:/Users/LiZhuJun/Desktop/map.txt  读取稀疏数组数据");
        // 创建文件对象
        File file = new File("C:/Users/LiZhuJun/Desktop/map.txt");
        // 创建文件输入流，将文件内容读到流中
        FileInputStream fis = new FileInputStream(file);
        // 创建带缓冲的字符输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        // 创建数组集合，接收文件内容
        List<int[]> data = new ArrayList<>();
        String line;  // 文件行数据
        while ((line = br.readLine()) != null) {
            // 根据空格截取行数据
            String[] strings = line.split("\t");
            // 创建数组接收数据
            int[] arr = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                arr[i] = Integer.parseInt(strings[i]);
            }
            // 将数组添加到集合中
            data.add(arr);
        }
        // 创建二维数组
        int[][] twoArr = new int[data.size()][];
        // 遍历集合，将数据添加到二维数组中
        for (int i = 0; i < data.size(); i++) {
            twoArr[i] = data.get(i);
        }
        // 关闭流
        fis.close();
        // 打印稀疏数组
        System.out.println("稀疏数组：");
        for (int[] ints : twoArr) {
            for (int i : ints) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        /*
           将稀疏数组转换为二维数组
           行数、列数：稀疏数组第一行中取到[0][0]、[0][1]
           根据稀疏数组中的第一行的行、列值创建二维数组
           通过稀疏数组其它行记录，给二维数组对应位置赋值
         */
        // 创建二维数据
        int[][] twoArray = new int[twoArr[0][0]][twoArr[0][1]];
        // 将稀疏数组数据赋值到二维数组
        for (int i = 1; i < twoArr.length; i++) {
            twoArray[twoArr[i][0]][twoArr[i][1]] = twoArr[i][2];
        }
        // 打印二维数组
        System.out.println("二维数组：");
        for (int[] ints : twoArray) {
            for (int i : ints) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
    }

}
