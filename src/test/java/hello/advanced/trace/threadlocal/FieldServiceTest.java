package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");

        Runnable userA = () ->{
            try {
                fieldService.logic("userA");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable userB = () ->{
            try {
                fieldService.logic("userB");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread threadA = new Thread(userA);
        threadA.setName("threadA");
        Thread threadB = new Thread(userB);
        threadB.setName("threadB");

        threadA.start();
//        sleep(2000); //동시성 문제 발생 X
        sleep(100);
        threadB.start();

        sleep(2000); //메인 쓰레드 종료 대기
        log.info("main end");
    }

    private void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
