package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) throws InterruptedException {

        TraceStatus status = null;
        try{
            status = trace.begin("OrderControllerV1.request");
            orderService.orderItem(itemId);
            trace.end(status);
            return "OK";

        }catch(Exception e){
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야한다.
        }

    }
}
