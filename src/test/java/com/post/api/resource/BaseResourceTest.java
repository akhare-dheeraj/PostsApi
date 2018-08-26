package com.post.api.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import com.posts.api.resource.UserResource;

public abstract class BaseResourceTest extends JerseyTest{
	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(UserResource.class).property("contextConfigLocation",
				"classpath:application-context-test.xml");
	}
}
