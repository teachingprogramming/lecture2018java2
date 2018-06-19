package d00000.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lect10")
public class Lect10Controller {
    private final JdbcTemplate jdbcTemplate;

    /**
     * コンストラクタ
     */
    @Autowired
    public Lect10Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 掲示板: 表示
     */
    @RequestMapping("/bbs")
    public String bbs(ModelMap modelMap) {
        List<BbsComment> commentList = new ArrayList<>(); // 表示するコメントのリスト
        List<Map<String, Object>> dataList = jdbcTemplate.queryForList("SELECT * FROM bbs_comment"); // データベースから取り出す。
        for (Map<String, Object> data : dataList) {
            BbsComment comment = new BbsComment();
            comment.comment = (String)data.get("comment");
            comment.name = (String)data.get("name");
            comment.date = (Date)data.get("date");
            commentList.add(comment);
        }
        modelMap.addAttribute("commentList", commentList);
        return "lect10/bbs";
    }

    /**
     * 掲示板: コメント処理
     */
    @RequestMapping("/post_comment")
    public String postComment(@RequestParam("comment") String comment, @RequestParam("name") String name) {
        Date date = new Date();
        jdbcTemplate.update("INSERT INTO bbs_comment ( comment , name, date ) VALUES (?, ?, ?)", comment, name, date);
        return "redirect:/lect10/bbs"; // http://localhost:18080/lect10/bbsにリダイレクト
    }
}
