package d00000.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日付
 */
@Controller
@RequestMapping("/date")
public class DateController {

    /** 入力（フォーム） */
    @GetMapping("input")
    public String input() {
        return "date/input";
    }

    /** 処理（フォームから受け取り表示） */
    @PostMapping("process")
    public String process(@RequestParam("date1") String dateString1, @RequestParam("date2") String dateString2, ModelMap modelMap) {
        // 日付1（年月日）
        LocalDate localDate = LocalDate.parse(dateString1, DateTimeFormatter.ISO_DATE); // 文字列をLocalDateに
        Date date1 = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Tokyo")).toInstant()); // LocalDateをDateに
        modelMap.addAttribute("date1", date1);

        // 日付2（年月日+時刻）
        LocalDateTime localDateTime = LocalDateTime.parse(dateString2, DateTimeFormatter.ISO_LOCAL_DATE_TIME); // 文字列をLocalDateTimeに
        Date date2 = Date.from(localDateTime.atZone(ZoneId.of("Asia/Tokyo")).toInstant()); // LocalDateTimeをDateに
        modelMap.addAttribute("date2", date2);

        return "date/process";
    }
}
