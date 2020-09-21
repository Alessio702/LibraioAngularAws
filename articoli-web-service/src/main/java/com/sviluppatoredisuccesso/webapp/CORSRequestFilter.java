//package com.sviluppatoredisuccesso.webapp;
//
//import java.io.IOException;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CORSRequestFilter implements ContainerRequestFilter{
//
//	
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//    	requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
//    	requestContext.getHeaders().add("Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
//    	requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
//    	requestContext.getHeaders().add("Access-Control-Allow-Methods",
//                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//    	
//    }
//}
