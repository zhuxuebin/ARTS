package com.zxb.diagram.ch08.greedy;

import java.util.*;

/**
 * 描述：
 * 假设你办了个广播节目，要让全美50个州的听众都收听得到。为此，你需要决定在哪些广播台播出。
 * 在每个广播台播出都需要支付费用，因此你力图在尽可能少的广播台播出
 * 典型的集合覆盖问题，给定一个目标总集合，给定一组输入集合，
 * 从输入集合列表中选取最少的集合数，保证他们的并集包含目标总集合，求这样的集合列表
 *
 * 暴力破解法：n!种选法，选出符合条件的最小集合数即可；n的值较小还好，n值很大的时候就要计算到猴年马月了
 * 近似解法：贪婪算法：每次都从剩余集合列表中选取能够与剩余未选择州交集最多元素的集合，直至覆盖了所有的州
 * @author xuery
 * @date 2018/8/8
 */
public class SetCoverProblem {

    public static void main(String[] args) {
        Set<String> target = new HashSet<>(Arrays.asList("mt", "wa", "or", "id", "nv", "ut", "ca", "az"));
        List<Set<String>> setList = new ArrayList<>(Arrays.asList(new HashSet<String>(Arrays.asList("id","nv","ut")),
                new HashSet<String>(Arrays.asList("wa","id","mt")),
                new HashSet<String>(Arrays.asList("or","nv","ca")),
                new HashSet<String>(Arrays.asList("nv","ut")),
                new HashSet<String>(Arrays.asList("ca","az"))));
        List<Set<String>> resSetList = findLeastConverSetList(target, setList);
        System.out.println(resSetList);
    }

    public static List<Set<String>> findLeastConverSetList(Set<String> target, List<Set<String>> setList){
        if(target == null || target.size() == 0){
            return new ArrayList<>();
        }
        //暂时不考虑找不到的情况
        List<Set<String>> resSetList = new ArrayList<>();
        while(!target.isEmpty()){
            Iterator<Set<String>> itSet = setList.iterator();
            Set<String> selectSet = new HashSet<>();
            while(itSet.hasNext()){
                Set<String> retainSet = new HashSet<>();
                Set<String> next = itSet.next();
                retainSet.addAll(next);
                retainSet.retainAll(target);
                if(retainSet.size() > selectSet.size()){
                    selectSet = next;
                }
            }
            //更新条件
            target.removeAll(selectSet);
            //这个不是必须的，因为再次查找不可能会再找到它了
            setList.remove(selectSet);
            resSetList.add(selectSet);
        }
        return resSetList;
    }
}
