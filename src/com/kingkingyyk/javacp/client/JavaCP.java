package com.kingkingyyk.javacp.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.kingkingyyk.javacp.client.topic.BigTopic;
import com.kingkingyyk.javacp.client.topic.Topic;


public class JavaCP implements EntryPoint {
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final RootPanel RootPane=RootPanel.get();
	private HorizontalPanel ContentPanel;
	private Frame TopicFrame;
	private FlowPanel TopicPanel;
	private FlowPanel TextPanel;
	private ArrayList<BigTopic> BigTopicList=new ArrayList<>();
	private HashMap<Button,BigTopic> BigTopicButtonMap=new HashMap<>();
	private HashMap<Topic,Button> TopicButtonMap=new HashMap<>();
	public HashMap<Button,Boolean> BigTopicButtonExpandStatus=new HashMap<>();

	private Button LastSelectedBigTopicButton;
	
	private Button LastSelectedTopicPanelButton;
	private Frame LastShownTextPanelFrame;
	
	
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
		BigTopic bt=new BigTopic("Java Programming - 1");
		BigTopicList.add(bt);
		bt.addTopic(new Topic("Section 1 - Introduction","/topics/topic01_01.html"));
		bt.addTopic(new Topic("Section 2 - Hello World, again","/topics/topic01_02.html"));
		bt.addTopic(new Topic("Section 3 - Data Types","/topics/topic01_03.html"));
		bt.addTopic(new Topic("Section 4 - Variable","/topics/topic01_04.html"));
		bt.addTopic(new Topic("Section 5 - Going Interactive","/topics/topic01_05.html"));
		bt.addTopic(new Topic("Section 6 - Character & The Naughty String","/topics/topic01_06.html"));
		bt.addTopic(new Topic("Section 7 - Boolean & Conditions","/topics/topic01_07.html"));
		bt.addTopic(new Topic("Section 8 - Code Simplification","/topics/topic01_08.html"));
		bt.addTopic(new Topic("Section 9 - Loop Reduces Length","/topics/topic01_09.html"));
		bt.addTopic(new Topic("Section 10 - Array","/topics/topic01_10.html"));
		bt.addTopic(new Topic("Section 11 - Methods & Functions","/topics/topic01_11.html"));
		bt.addTopic(new Topic("Section 12 - Variable Scoping","/topics/topic01_12.html"));
		bt.addTopic(new Topic("Section 13 - Library","/topics/topic01_13.html"));
		bt.addTopic(new Topic("-- Summary --","/topics/summary01.html"));
		
		bt=new BigTopic("Java Programming - 2");
		BigTopicList.add(bt);
		bt.addTopic(new Topic("Section 1 - Recursion","/topics/topic02_01.html"));
		bt.addTopic(new Topic("Section 2 - Memoization","/topics/topic02_02.html"));
		bt.addTopic(new Topic("Section 3 - Benchmarking","/topics/topic02_03.html"));
	}
	
	public void drawTopicsUI() {
		for (BigTopic bt : BigTopicList) {
			Button btnBT=new Button(bt.getTitle());
			BigTopicButtonMap.put(btnBT,bt);
			BigTopicButtonExpandStatus.put(btnBT, false);
			TopicPanel.add(btnBT);
			btnBT.setStyleName("TopicButtonFrozen");

			btnBT.addClickHandler((event) -> {
				Button currentSelectedButton=(Button)event.getSource();
			
				BigTopicButtonExpandStatus.put(currentSelectedButton,!BigTopicButtonExpandStatus.get(currentSelectedButton));
				for (Topic t : BigTopicButtonMap.get(currentSelectedButton).getTopicList()) {
					TopicButtonMap.get(t).setVisible(BigTopicButtonExpandStatus.get(currentSelectedButton));
				}
			});
			
			for (Topic t : bt.getTopicList()) {
				Button btn=new Button(t.title);
				TopicButtonMap.put(t,btn);
				TopicPanel.add(btn);
				btn.setStyleName("TopicButton");
				btn.setVisible(false);
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
}
