package stackAndQueue.hanoi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Hanoi {
    public static final Map<Action, StepInfo> stepInfoMap = new HashMap<>();
    public static Stack<Integer> LStack;
    public static Stack<Integer> MStack;
    public static Stack<Integer> RStack;


    public static int stack2stack(Action[] preAction, Action nowAction) {
        StepInfo stepInfo = stepInfoMap.get(nowAction);
        if (preAction[0] != stepInfo.getReveseStep() && stepInfo.getFromStack().peek() < stepInfo.getToStack().peek()) {
            int value = stepInfo.getFromStack().pop();
            stepInfo.getToStack().push(value);
            System.out.println("Move " + value + " from " + stepInfo.getFromString() + " to " + stepInfo.getToString());
            preAction[0] = nowAction;
            return 1;
        }
        return 0;
    }

    public static void handoi(int num) {
        LStack = new Stack<>();
        MStack = new Stack<>();
        RStack = new Stack<>();

        LStack.push(Integer.MAX_VALUE);
        MStack.push(Integer.MAX_VALUE);
        RStack.push(Integer.MAX_VALUE);

        stepInfoMap.put(Action.L2M, new StepInfo(Action.M2L, "left", "middle", LStack, MStack));
        stepInfoMap.put(Action.M2R, new StepInfo(Action.R2M, "middle", "right", MStack, RStack));
        stepInfoMap.put(Action.R2M, new StepInfo(Action.M2R, "right", "middle", RStack, MStack));
        stepInfoMap.put(Action.M2L, new StepInfo(Action.L2M, "middle", "left", MStack, LStack));
        for (int i = num; i > 0; i--) {
            LStack.push(i);
        }
        Action[] record = {Action.No};
        final int[] step = {0};

        while (RStack.size() != num + 1) {
            Arrays.stream(Action.values()).filter(x -> x != Action.No).forEach(x -> {
                int p = Hanoi.stack2stack(record, x);
                step[0] += p;
                if (p > 0) {
                    System.out.println("LStack:" + LStack);
                    System.out.println("MStack:" + MStack);
                    System.out.println("RStack:" + RStack);
                    System.out.println(step[0]);
                }
            });
        }
        System.out.println(step[0]);

    }

    public static void main(String[] args) {
        handoi(2);
    }


}

enum Action {
    No, L2M, M2R, R2M, M2L
}

class StepInfo {
    private Action reveseStep;
    private String fromString;
    private String toString;
    private Stack<Integer> fromStack;
    private Stack<Integer> toStack;

    public StepInfo(Action reveseStep, String fromString, String toString, Stack<Integer> fromStack, Stack<Integer> toStack) {
        this.reveseStep = reveseStep;
        this.fromString = fromString;
        this.toString = toString;
        this.fromStack = fromStack;
        this.toStack = toStack;
    }

    public Action getReveseStep() {
        return reveseStep;
    }

    public void setReveseStep(Action reveseStep) {
        this.reveseStep = reveseStep;
    }

    public String getFromString() {
        return fromString;
    }

    public void setFromString(String fromString) {
        this.fromString = fromString;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public Stack<Integer> getFromStack() {
        return fromStack;
    }

    public void setFromStack(Stack<Integer> fromStack) {
        this.fromStack = fromStack;
    }

    public Stack<Integer> getToStack() {
        return toStack;
    }

    public void setToStack(Stack<Integer> toStack) {
        this.toStack = toStack;
    }
}