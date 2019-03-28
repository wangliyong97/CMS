import com.cms.pojo.Subscribe;
import com.cms.service.SubscribeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangliyong on 2019/3/28.
 */
public class SubscribeActivityListTest {
    @Autowired
    private SubscribeService subscribeService;

    @Test
    public void SubscribeActivityListTest(){
        List<?> result = subscribeService.selectSubscribeActivityList(9);
        System.out.println(result.toString());
    }
}
