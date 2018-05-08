package d00000.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/lect04")
public class Lect04Controller {

    /**
     * コメント文字列を格納するリスト
     */
    public List<String> commentList;

    /**
     * Lect04Controllerのコンストラクタ
     */
    public Lect04Controller() {
        this.commentList = new ArrayList<String>();
    }

    /**
     * 掲示板（コメントと入力フォーム）の表示
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/bbs")
    public String bbs(ModelMap modelMap) {
        modelMap.addAttribute("commentList", commentList);
        return "lect04/bbs";
    }

    /**
     * フォームからpostされてきたコメントの処理
     *
     * @param comment
     * @return
     */
    @RequestMapping("/add_comment")
    public String addComment(@RequestParam("comment") String comment) {
        this.commentList.add(comment);
        return "lect04/add_comment";
        // return "redirect:/lect04/bbs";
    }

    // 以下は名前と投稿時刻を加えた掲示板 -----------------------------------------------------

    public List<BbsComment> commentList2 = new ArrayList<>();

    @RequestMapping("/bbs2")
    public String bbs2(ModelMap modelMap) {
        modelMap.addAttribute("commentList", commentList2);
        return "lect04/bbs2";
    }

    @RequestMapping("/add_comment2")
    public String addComment2(@RequestParam("comment") String comment, @RequestParam("name") String name) {
        BbsComment bbsComment = new BbsComment();
        bbsComment.comment = comment;
        bbsComment.name = name;
        bbsComment.date = new Date();
        this.commentList2.add(bbsComment);
        return "redirect:/lect04/bbs2";
    }


}
