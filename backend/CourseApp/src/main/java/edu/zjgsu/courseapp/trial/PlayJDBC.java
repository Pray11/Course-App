package edu.zjgsu.courseapp.trial;

import edu.zjgsu.courseapp.bean.ForumRecord;
import edu.zjgsu.courseapp.service.ForumRecordService;
import edu.zjgsu.courseapp.service.impl.ForumRecordServiceImpl;
import edu.zjgsu.courseapp.utils.DBHelper;

import java.util.List;
import java.util.Map;

public class PlayJDBC {

    public static void main0(String[] args) {
        DBHelper dbHelper = new DBHelper();
        String sql = "SELECT * FROM forum;";

        List<Object> result = dbHelper.query(sql, null);
        Map<String, Object> map = (Map<String, Object>) result.get(0);
        System.out.println((String) map.get("comment_by"));
    }

    public static void main(String[] args) {
        ForumRecordService service = new ForumRecordServiceImpl();
        List<ForumRecord> list = service.select("1");
        System.out.println(list.get(0).getCommentTime());
    }
}
