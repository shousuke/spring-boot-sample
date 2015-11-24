package entpay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "dashboard")
public class DashboardController {
	// private static final Logger log = Logger.getLogger("debugLogger");
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		return new ModelAndView("redirect:/dashboard/stats/settlements");
	}

	@RequestMapping(value = "stats/settlements", method = RequestMethod.GET)
    public ModelAndView settlementStat() {
        Map<String, Object> model = new HashMap<>();
        model.put("pageId", "statsSettlements");
        model.put("subTitle", "结算统计");
        
        return new ModelAndView("dashboard", model);
    }
	
	@RequestMapping(value = "stats/subsidies", method = RequestMethod.GET)
    public ModelAndView subsidyStat() {
        Map<String, Object> model = new HashMap<>();
        model.put("pageId", "statsSubsidies");
        model.put("subTitle", "补贴统计");
        
        return new ModelAndView("dashboard", model);
    }
	
	@RequestMapping(value = "stats/cpos", method = RequestMethod.GET)
    public ModelAndView cposStat() {
        Map<String, Object> model = new HashMap<>();
        model.put("pageId", "statsCpos");
        model.put("subTitle", "CPOS统计");
        
        return new ModelAndView("dashboard", model);
    }
}
