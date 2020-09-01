package com.xantrix.webappspec.service;

import java.util.List;

import com.sviluppatoredisuccesso.webapp.service.ArticoliService;
import com.xantrix.webappspec.entities.ArticoliSpec;

public interface ArticoliServiceSpec extends ArticoliService
{
	public List<ArticoliSpec> getLibriByRedazione(String redazione);
}
