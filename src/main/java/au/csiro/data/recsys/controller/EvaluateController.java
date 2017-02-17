package au.csiro.data.recsys.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.csiro.data.recsys.model.RelatedDataset;
import au.csiro.data.recsys.model.ResponseObject;
import au.csiro.data.recsys.service.EvaluateService;

@Controller
@RequestMapping("/")
public class EvaluateController {

	static final Logger log = Logger.getLogger(EvaluateController.class);

	@Autowired
	EvaluateService evalService;

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.HEAD })
	public ResponseEntity<?> getRoot() {
		return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
	}

	// retrieve related datasets
	@RequestMapping(value = "target/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") String id, Model model,final RedirectAttributes redirectAttributes) {
		ResponseEntity<ResponseObject> resp = evalService.getRelatedDatasets(id);
		if (resp == null) {
			log.debug("Related datasets of " + id + " not found");
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Cannot retrive related datasets. Target dataset is not found in the database. Please check your url.");
		} else {
			model.addAttribute("dataset", resp.getBody().getDataset());
			model.addAttribute("related", resp.getBody().getRelatedDataset());
		}
		return "users/show";

	}

	// -------------------Add
	// Evaluation--------------------------------------------------------
	@RequestMapping(value = "add/{target}", method = RequestMethod.POST)
	public String saveEvaluation(@PathVariable("target") String target, HttpServletRequest request,final RedirectAttributes redirectAttributes) {
		log.debug("Fetching evaluation results of " + target);
		ResponseEntity<? extends Object> responseCode = null;
		HashMap<Long, String> hmap = new HashMap<Long, String>();
		String evaluator = request.getParameter("email");
		String comment = request.getParameter("comment");
		Enumeration<String> attrs = request.getParameterNames();
		List<String> list = Collections.list(attrs);
		list.remove("email");
		list.remove("comment");
		for (String element : list) {
				String radio = request.getParameter(element);
				hmap.put(Long.parseLong(element), radio);
		}
		responseCode = evalService.saveEvaluation(evaluator,target, hmap, comment);
		
		if (responseCode.getStatusCode() == HttpStatus.CREATED)
		{
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Your evaluation has been submitted successfully! Thank you.");
		}
		else
		{
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Data insertion error.");
		}
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;

	}

	private void parseJson(String jsonString) {
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = null;
		try {
			rootNode = mapper.readTree(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.getFields();
		while (fieldsIterator.hasNext()) {

			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
		}
	}

}