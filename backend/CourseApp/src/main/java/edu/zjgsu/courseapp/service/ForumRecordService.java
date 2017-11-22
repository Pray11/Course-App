package edu.zjgsu.courseapp.service;

import edu.zjgsu.courseapp.bean.ForumRecord;

import java.util.List;

public interface ForumRecordService {

    List<ForumRecord> select(String courseId);

    int insert(ForumRecord forumRecord);

    int delete(ForumRecord forumRecord);
}
