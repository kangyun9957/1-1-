package com.example.demo.batch2;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DtoTakerThread2<V> implements Runnable{
	String serviceId;
	int batchSize = 16;
	boolean isRunning = true;
	private static final int POLL_TIMEOUT = 500;

	BatchThreadService2<V> batchService;
	BlockingQueue<V> dataQueue = new LinkedBlockingQueue<>();
	BlockingQueue<Runnable> threadQueue = new LinkedBlockingQueue<>();

	ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(
			Runtime.getRuntime().availableProcessors(),
			Runtime.getRuntime().availableProcessors(),
			1,
			TimeUnit.MILLISECONDS,
			threadQueue,
			Executors.defaultThreadFactory());

	public DtoTakerThread2(BlockingQueue<V> dataQueue,BatchThreadService2<V> batchService) {
		this.dataQueue = dataQueue;
		this.batchService=batchService;
		this.serviceId = batchService.getClass().getSimpleName();
		Executors.newFixedThreadPool(1).execute(this);
	}


	public void assignJob(List<V> dtoList) {
		log.info("리스트의 길이 : {} , {}", dtoList.size(),threadExecutor.getActiveCount());

		threadExecutor.execute(new DelegateService2(dtoList,batchService));

	}

	public void stop() {
		isRunning=false;
	}

	@Override
	public void run() {
		log.info(">>> Start DtoTakerThread:{}.....", serviceId);
		V dto;
		List<V> dtoList = new ArrayList<>();
		int currentSize = 0;

//		while(!dataQueue.isEmpty()) {
//				dto = dataQueue.poll();
//				dtoList.add(dto);
//		}
//		assignJob(dtoList);
		while(isRunning) {
			try {
				if (currentSize == 0) {
                    dtoList = new ArrayList<>();
                }

                dto = dataQueue.poll(POLL_TIMEOUT, TimeUnit.MILLISECONDS);


                if (dto != null) {

                    currentSize++;
                    dtoList.add(dto);
                } else if (currentSize > 0) {
                    currentSize = 0;

                    assignJob(dtoList);
                    continue;
                }

                if (currentSize >= batchSize) {
                    currentSize = 0;


                    assignJob(dtoList);
                }

			}catch (InterruptedException e) {
                if (currentSize > 0) {
                    currentSize = 0;
                    assignJob(dtoList);
                }

                isRunning = false;
                log.error("DtoTakerThread:" + serviceId + " Assign error : ", e);
                Thread.currentThread().interrupt();
            }
		}
		log.info("<<< Stop DtoTakerThread : {}.....", serviceId);


	}

}
