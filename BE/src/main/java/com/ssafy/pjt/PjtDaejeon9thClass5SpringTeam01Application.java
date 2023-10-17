package com.ssafy.pjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy.pjt.member", "com.ssafy.pjt.recent", "com.ssafy.pjt.board", "com.ssafy.pjt.like", "com.ssafy.pjt.house" , "com.ssafy.pjt.univ"})
@MapperScan("com.ssafy.pjt.*.repo")
public class PjtDaejeon9thClass5SpringTeam01Application {

	public static void main(String[] args) {
		SpringApplication.run(PjtDaejeon9thClass5SpringTeam01Application.class, args);
	}

}
