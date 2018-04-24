package d00000.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lect03")
public class Lect03Controller {

    @RequestMapping("/bmi_input")
    public String bmiInput() {
        return "lect03/bmi_input";
    }

    @RequestMapping("/bmi_output")
    public String bmiOutput(@RequestParam("height") String height, @RequestParam("weight") String weight, ModelMap modelMap) {
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        double bmi = w / ((h * 0.01) * (h * 0.01));
        modelMap.addAttribute("bmi", bmi);
        return "lect03/bmi_output";
    }

    @GetMapping("/bmi_input_wo_action")
    public String bmiGet() {
        return "lect03/bmi_input_wo_action";
    }

    @PostMapping("/bmi_input_wo_action")
    public String bmiPost(@RequestParam("height") String height, @RequestParam("weight") String weight, ModelMap modelMap) {
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        double bmi = w / ((h * 0.01) * (h * 0.01));
        modelMap.addAttribute("bmi", bmi);
        return "lect03/bmi_output";
    }

}
