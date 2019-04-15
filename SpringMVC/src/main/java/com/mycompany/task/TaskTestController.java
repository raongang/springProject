package com.mycompany.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 스케쥴러 설정법 
 * @author ykh
 * 
 *  1. root-context와 같은 위치의 xml을 만들고 task namespace를 추가한다
 *  2. component-scan를 통해 스케쥴러를 실행할 패키지나 클래스를 지정한다.
 *  3. <task:annotation-driven/> 을 설정한다.
 *  4. web.xml에서 contextConfigLocation 에 task xml을 설정한다.
 *
 */


@Component
public class TaskTestController {

	/*
	@Scheduled(fixedRate=2000) //수행시작기점 , 2초후 수행
	public void fixedRateTest() {
		System.out.println("fixedRate : 2sec -> " + new Date());
	}*/
	
	/*
	@Scheduled(fixedRate=7000) //수행시작기점 , 7초후 수행
	public void fixedDelayTest() {
		System.out.println("fixedRate : 7sec -> " + new Date());
	}*/
	
	@Scheduled(cron = "0 0/1 14 * * *") // 매일 14시에 1분 간격으로 실행
	public void cronTest() {
		System.out.println("cron : 0 0/1 14 * * * ->" + new Date());
	}	
	
}
