package stackAndQueue.petQueue;

/**
 * 题目 有猫狗宠物类 不允许改变类的结构实现以下方法
 * 用户可以调用add方法将cat类或dog类的实例放入队列中;
 * 用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出;
 * 用户可以调用pollDog方法，将队列中dog类的实例按照进队列的先后顺序依次弹出;
 * 用户可以调用pollCat方法，将队列中cat类的实例按照进队列的先后顺序依次弹出;
 * 用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例;
 * 用户可以调用isDogEmpty方法，检查队列中是否有dog类的实例;
 * 用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
 */


import java.util.LinkedList;
import java.util.Queue;

/**
 * 解题思路：
 * 1.重构猫狗队列中存储的元素，创建新的类，加入时间戳
 * 2.猫狗队列中放入两个队列 猫队列 和狗队列 还有计数器
 * a)压入数据时，计数器加一，根据类型压入不同的队列
 * b）不分猫狗弹出时，比较两个队列顶端的元素的时间戳，谁早先弹出谁
 * c)分猫狗弹出时，从对应的队列弹出即可
 */
public class PetQueue {
    private Queue<PetQueueElement> dogQ;
    private Queue<PetQueueElement> catQ;
    private long count;

    public PetQueue() {
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        this.count++;
        PetQueueElement petQueueElement = new PetQueueElement(pet, this.count);
        if ("dog".equals(pet.getType())) {
            dogQ.add(petQueueElement);

        } else {
            catQ.add(petQueueElement);
        }
    }
    public Pet pollAll() throws Exception {
        if(!dogQ.isEmpty() && !catQ.isEmpty()){
            if(dogQ.peek().getCount() < catQ.peek().getCount()){
                return dogQ.poll().getPet();
            }else{
                return catQ.poll().getPet();
            }
        }else if(dogQ.isEmpty() && !catQ.isEmpty()){
            return catQ.poll().getPet();
        }else if(!dogQ.isEmpty() && catQ.isEmpty()){
            return dogQ.poll().getPet();
        }else{
            throw new Exception("Queue is empty");
        }

    }

    public Pet pollDog() throws Exception {
        if(!dogQ.isEmpty()){
            return dogQ.poll().getPet();
        }else{
            throw new Exception("Queue is empty");
        }
    }

    public Pet pollCat() throws Exception {
        if(!catQ.isEmpty()){
            return catQ.poll().getPet();
        }else{
            throw new Exception("Queue is empty");
        }
    }

    public boolean isEmpty(){
        return catQ.isEmpty() && dogQ.isEmpty();
    }

    public boolean isDogEmpty(){
        return dogQ.isEmpty();
    }


    public boolean isCatEmpty(){
        return catQ.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        PetQueue petQueue = new PetQueue();
        petQueue.add(new Cat());
        petQueue.add(new Dog());
        petQueue.add(new Dog());
        petQueue.add(new Cat());
        petQueue.add(new Dog());
        petQueue.add(new Cat());
        System.out.println(petQueue.pollAll().getType());
        System.out.println(petQueue.pollAll().getType());
        System.out.println(petQueue.pollDog().getType());
        System.out.println(petQueue.pollAll().getType());
    }
}

class PetQueueElement {
    private Pet pet;
    private long count;

    public PetQueueElement(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }


    public long getCount() {
        return count;
    }

    public String getPetType() {
        return this.pet.getType();
    }

}