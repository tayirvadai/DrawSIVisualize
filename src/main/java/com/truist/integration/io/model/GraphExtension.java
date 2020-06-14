package com.truist.integration.io.model;

import java.util.List;
import java.util.Map;

import org.springframework.integration.graph.IntegrationNode;
import org.springframework.integration.graph.LinkNode;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphExtension {

	@JsonProperty("contentDescriptor")
	private ContentDescriptor contentDescriptor;
	
	@JsonProperty("nodes")
	private List<IntgNode> nodes;

	@JsonProperty("links")
	private List<IntgLinkNode> links;

	public ContentDescriptor getContentDescriptor() {
		return contentDescriptor;
	}

	public void setContentDescriptor(ContentDescriptor contentDescriptor) {
		this.contentDescriptor = contentDescriptor;
	}

	public List<IntgNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<IntgNode> nodes) {
		this.nodes = nodes;
	}

	public List<IntgLinkNode> getLinks() {
		return links;
	}

	public void setLinks(List<IntgLinkNode> links) {
		this.links = links;
	}

	
}
