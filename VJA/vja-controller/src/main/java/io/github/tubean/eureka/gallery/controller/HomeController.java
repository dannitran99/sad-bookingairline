package io.github.tubean.eureka.gallery.controller;

import io.github.tubean.eureka.gallery.entity.Gallery;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
	}
  
	@RequestMapping("/tickets")
	public Gallery getGallery() {
		// create gallery object
		Gallery gallery = new Gallery();
		
		// get list of available images 
		List<Object> tickets = restTemplate.getForObject("http://vja-ticket-service/ticket/", List.class);
		gallery.setTickets(tickets);
		
		return gallery;
	}
	@RequestMapping("/{ngaydi}/{ngayve}/{noidi}/{noiden}/{nguoilon}/{treem}/{embe}")
	public Gallery getGallery(@PathVariable String ngaydi, @PathVariable String ngayve, @PathVariable String noidi, @PathVariable String noiden, @PathVariable String nguoilon,
			@PathVariable String treem, @PathVariable String embe) {
		Gallery gallery = new Gallery();
		JSONObject timVe = new JSONObject();
		List<Object> vedi = new ArrayList<>();
		List<Object> veve = new ArrayList<>();
		String result = restTemplate.getForObject("http://vja-ticket-service/ticket/", String.class);
		try {
			Object obj = new JSONParser().parse(result);
			JSONArray ja = (JSONArray) obj;
			for (int i = 0; i < ja.size(); i++) {
				JSONObject tmp = (JSONObject) ja.get(i);
				if(tmp.get("flightFrom").equals(noidi) && tmp.get("flightTo").equals(noiden) && tmp.get("date").toString().equals(ngaydi)) {
					vedi.add(tmp);
				}
				if(tmp.get("flightFrom").equals(noiden) && tmp.get("flightTo").equals(noidi) && tmp.get("date").toString().equals(ngayve)) {
					veve.add(tmp);
				}
			}
			timVe.put("veDi", vedi);
			timVe.put("veVe", veve);
			JSONArray kk = new JSONArray();
			kk.add(timVe);
			List<Object> ve = (List<Object>) kk;
			gallery.setTickets(ve);
			return gallery;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}