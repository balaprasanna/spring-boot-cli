package io.bala.springbootstarter.topics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	
	// Do the dependency injection
	@Autowired 
	private TopicService topicService;

	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
	
	// {id} = :id
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable Integer id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic t) {
		topicService.addTopic(t);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public void updateTopic(@RequestBody Topic t, @PathVariable Integer id) {
		topicService.updateTopic(t, id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
	public void deleteTopic(@PathVariable Integer id) {
		topicService.deleteTopic(id);
	}
	
	
//	@RequestMapping(method = RequestMethod.GET, value = "/topics/find/{name}")
//	public List<Topic> getTopicsByName(@PathVariable String name) {
//		return topicRepository.findByName(name);
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/topics/find")
//	public List<Topic> getTopicsByNameQuery(@RequestParam(name = "name") String name) {
//		return topicRepository.findByName(name);
//	}
	
}
