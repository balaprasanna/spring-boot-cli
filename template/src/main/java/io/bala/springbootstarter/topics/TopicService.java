package io.bala.springbootstarter.topics;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Singleton Service object...
@Service
public class TopicService {

	@Autowired 
	private TopicRepository topicRepository;
	
	public List<Topic> getAllTopics(){
		return topicRepository.findAll();
	}
	
	public Topic getTopic(Integer id){
		return topicRepository.findOne(id);
	}
	
	public void addTopic(Topic t) {
		topicRepository.save(t);
	}
	
	public void updateTopic(Topic topic, Integer id) {
		topicRepository.save(topic);
	}
	
	public void deleteTopic(Integer id) {
		topicRepository.delete(id);
	}
	
}


/*
 * package io.bala.springbootstarter.topics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Singleton Service object...
@Service
public class TopicService {

	@Autowired 
	private TopicRepository topicRepository;
	
	private List<Topic> topics = new ArrayList<>( Arrays.asList( 
			new Topic(101, "AAPL" , "desc"),
			new Topic(102, "SMNG" , "desc"),
			new Topic(103, "IPHONE" , "desc")
			));
	
	public List<Topic> getAllTopics(){
		return topics;
	}
	
	public Topic getTopic(Integer id){
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}
	
	public void addTopic(Topic t) {
		topics.add(t);
	}
	
	public void updateTopic(Topic topic, Integer id) {
		// TODO: Implemented
		topics.stream().filter(t -> t.getId().equals(id))
			.forEach( f ->  {
				f.setDescription(topic.getDescription());
				f.setId(topic.getId());
				f.setName(topic.getName());
			});
	}
	
	public void deleteTopic(Integer id) {
		topics.removeIf(t -> t.getId().equals(id));
	}
	
}

*/