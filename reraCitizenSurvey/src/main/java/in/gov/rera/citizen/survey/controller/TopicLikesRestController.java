package in.gov.rera.citizen.survey.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import in.gov.rera.citizen.survey.common.model.ResponseModel;
import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;
import in.gov.rera.citizen.survey.model.AllotteeForumTopicModel;
import in.gov.rera.citizen.survey.model.TopicLikesModel;
import in.gov.rera.citizen.survey.model.TopicReplyModel;
import in.gov.rera.citizen.survey.security.AuthSecurity;
import in.gov.rera.citizen.survey.security.AuthUser;
import in.gov.rera.citizen.survey.services.AllotteeTopicService;
import in.gov.rera.citizen.survey.services.TopicLikesService;
import in.gov.rera.citizen.survey.services.TopicReplyService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/likes")
public class TopicLikesRestController {
	private static final Logger logger = LogManager.getLogger(TopicLikesRestController.class);

	@Autowired
	TopicLikesService likeServ;

	@Autowired
	Environment env;

	@GetMapping("/all-likes{topicId}")
	public ResponseEntity<?> getAllTopicLikes(@PathVariable(value = "topicId") Long topicId)
			throws ResourceNotFoundException, IOException, ParseException {
		List<TopicLikesModel> likesList = new ArrayList<TopicLikesModel>();
		likesList = likeServ.findByTopicId(topicId);
		Optional.of(likesList).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(likesList);
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveTopicLikes(@RequestBody TopicLikesModel likesModel, HttpServletRequest req) throws ResourceNotFoundException{
		    
		Optional.ofNullable(likesModel).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		    AuthUser user = (AuthUser) req.getAttribute(AuthSecurity.AUTH_USER_ATTR);
		    List<TopicLikesModel> likesList=likeServ.findByTopicId(likesModel.getTopicId());
		    if(likesList.size()>0) {
		    int check=0;
		    for(TopicLikesModel m:likesList)
		    {
		    	if(m.getTopicId().equals(likesModel.getTopicId()) && m.getLikedById().equals(user.getUserId()) && m.getLikedByType().equals(user.getUserType()))
		    	{
		    		check=1;
		    	}
		    }
		    if(check==0 && check!=1)
		    {
		    	likesModel.setLikedById(user.getUserId());
	    		likesModel.setLikedByType(user.getUserType());
	    		likesModel.setLikedByMobileNo(user.getMobile());
	    		likesModel.setLikedByName(user.getUserName());
	    		likesModel.setLikedByEmailId(user.getEmail());
	    		likesModel = likeServ.saveTopicLikes(likesModel);
		    }}
		    else
		    {
		    	likesModel.setLikedById(user.getUserId());
	    		likesModel.setLikedByType(user.getUserType());
	    		likesModel.setLikedByMobileNo(user.getMobile());
	    		likesModel.setLikedByName(user.getUserName());
	    		likesModel.setLikedByEmailId(user.getEmail());
	    		likesModel = likeServ.saveTopicLikes(likesModel);
		    }
		    ResponseModel rs = new ResponseModel();
			rs.setMessage("SUCCESS");
			rs.setStatus("200");
			rs.setData(likesModel);
		return ResponseEntity.ok().body(rs);
	}
}
