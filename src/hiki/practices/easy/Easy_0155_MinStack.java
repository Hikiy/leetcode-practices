package hiki.practices.easy;

import java.util.Stack;

/**
 * 155. 最小栈
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 */
public class Easy_0155_MinStack {
    // 数据栈
    private Stack<Integer> data;
    // 辅助栈
    private Stack<Integer> helper;

    public Easy_0155_MinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    public void push(int x) {
        data.add(x);
        if( helper.isEmpty() || helper.peek() >= x){
            helper.add(x);
        }else{
            helper.add(helper.peek());
        }
    }

    public void pop() {
        if( !data.isEmpty() ) {
            data.pop();
            helper.pop();
        }
    }

    public int top() {
        if ( data.isEmpty() ){
            throw new RuntimeException();
        }

        return data.peek();
    }

    public int getMin() {
        if ( helper.isEmpty() ){
            throw new RuntimeException();
        }
        return helper.peek();
    }
}
