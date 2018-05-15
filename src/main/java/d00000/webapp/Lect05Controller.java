package d00000.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/lect05")
public class Lect05Controller {
    public Map<String, String> dictionary;

    public Lect05Controller() {
        dictionary = new HashMap<String, String>();
    }

    @RequestMapping("register_form")
    public String registerForm() {
        return "lect05/register_form";
    }

    @RequestMapping("register_result")
    public String registerResult(@RequestParam("english") String english, @RequestParam("japanese") String japanese) {
        dictionary.put(english, japanese);
        return "lect05/register_result";
    }

    @RequestMapping("search_form")
    public String searchForm() {
        return "lect05/search_form";
    }

    @RequestMapping("search_result")
    public String searchResult(@RequestParam("english") String english, ModelMap modelMap) {
        String japanese = dictionary.get(english); // ない場合はnullが入る
        modelMap.addAttribute("english", english);
        modelMap.addAttribute("japanese", japanese);
        return "lect05/search_result";
    }

    @RequestMapping("list")
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("dictionary", dictionary);
        return "lect05/list";
    }



}
