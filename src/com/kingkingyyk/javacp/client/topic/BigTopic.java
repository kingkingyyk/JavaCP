package com.kingkingyyk.javacp.client.topic;

import java.util.ArrayList;

public class BigTopic {
	private String title;
	private ArrayList<Topic> topiclist;
	
	public BigTopic(String title) {
		this.title=title;
		this.topiclist=new ArrayList<>();
	}
	
	public String getTitle() { return this.title; }
	
	public void addTopic(Topic t) { this.topiclist.add(t);}
	
	public ArrayList<Topic> getTopicList () { return this.topiclist; }
}
