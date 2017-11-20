package edu.zjgsu.courseapp.trial;

import edu.zjgsu.courseapp.bean.ForumRecord;
import edu.zjgsu.courseapp.service.ForumRecordService;
import edu.zjgsu.courseapp.service.impl.ForumRecordServiceImpl;

public class PlayInsert {

    public static void main(String[] args) {
        ForumRecordService service = new ForumRecordServiceImpl();
        ForumRecord record = new ForumRecord("1",
                "2017-10-10 10:10:10", "Feifei", "Nonono!");
        int effected = service.insert(record);
        System.out.println("Should be 1: " + effected);
    }
}
