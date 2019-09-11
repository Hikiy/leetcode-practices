package hiki.practices.medium;

/**
 * @author ：hiki
 * 2019/9/10 16:20
 */
public class Medium_0011_ContainerWithMostWater {

    //Time complexity   O(n2)
    //Space complexity  O(1)
    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0;i<height.length;i++){
            for (int j = 1;j<height.length;j++){
                int area = (j-i)*Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    //双指针法
    //Time complexity   O(n)
    //Space complexity  O(1)
    public static int maxArea2(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length-1;

        while( i < j ){
            maxArea = Math.max(maxArea, Math.min(height[i], height[j])*(j-i));
            if( height[i] < height[j] ){
                i++;
            }else{
                j--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        int[] a= {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(a));
        System.out.println(maxArea2(a));
    }
}
