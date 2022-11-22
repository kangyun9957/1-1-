package com.example.demo.batch2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.InsertBatchMapper;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class BatchThreadService2<V> {

	@Autowired
	InsertBatchMapper<V> insertBatchMapper;

	@Autowired
	EmployeeMapper employeeMapper;

	public void execute(List<V> dtoList) {

		int a = insertBatchMapper.insertBatch(dtoList);

	}

	public void execute2(List<V> dtoList) {
		employeeMapper.selectTest();
		//int a = insertBatchMapper.insertBatch(dtoList);

	}
}
