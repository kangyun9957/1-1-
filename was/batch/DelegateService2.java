package com.example.demo.batch2;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
public class DelegateService2<V> implements Runnable {
	private List<V> dtoList;
	private BatchThreadService2<V> batchThreadService;


	@Override
	public void run() {

		batchThreadService.execute(dtoList);


	}

}
