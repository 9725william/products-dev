package com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.bd1.LogFranchise;
import com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.bd1.LogFranchiseRepository;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;

@Service
public class LogFranchiseAdapter {
    private String myClassName = LogFranchiseAdapter.class.getName();
	
	@Autowired
	LogFranchiseRepository logFranchiseRepository;

	public Object register( LogFranchise log )
	{
		try {
			Object resp = logFranchiseRepository.save( log );
			return resp;
		}
		catch ( Exception e ){
			return new ErrorService(
				"Error almacenando el Log en la BD",
				e.getMessage(),
				myClassName
			);
		}
	}

}


