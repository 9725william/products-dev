package com.products.demo.api.v1.local.api_franchise.logs_franchise;

import com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.bd1.LogFranchise;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;

public class LogFranchiseThread extends Thread {
	
	private LogFranchise LogFranchise;
	private LogFranchiseService LogFranchiseService;

	public LogFranchiseThread( LogFranchiseService LogFranchiseService, LogFranchise LogFranchise ){
		this.LogFranchiseService = LogFranchiseService;
		this.LogFranchise = LogFranchise;
	}

	@Override
	public void run(){
		try {
			Object resp = LogFranchiseService.register( LogFranchise );
			UtilsLocal.log( "Responde Log" ); UtilsLocal.log( resp );
		}
		catch ( Exception e ){
			UtilsLocal.log( "Error desde Hilo de Log:" );
			UtilsLocal.log( e.getMessage() );
		}
	}
}