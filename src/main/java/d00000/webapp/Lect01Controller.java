package d00000.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lect01")
public class Lect01Controller {

    /**
     * 「こんにちは」と表示する
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "こんにちは";
    }

    /**
     * アクセスした回数を保存しておくフィールド
     */
    public int count = 0;

    /**
     * アクセスがあったらカウントし、それを表示する。
     */
    @RequestMapping("/counter")
    @ResponseBody
    public String counter() {
        count++;
        return "" + count;
    }

}
