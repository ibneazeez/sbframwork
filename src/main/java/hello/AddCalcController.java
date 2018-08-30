package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.dao.BasicDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/")
@Api(value="" ,description="Micro Calculator - Add Service" , consumes="application/json")
public class AddCalcController {
	
	@Autowired
	BasicDAO basicDAO;


@ApiOperation(value = "Add two values and save the data in Cal_HIstory Table and return sum of the values", response = Iterable.class)
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully values added to database and return value successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
}
)
	
    @RequestMapping(value = "add/{a}/{b}",method = RequestMethod.GET)
    public @ResponseBody Integer add(@ApiParam(name="a" , value="This is a first Opperand" ) @PathVariable("a") Integer a,@ApiParam(name="b" , value="This is a second Opperand" ) @PathVariable("b") Integer b) {
    	basicDAO.saveCalc(a, b, a+b);
        return   a+b;
    }

}
