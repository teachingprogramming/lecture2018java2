package d00000.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/lect02")
public class Lect02Controller {

    /**
     * lect02/hello.htmlを表示する
     */
    @RequestMapping("/hello")
    public String hello() {
        return "lect02/hello";
    }

    /**
     * アクセスした回数を保存しておくフィールド
     */
    public int count = 0;

    /**
     * アクセスがあったらカウントし、カウント値をlect02/counter.htmlを使って表示する。
     */
    @RequestMapping("/counter")
    public String counter(ModelMap modelMap) {
        count++;
        modelMap.addAttribute("count", count);
        return "lect02/counter";
    }

    @RequestMapping("/omikuji")
    public String omikuji(ModelMap modelMap) {
        String result; // おみくじの結果
        double rand = Math.random(); // 0以上1未満の乱数
        if (rand < 0.2) {
            result = "凶";
        } else if (rand < 0.8) {
            result = "吉";
        } else {
            result = "大吉";
        }
        modelMap.addAttribute("result", result);
        return "lect02/omikuji";
    }

}
