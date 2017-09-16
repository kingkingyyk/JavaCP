package com.kingkingyyk.javacp.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.kingkingyyk.javacp.client.topic.Topic;


public class JavaCP implements EntryPoint {
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final RootPanel RootPane=RootPanel.get();
	private HorizontalPanel ContentPanel;
	private FlowPanel TopicPanel;
	private Button LastSelectedTopicPanelButton;
	private FlowPanel TextPanel;
	private Frame LastShownTextPanelFrame;
	private ArrayList<Topic> TopicList=new ArrayList<>();

	public void onModuleLoad() {
		RootPane.setStyleName("Root");
		initializeLayout();
		initializeTopics();
		drawTopicsUI();
	}
	
	public void initializeLayout() {
		HorizontalPanel header=new HorizontalPanel();
		RootPane.add(header);
		header.setStyleName("HeaderPanel");
		
		Label lblHeader=new Label("Java : From 0 to Competitive Programming");
		lblHeader.setStyleName("HeaderPanelText");
		header.add(lblHeader);
		
		HorizontalPanel bodyPanel=new HorizontalPanel();
		RootPane.add(bodyPanel);
		bodyPanel.setStyleName("BodyPanel");
		
		ContentPanel=new HorizontalPanel();
		ContentPanel.setStyleName("ContentPanel");
		bodyPanel.add(ContentPanel);
		
		TopicPanel=new FlowPanel();
		TopicPanel.setStyleName("TopicPanel");
		ContentPanel.add(TopicPanel);
		
		TextPanel=new FlowPanel();
		TextPanel.setStyleName("TextPanel");
		ContentPanel.add(TextPanel);
	}
	
	public void initializeTopics() {
		TopicList.add(new Topic("Section 1 - Introduction To Programming","/topics/topic1.html"));
		TopicList.add(new Topic("Section 2 - Hello World, again","/topics/topic2.html"));
		TopicList.add(new Topic("Section 3 - Data Types","/topics/topic3.html"));
	}
	
	public void drawTopicsUI() {
		for (Topic t : TopicList) {
			Button btn=new Button(t.title);
			btn.setStyleName("TopicButton");
			TopicPanel.add(btn);
			
			btn.addClickHandler((event) -> {
				if (LastSelectedTopicPanelButton!=btn) {
					btn.setStyleName("TopicButtonSelected");
					if (LastSelectedTopicPanelButton!=null) {
						LastSelectedTopicPanelButton.setStyleName("TopicButton");
					}
					LastSelectedTopicPanelButton=btn;
					
					
					if (LastShownTextPanelFrame!=null) TextPanel.remove(LastShownTextPanelFrame);
					
					Frame f=new Frame(t.content);
					f.setStyleName("ContentFrame");
					TextPanel.add(f);
					
					LastShownTextPanelFrame=f;

				}
			});
		}
	}
}
