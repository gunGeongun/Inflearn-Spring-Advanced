package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static java.lang.Thread.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) throws InterruptedException {
        if (itemId.equals("ex")){
            throw new IllegalStateException("예외 발생!");
        }

        sleep(1000);
    }
    private void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
