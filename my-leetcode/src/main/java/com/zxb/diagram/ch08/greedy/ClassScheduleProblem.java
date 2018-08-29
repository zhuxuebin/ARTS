package com.zxb.diagram.ch08.greedy;


import java.util.*;

/**
 * 描述：
 * 假设有如下课程表，你希望将尽可能多的课程安排在某间教室上。
 * 美术：9:00-10:00
 * 英语：9:30-10:30
 * 数学：10:00-11:00
 * 计算机： 10:30-11:30
 * 音乐： 11:00-12:00
 * 显然没法将所有的课都安排在同一间教室上，是有冲突的，那该如何安排呢？
 * 简单粗暴的方法：将所有的集合都遍历一遍，一节课的，两节课的，。。。，然后找出满足条件的最大集合
 * 显然是不可取的，当课程增多一门，时间复杂度可能随指数增加
 * <p>
 * 正确解法：贪婪算法，一种近似算法，只保证尽量贴近最优解
 * 【每次都选取从当前时间开始，结束时间最早的课程】
 *
 * @author 01368080
 * @date 2018/8/8
 */
public class ClassScheduleProblem {

    public static void main(String[] args) {
        List<TimeInterval> timeIntervals = new ArrayList<>(Arrays.asList(new TimeInterval("美术", "09:00", "10:00"),
                new TimeInterval("英语", "09:30", "10:30"),
                new TimeInterval("数学", "10:00", "11:00"),
                new TimeInterval("计算机", "10:30", "11:30"),
                new TimeInterval("音乐", "11:00", "12:00")));
        List<TimeInterval> scheduleResult = classScheduleAlgo(timeIntervals);
        System.out.println(scheduleResult);
    }

    public static List<TimeInterval> classScheduleAlgo(List<TimeInterval> timeIntervals) {
        if (timeIntervals == null || timeIntervals.size() == 0) {
            return new ArrayList<>();
        }
        List<TimeInterval> scheduleResult = new ArrayList<>();
        //一般最好不要对源数据做修改，主要防止外部其他地方调用，导致数据不一致
        List<TimeInterval> copyTimeIntervals = new ArrayList<>(timeIntervals);
        ComparatorSortUtils.sortTimeIntervalByEndTimeAsc(copyTimeIntervals);
        scheduleResult.add(copyTimeIntervals.get(0));
        String preEndTime = copyTimeIntervals.get(0).endTime;
        for (TimeInterval timeInterval : copyTimeIntervals) {
            if (timeInterval.startTime.compareTo(preEndTime) >= 0) {
                scheduleResult.add(timeInterval);
                preEndTime = timeInterval.endTime;
            }
        }
        return scheduleResult;
    }

    static class ComparatorSortUtils {
        public static void sortTimeIntervalByEndTimeAsc(List<TimeInterval> timeIntervals) {
            Collections.sort(timeIntervals, new Comparator<TimeInterval>() {
                @Override
                public int compare(TimeInterval o1, TimeInterval o2) {
                    return o1.endTime.compareTo(o2.endTime);
                }
            });
        }
    }

    static class TimeInterval {
        private String courseName;
        private String startTime;
        private String endTime;

        public TimeInterval(String courseName, String startTime, String endTime) {
            this.courseName = courseName;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"courseName\":\"")
                    .append(courseName).append('\"');
            sb.append(",\"startTime\":\"")
                    .append(startTime).append('\"');
            sb.append(",\"endTime\":\"")
                    .append(endTime).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }
}
