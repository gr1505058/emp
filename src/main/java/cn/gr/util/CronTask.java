package cn.gr.util;


import cn.gr.service.NwtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时执行
 * */
@Component	
@EnableScheduling 
public class CronTask {
	@Autowired
	private NwtmService ns;
	@Value("${env.name}")
	private String envName;

	@Scheduled(cron="0 0 2 * * ? ")
	public void runDailyCi() {
		System.out.println("envName:"+envName);
	}
}
