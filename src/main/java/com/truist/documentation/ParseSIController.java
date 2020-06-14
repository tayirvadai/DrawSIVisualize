package com.truist.documentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.runners.model.FrameworkMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.truist.integration.io.model.GraphExtension;
import com.truist.integration.io.model.IntgLinkNode;
import com.truist.integration.io.model.IntgNode;
import com.truist.integration.io.model.IntgTreeNode;

@RestController
@RequestMapping("/getPlantUmlCode")
public class ParseSIController {

	private Map<Integer, String> unwantedNode = new HashMap<Integer,String>();
	private List<IntgLinkNode> linksToTrace = new ArrayList<IntgLinkNode>();
	private Set<IntgLinkNode> fromGoodNodes = new HashSet<IntgLinkNode>();
	private Set<IntgLinkNode> toGoodNodes = new HashSet<IntgLinkNode>();
	private Map<Integer,Integer> fromToMap = new HashMap<Integer,Integer>();
	private Map<Integer,Integer> backupFromToMap = new HashMap<Integer,Integer>();
	
	@GetMapping
	public ResponseEntity<String> parseSpringIntegrationOutput() {

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:9090/integration";
		System.out.println(" Integration data received  " + fooResourceUrl);
		ResponseEntity<GraphExtension> response = restTemplate.getForEntity(fooResourceUrl, GraphExtension.class);
		StringBuilder sb = new StringBuilder();

		GraphExtension graph = response.getBody();

		sb.append("@startuml\n");
		sb.append("interface request\n");
		sb.append("interface end\n");
		for (IntgNode nd : graph.getNodes()) {
			if (nd.getName().contains(".channel#")) {
			//	System.out.println(" Unwanted Node " + nd.getNodeId() + " " + nd.getName());
				unwantedNode.put(nd.getNodeId(), "0,0");
			} else {
				// System.out.println(" Wanted Node " + nd.getNodeId() + " " + nd.getName());
				String imageType = "rectangle";
				String imageIcon = "";
				switch (nd.getIntegrationPatternType()) {
				case  "message_channel":
					imageType = "queue";
					break;
				case "inbound_gateway":
					imageType = "boundary";
					break;
				case "bridge" :
					imageType = "stack";
					break;
				case "transformer" :
					imageType = "artifact";
					break;
				case "publish_subscribe_channel" :
					imageType = "queue";
					break;
				default:
					imageType = "rectangle";
				}
		
				sb.append(imageType + " " + nd.getNodeId() + " [ <b>" + nd.getNodeId() + "::" + nd.getName().replace("org.springframework.integration.config.ConsumerEndpointFactoryBean", "o.s.i.c.CEFB") + "</b> \n");
				sb.append("==== \n");
				sb.append(nd.getIntegrationPatternType() + " \n----\n");
				sb.append(nd.getIntegrationPatternCategory() + " " + nd.getComponentType() + " \n ] \n");
			}
		}
		/*for (IntgLinkNode ln : graph.getLinks()) {
			System.out.println(ln.getFrom() + " -> " + ln.getTo());
		}*/
		
		for (IntgLinkNode ln : graph.getLinks()) {
					
					if(unwantedNode.containsKey(ln.getFrom())) {
						String d = unwantedNode.get(ln.getFrom()).split(",")[0];
						unwantedNode.put(ln.getFrom(), d+","+ln.getTo());
				//		System.out.println(ln.getFrom() + " From Unwanted " + ln.getTo() + "__" + d + " " +unwantedNode.get(ln.getFrom()));
					} else if (unwantedNode.containsKey(ln.getTo()) ){
						String d = unwantedNode.get(ln.getTo()).split(",")[1];
						unwantedNode.put(ln.getTo(), ln.getFrom()+","+d);
			//			System.out.println(ln.getFrom() + " To Unwanted " + ln.getTo()  + "__" + d + " " +unwantedNode.get(ln.getTo()));
					}
					else {
						sb.append(ln.getFrom() + " --> " + ln.getTo() + " : " + ln.getType() + " \n");
					}
				}	
				
		for(Entry<Integer,String> p : unwantedNode.entrySet()) {
			//System.out.println(p.getKey() + ":::"+p.getValue());
			if(p.getValue().equalsIgnoreCase("0,0")) {
			//	System.out.println(p.getKey() + " 0 - 0 ");
			} else if(p.getValue().startsWith("0,")) {
			//	System.out.println(p.getKey() + " _StartsWith0_  " + p.getValue());
				sb.append(" request --> " + p.getValue().split(",")[1] + " : input \n");
			} else if(p.getValue().endsWith(",0")) {
			//	System.out.println(p.getKey() + " _EndsWith0_  " + p.getValue());
				sb.append(p.getValue().split(",")[0] + " --> end : input \n");
			}
			else {
			//	System.out.println(p.getKey() + " _No0_  " + p.getValue());
				sb.append(p.getValue().split(",")[0] + " --> " + p.getValue().split(",")[1] + " : input \n");
			}
			
		}
		sb.append("@enduml\n");
		
		return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
	}
	
}
