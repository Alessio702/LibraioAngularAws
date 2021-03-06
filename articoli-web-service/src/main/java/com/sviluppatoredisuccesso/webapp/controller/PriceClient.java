package com.sviluppatoredisuccesso.webapp.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name ="PriceArtService", url="http://restapitest-env-1.eba-z2vwijdq.eu-south-1.elasticbeanstalk.com/") //, configuration = FeignClientConfiguration.class)

public interface PriceClient 
{
	@GetMapping(value = "/api/prezzi/{id}")
    public Double getDefPriceArt(@RequestHeader("Authorization") String AuthHeader, @PathVariable("id") String Id);
	
	@GetMapping(value = "/api/prezzi/{id}/{idlist}")
    public Double getPriceArt(@RequestHeader("Authorization") String AuthHeader, @PathVariable("id") String Id, 
    		@PathVariable("idlist") String IdList);
}
