package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) throws InterruptedException {

        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepository.save()");
            if (itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }

            sleep(1000);
            trace.end(status);

        }catch(Exception e){
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야한다.
        }


    }
    private void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
