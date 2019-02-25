package com.zxb.structurealgo.greedy;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @ClassName MaxSectionCount
 * @Description 给定多个区间，求不相交的区间的最大个数，边界值相等不算相交
 * <p>
 * 先按左边界从小到大排序，然后取右边界最小的一个区间，之后选择左边界【大于等于之前所选区间的右区间】
 * 中的右边界最小值的区间
 * 并重复上述过程
 * @Author xuery
 * @Date 2019/2/25 21:07
 * @Version 1.0
 */
public class MaxSectionCount {
    public static void main(String[] args) {
        List<Section> sections = Arrays.asList(new Section(6,8),
                new Section(2,4),
                new Section(3,5),
                new Section(1,5),
                new Section(5,9),
                new Section(8,10));
        MaxSectionCount maxSectionCount = new MaxSectionCount();
        List<Section> sections1 = maxSectionCount.findMaxSectionCount(sections);
        for(Section section: sections1){
            System.out.println(section);
        }
    }

    static class Section {
        int left;
        int right;

        public Section(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Section{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public List<Section> findMaxSectionCount(List<Section> sections) {
        Collections.sort(sections, new Comparator<Section>() {
            @Override
            public int compare(Section o1, Section o2) {
                return o1.left - o2.left;
            }
        });

        List<Section> sectionResList = new ArrayList<>();
        int startIndex = 0;
        while (startIndex < sections.size()) {
            //每次从startIndex开始查找下一个符合上述条件，section表示排序后的第一个区间或者上一个满足条件的
            Section section = sectionResList.size() == 0 ?
                    sections.get(0) : sectionResList.get(sectionResList.size() - 1);
            Section tmpSection = sectionResList.size() == 0 ?
                    sections.get(0) : null;
            int tmpIndex = 0;
            for (int i = startIndex; i < sections.size(); i++) {
                if (sectionResList.size() == 0) {
                    if (sections.get(i).right < section.right) {
                        tmpSection = section = sections.get(i);
                        tmpIndex = i;
                    }
                } else {
                    if (sections.get(i).left >= section.right) {
                        if (tmpSection == null || sections.get(i).right < tmpSection.right) {
                            tmpSection = sections.get(i);
                            tmpIndex = i;
                        }
                    }
                }
            }
            if (tmpSection == null) {
                break;
            }
            sectionResList.add(tmpSection);
            startIndex = tmpIndex + 1;
        }

        return sectionResList;
    }
}
