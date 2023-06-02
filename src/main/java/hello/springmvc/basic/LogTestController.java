package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        String name2 = "JAVA";

//        System.out.println("name = " + name);

            // 옳바르지 않은 사용법 이 trace 로그가 사용되지 않을때에도,
        // log + name 이 치환되는 연산이 수행된다.
//        log.trace(" trace log= " + name);

        log.trace(" trace log={}, {}",name, name2);
        log.debug(" debug log={}",name);


        log.info(" info log={}",name);

        log.warn(" warn log={}",name);
        log.error(" error log={} ", name);
        return "ok";

    }
}
