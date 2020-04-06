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
		String resultVJA = restTemplate.getForObject("http://vja-gateway/vja/tickets/", String.class);
		String resultVNA = restTemplate.getForObject("http://vna-gateway/vna/tickets/", String.class);
		try {
			//lay du lieu server VJA
			Object objVJA = new JSONParser().parse(resultVJA);
			JSONObject obVJA = (JSONObject) objVJA;
			JSONArray arrVJA = (JSONArray) obVJA.get("tickets");
			JSONObject vja = new JSONObject();
			vja.put("vja", arrVJA);
			
			//lay du lieu server VNA
			Object objVNA = new JSONParser().parse(resultVNA);
			JSONObject obVNA = (JSONObject) objVNA;
			JSONArray arrVNA = (JSONArray) obVNA.get("tickets");
			JSONObject vna = new JSONObject();
			vna.put("vna", arrVNA);
			
			JSONArray kk = new JSONArray();
			kk.add(vja);
			kk.add(vna);
			gallery.setTickets((List<Object>) kk);
			return gallery;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/tickets/{plane}")
	public Gallery getGallery(@PathVariable String plane) {
		// create gallery object
		Gallery gallery = new Gallery();
		if(plane.equals("vna")) {
			String resultVNA = restTemplate.getForObject("http://vna-gateway/vna/tickets/", String.class);
			try {
				
			//lay du lieu server VNA
				Object objVNA = new JSONParser().parse(resultVNA);
				JSONObject obVNA = (JSONObject) objVNA;
				JSONArray arrVNA = (JSONArray) obVNA.get("tickets");	
				gallery.setTickets((List<Object>) arrVNA);
				return gallery;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(plane.equals("vja")) {
			String resultVJA = restTemplate.getForObject("http://vja-gateway/vja/tickets/", String.class);
			try {
				//lay du lieu server VJA
				Object objVJA = new JSONParser().parse(resultVJA);
				JSONObject obVJA = (JSONObject) objVJA;
				JSONArray arrVJA = (JSONArray) obVJA.get("tickets");
				gallery.setTickets((List<Object>) arrVJA);
				return gallery;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	
	@RequestMapping("/{ngaydi}/{ngayve}/{noidi}/{noiden}/{nguoilon}/{treem}/{embe}")
	public Gallery getGallery(@PathVariable String ngaydi, @PathVariable String ngayve, @PathVariable String noidi, @PathVariable String noiden, @PathVariable String nguoilon,
			@PathVariable String treem, @PathVariable String embe) {
		Gallery gallery = new Gallery();
		String resultVJA = restTemplate.getForObject("http://vja-gateway/vja/"+ngaydi+"/"+ngayve+"/"+noidi+"/"+noiden+"/"+nguoilon+"/"+treem+"/"+embe, String.class);
		String resultVNA = restTemplate.getForObject("http://vna-gateway/vna/"+ngaydi+"/"+ngayve+"/"+noidi+"/"+noiden+"/"+nguoilon+"/"+treem+"/"+embe, String.class);
		try {
			//lay du lieu server VJA
			Object objVJA = new JSONParser().parse(resultVJA);
			JSONObject obVJA = (JSONObject) objVJA;
			JSONArray arrVJA = (JSONArray) obVJA.get("tickets");
			JSONObject vja = new JSONObject();
			vja.put("vja", arrVJA);
			
			//lay du lieu server VNA
			Object objVNA = new JSONParser().parse(resultVNA);
			JSONObject obVNA = (JSONObject) objVNA;
			JSONArray arrVNA = (JSONArray) obVNA.get("tickets");
			JSONObject vna = new JSONObject();
			vna.put("vna", arrVNA);
			
			JSONArray kk = new JSONArray();
			kk.add(vja);
			kk.add(vna);
			gallery.setTickets((List<Object>) kk);
			return gallery;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}