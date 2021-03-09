package com.longpc.dto;

import java.io.Serializable;

public class ResultQuizDTO implements Serializable{
	private String name;
	private int total;
	private int numRight;
	private double score;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getNumRight() {
		return numRight;
	}
	public void setNumRight(int numRight) {
		this.numRight = numRight;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
