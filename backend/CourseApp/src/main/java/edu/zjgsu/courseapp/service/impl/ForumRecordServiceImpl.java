package edu.zjgsu.courseapp.service.impl;

import edu.zjgsu.courseapp.bean.ForumRecord;
import edu.zjgsu.courseapp.dao.BaseDao;
import edu.zjgsu.courseapp.dao.impl.BaseDaoImpl;
import edu.zjgsu.courseapp.service.ForumRecordService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ForumRecordServiceImpl implements ForumRecordService {

    @Override
    public int delete(ForumRecord forumRecord) {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM " + TABLE_NAME + " WHERE");
        // colume 1, course_id
        sql.append(" course_id = " + forumRecord.getCourseId());
        // column 2, comment_time
        sql.append(" AND comment_time = \"" + forumRecord.getCommentTime() + "\"");
        // column 3, comment_by
        sql.append(" AND comment_by = \"" + forumRecord.getCommentBy() + "\"");
        // column 4, content
        sql.append(" AND content = \"" + forumRecord.getContent() + "\"");
        sql.append(";");
        return dao.update(sql.toString(), null);
    }

    @Override
    public int insert(ForumRecord forumRecord) {
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO " + TABLE_NAME + " VALUES(");
        sql.append("'" + forumRecord.getCourseId() + "', ");
        sql.append("str_to_date('" + forumRecord.getCommentTime()
                + "', '%Y-%m-%d %H:%i:%S'), ");
        sql.append("'" + forumRecord.getCommentBy() + "', ");
        sql.append("\"" + forumRecord.getContent() + "\");");
        return dao.update(sql.toString(), null);
    }

    @Override
    public List<ForumRecord> select(String courseId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE course_id = " + courseId
                + " ORDER BY comment_time DESC;";
        List<Object> objectList = dao.query(sql, null);
        List<ForumRecord> result = new ArrayList<>();
        for (Object object : objectList) {
            ForumRecord record = new ForumRecord();
            Map<String, Object> map = (Map<String, Object>) object;
            record.setCourseId((String) map.get("course_id"));
            record.setCommentBy((String) map.get("comment_by"));
            record.setContent((String) map.get("content"));

            Date date = new Date();
            date.setTime(((Timestamp) map.get("comment_time")).getTime());
            record.setCommentTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
            result.add(record);
        }
        return result;
    }

    BaseDao dao = new BaseDaoImpl();

    private static final String TABLE_NAME = "test_forum";
}
