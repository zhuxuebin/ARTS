package com.zxb.structurealgo.dijkstraAlgo;

/**
 * @ClassName PriorityQueue
 * @Description 优先级队列，根据Vertex.distance构建小顶堆
 * @Author xuery
 * @Date 2019/3/21 20:43
 * @Version 1.0
 */
public class PriorityQueue {

    Vertx[] nodes; //堆数组
    int count;     //当前队列实际存在多少个值

    public PriorityQueue(int v){
        /**
         * 多加1，从下标为1的位置开始存数据，方便计算堆的节点的左右子树
         * 下标为i的左右子树为:2*i,2*i+1,父节点为i/2
         */
        nodes = new Vertx[v+1];
        count = 0; //专栏里面应该是写错了
    }

    public Vertx poll(){
        if(count == 0){
            return null;
        }
        /**
         * 下标为1的元素就是要出队列的元素，
         * 利用小顶堆性质：将下标为1的元素与最后一个元素交换，最后一个元素出队列并将count减1，之后从第一个元素开始向下堆化
         * 假设堆化时有n个元素，下标1...n,则只需要堆化到n/2即可，因为后续的都是叶子节点，举个例子就知道了
         */
        Vertx pollVertx = nodes[1];
        swap(nodes, 1, count);
        count--;
        //从1-count/2开始堆化
        int i=1;
        while(count > 1 && i <= count/2){
            //如果i比它的左右叶子节点2*i,2*i+1大则继续调整
            int distance = nodes[i].distance;
            int swapIndex = -1;
            if(distance > nodes[2*i].distance){
                distance = nodes[2*i].distance;
                swapIndex = 2*i;
            }
            if(2*i+1 <= count && distance > nodes[2*i+1].distance){
                swapIndex = 2*i+1;
            }
            if(swapIndex != -1){
                swap(nodes,i,swapIndex);
                i = swapIndex;
            } else {
                break;
            }
        }
        return pollVertx;
    }

    private void swap(Vertx[] nodes, int i, int j){
        Vertx tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }

    public void add(Vertx vertx){
        /**
         * 小顶堆插入数据，count加1，先将其插入末尾
         * 然后从末尾开始往上按照小顶堆堆化
         */
        count++;
        nodes[count] = vertx;
        int i = count; //i的父节点为i/2
        while(i/2 >=1){
            if(nodes[i].distance < nodes[i/2].distance){
                swap(nodes,i,i/2);
                i = i/2;
            } else {
                break;
            }
        }
    }

    public void update(Vertx vertx){
        /**
         * 先根据vertex.nodeValue找到其在堆数组中的位置并更新它的distance
         * 之后从该点开始，在这里之所以更新它是因为找到了更小的distance,
         * 所以从该点开始向上按小顶堆堆化即可，画图举例就知道了
         *
         * 这个查找复杂度为O(n),有点高,这里其实可以通过构造map来实现快速查找<nodeValue,在数组中的位置>
         */
        int updateIndex = -1;
        for(int i=1;i<=count;i++){
            if(nodes[i].nodeValue == vertx.nodeValue){
                updateIndex = i;
                break;
            }
        }
        if(updateIndex != -1){
            nodes[updateIndex].distance = vertx.distance;
        }
        int i = updateIndex;//从updateIndex开始向上堆化
        while(i/2 >=1){
            if(nodes[i].distance < nodes[i/2].distance){
                swap(nodes,i,i/2);
                i = i/2;
            } else {
                break;
            }
        }

    }

    public boolean isEmpty(){
        return count == 0;
    }
}
