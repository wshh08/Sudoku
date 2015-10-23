package com.wshh08.sudoku;

import android.util.Log;

/**
 * Created by wshh08 on 15-10-18.
 */
public class Game {
    //    准备一个字符串数字
    public final String STR = "360000000004230800000004200"
            + "070460003820000014500013020" + "001900000007048300000000045";
    //    一个数组，用于存储某个单元格中的不可用数据
    private int[] data = new int[9 * 9];
    //    一个数组，用于存储所有单元格中的不可用数据
    int[][][] usedAll = new int[9][9][];

    public Game() {
        data = getData(STR);
        calculateAllUsedTiles();
    }

    //    获得某个单元格中数字
    private int getTile(int x, int y) {
        return data[x + y * 9];
    }

    //    将单元格中数字转换为字符串格式
    public String getTileString(int x, int y) {
        int v = getTile(x, y);
        if (v == 0) {
            return "";
        } else {
            return String.valueOf(v);
        }
    }

    //定义方法，将字符串常量转换为数字存入数组，作为游戏初始化数据
    private int[] getData(String strData) {
        int[] sudo = new int[strData.length()];
        for (int i = 0; i < sudo.length; i++) {
            sudo[i] = strData.charAt(i) - '0'; //c-'0'为字符ASCII编码与'0'编码差值，等于字符具体int数值(将数字字符转换为具体数值)
        }
        return sudo;
    }

//    取出所有单元格中的不可用数据
    public void calculateAllUsedTiles()
    {
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                usedAll[i][j] = calculateUsedTile(i, j);
            }
        }
    }

//    取出某单元格中的不可用数据
    public int[] getUsedTile(int x, int y)
    {
        return usedAll[x][y];
    }

//    计算某一单元格中的不可用数据
    public int[] calculateUsedTile(int x, int y) //x代表列，y代表行？
    {
        int[] c = new int[9];

//        计算纵向列不可用数据
        for(int i=0; i<9; i++)
        {
            if(i==y)
                continue;
            int t = getTile(x, i);
            if(t!=0)
            {
                c[t-1] = t;
            }
        }
//        计算横向行不可用数据
        for(int i=0; i<9; i++)
        {
            if(i==x)
                continue;
            int t = getTile(i, y);
            if(t!=0)
                c[t-1] = t;
        }
//        计算小九宫格中的不可用数据
        int startx = (x/3)*3;
        int starty = (y/3)*3;
        for(int i=startx; i<startx+3; i++)
            for(int j=starty; j<starty+3; j++)
            {
                if(i==x&&j==y)
                    continue;
                int t = getTile(i, j);
                if(t != 0)
                    c[t - 1] = t;
            }
        int numused = 0;
        for (int t : c)
        {
            if (t != 0)
                numused++;
        }
        int[] c1 = new int[numused];
        numused = 0;
        for (int t : c)
        {
            if (t != 0)
            c1[numused++] = t;
        }
        return c1;
    }

//    接收KeyDialog中点击的数字
    public boolean setTileIfValid(int x, int y, int value)
    {
        int[] tiles = getUsedTile(x, y); //得到不可用的数据
        for(int i=0; i<tiles.length; i++)
            Log.e("TAG", "tiles["+i+"]"+tiles[i]);
        if (value !=0)
        {
            for(int t : tiles)
            {
                if(t == value)
                    return false;
            }
        }
        setTile(x, y, value); //将对应的值value绘制在xy对应的格子中
        calculateAllUsedTiles(); //重新计算所有格子的不可用数据
        return true;
    }

    public void setTile(int x, int y, int value)
    {
        data[y * 9 + x] = value;
    }

}















