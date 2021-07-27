package com.kh.spring.memo.model.vo;

import java.util.Date;

import lombok.Data;
@Data
public class Memo {
	private int memoNo;
	private String Memo;
	private int password;
	private Date memoDate;
}
