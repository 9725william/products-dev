package com.products.demo.api.v1.local.api_franchise.logs_franchise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.LogFranchiseAdapter;
import com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.bd1.LogFranchise;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;

@Service
public class LogFranchiseService {
	private String myClassName = LogFranchiseService.class.getName();

	@Autowired
	LogFranchiseAdapter logFranchiseAdapter;

	public Object register( LogFranchise log )
	{
		try {
			Object resp = logFranchiseAdapter.register( log );
			return resp;
		}
		catch ( Exception e ){
			return new ErrorService(
				"Error almacenando el Log",
				e.getMessage(),
				myClassName
			);
		}
	}
}
