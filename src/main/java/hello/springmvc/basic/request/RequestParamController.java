package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * [Input data]
 * ?username=nick&age=12
 */
@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //RestController 와 같은 효과
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 변수명 같으면 매핑 생략가능
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        // String, int, Integer 등의 단순 타입이면 @RequestParam 도 생략가능
        // 하지만 명확하지 않으므로 @RequestParam 정도는 명시를 해주는것을 권장
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // true 일경우, 없으면 오류
            @RequestParam(required = false) Integer age) { // false 일 경우 optional
        // int 에 null 이 들어갈 수 없으므로 500 에러 발생 따라서 Wrapper class 설정
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        // default 가 있는 경우 required 는 필요없게됨
        // null 이아닌 빈문자 "" 일경우에도 설정이됨
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        //요청 다양하게 조회 (모든 것 조회)
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
    /*
    @RequestParam Map
        Map(key=value)
    @RequestParam MultiValueMap
        MultiValueMap(key=[value1, value2, ...]
        ex) (key=userIds, value=[id1, id2])

        /?userIds=1&userIds=id2
     */

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
//        HelloData helloData = new HelloData();
//        helloData.setAge(age);
//        helloData.setUsername(username);

        // ModelAttribute 를 사용함으로서 위 내용들을 생략가능
        // ModelAttribute 가 있으면
        // 1. HelloData 객체를 생성
        // 2. 요청 파라미터의 이름으로 HelloData 객체 프로퍼티를 찾고, 프로퍼티의 Setter 를 호출해서 값을 입력(바인딩) 한다
        // 파라메터 이름이 username 이면 setUsername() 호출

        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());
        log.info("helloData={}, ",helloData);

        return "ok";
    }


    //ModelAttribute 생략가능
    // => RequestParam 과 ModelAttribute 구분?
    // String, int, Integer 같은 단순 타입 => @RequestParam
    // 그 외 => ModelAttribute ( argument resolver 로 지정해둔 타입 외 HttpServletRequest 같은 것들)
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){

        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());
        log.info("helloData={}, ",helloData);

        return "ok";
    }

}
