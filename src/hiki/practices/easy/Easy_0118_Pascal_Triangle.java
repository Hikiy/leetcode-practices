package hiki.practices.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
*/
public class Easy_0118_Pascal_Triangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        if (numRows == 0) {
            return triangle;
        }

        //顶端
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            //上一行
            List<Integer> prevRow = triangle.get(rowNum-1);

            //每行第一个必为1
            row.add(1);

            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            //每行最后一个必为1
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }
}
