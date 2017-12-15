package schedule;

import dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by nick on 2017/12/14.
 */
@Component
public class Scheduler {
    @Autowired
    private ReportDao reportDao;

    @Scheduled(cron = "1 0 0 * * ?")
    void doSomethingWith() {
        //查找1年和2年的分别更新
        reportDao.updateQuitStatus();
    }
}
