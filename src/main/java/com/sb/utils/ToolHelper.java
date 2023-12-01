package com.sb.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ToolHelper {

    public static void printThreads() {
        // 打印当前的线程信息
        Collection<Thread> threads = ThreadUtils.getAllThreads();
        List<String> list = new ArrayList<>(128);
        //list.add("Dump Date: " + Constants.getDateStrNow());
        list.add("------------------ thread dump ------------------");
        int threadCount = 0;
        for (Thread t : threads) {
            String groupName = t.getThreadGroup() == null? "null" : t.getThreadGroup().getName();
            list.add(String.format(Locale.US, "%-15s \t %-15s \t %-15d \t %s \t %-5d \t %-15s\n",
                    t.getName(), t.getState(), t.getPriority(), t.isDaemon(), t.getId(), groupName));
            threadCount++;
        }
        list.add("------------------ total count: " + threadCount + " ------------------");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
