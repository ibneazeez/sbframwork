package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.dao.BasicDAO;
@CrossOrigin("http://127.0.0.1:8999")
@Controller
@RequestMapping("/")
public class AddCalcController {
	
	@Autowired
	BasicDAO basicDAO;
	
    @RequestMapping(value = "add/{a}/{b}",method = RequestMethod.GET)
    public @ResponseBody Integer add(@PathVariable("a") Integer a,@PathVariable("b") Integer b) {
    	basicDAO.saveCalc(a, b, a+b);
        return a+b;
    }

}
