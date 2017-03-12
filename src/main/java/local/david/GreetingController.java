package local.david;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by [david] on 12.03.17.
 */
@Controller
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        logger.warn("AAAAAAAAAAa");
        model.addAttribute("name", name);
        return "greeting";
    }

}