package edu.zjgsu.courseapp.trial;

import edu.zjgsu.courseapp.bean.ForumRecord;
import edu.zjgsu.courseapp.service.ForumRecordService;
import edu.zjgsu.courseapp.service.impl.ForumRecordServiceImpl;

import java.util.List;

public class PlayClearDB {

    public static void main(String[] args) {
        ForumRecordService service = new ForumRecordServiceImpl();
        List<ForumRecord> list = service.select("1");
        for (ForumRecord forumRecord : list)
            service.delete(forumRecord);
        System.out.println("Done, having deleted " + list.size());
        //int effected = service.insert(record);
        //System.out.println("Should be 1: " + effected);
    }
}
