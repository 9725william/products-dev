package com.products.demo.api.v1.local.api_franchise.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import com.products.demo.api.v1.local.api_franchise.logs_franchise.LogFranchiseService;
import com.products.demo.api.v1.local.api_franchise.logs_franchise.LogFranchiseThread;
import com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.bd1.LogFranchise;

import jakarta.servlet.http.HttpServletRequest;

public class ResponseLocal {

	public boolean success;
	public Integer code;
	public Object data;
	public String message;
	public String description;

	private String action;
	private LogFranchiseService logFranchiseService;

	public ResponseLocal(  ){
		this.success = true;
		this.code = 200;
		this.logFranchiseService = null;
	}

	public ResponseLocal( LogFranchiseService logFranchiseService, String action ){
		this.success = true;
		this.code = 200;
		this.logFranchiseService = logFranchiseService;
		this.action = action;
	}

	public HttpStatus validateService(
		Object data,
		String message,
		String class_path,
		String payload,
		HttpServletRequest req
	){
		UtilsLocal.log( "\n============ validateService ================" );
		if( UtilsService.isErrorService(data) )
		{
			ErrorService errorService = ( ErrorService ) data;

			String message_ = errorService.getMessage();
			String description_ = errorService.getDescription();
			String class_path_ = errorService.getClass_path();
			int code_ = errorService.getCode();
			this.setError( code_, 
				message_, 
				description_, 
				new ArrayList<ObjectError>(), 
				class_path_, 
				payload, 
				req
			);
			System.out.println( "===>  Error validateService:\n" + description_ );
			HttpStatus httpStatus = this.getHttpStatus( code_ );
			return httpStatus;
		}
		else
		{
			this.success = true;
			this.code = 200;
			this.message = message;
			//this.data = ( data != null ) ? data : false;
			this.data = data;
			this.setSuccess( 
				class_path, 
				payload, 
				req
			);
			return HttpStatus.OK;
		}
	}


	public void setSuccess(
		String class_path,
		String payload,
		HttpServletRequest req
	){
		Timestamp created = UtilsLocal.getTimestampDateTimeNow();
		String ip = UtilsLocal.getIpAddr(req);
		String url = this.getUrl(req);
		String method = req.getMethod();

		if( this.logFranchiseService != null ){
			LogFranchise log = new LogFranchise();
			log.setUser_id( 1 ); // ----------------------------
			log.setAction( this.action );
			log.setCreated( created );
			log.setCode( this.code );
			log.setMessage( this.message );
			log.setDescription( this.description );
			log.setClass_path( class_path );
			log.setIp( ip );
			log.setUrl( url );
			log.setMethod( method );
			log.setPayload( payload );
			
			//logTaskService.register( log );
			// Asincrona
			LogFranchiseThread logFranchiseThread = new LogFranchiseThread( this.logFranchiseService, log );
			logFranchiseThread.start();
		}
	}


	public void setError(
		Integer code, 
		String message, 
		String description, 
		List<ObjectError> listErrors,
		String class_path,
		String payload,
		HttpServletRequest req
	){
		this.success = false;
		if( code == null ) this.code = 500;
		else this.code = code;

		if( this.code == 200 ) this.success = true;
		
		if( listErrors.size() != 0 ){
			StringBuilder messages_ = new StringBuilder();
			messages_.append("Lista_errores:");
			listErrors.forEach( err -> messages_.append( "- "+err.getDefaultMessage() ) );
			this.message = messages_.toString();
		}
		else{
			this.message = message;
		}
		
		if( description == null || description.equals("") ) description = this.message;
		this.description = description;
		
		Timestamp created = UtilsLocal.getTimestampDateTimeNow();
		String ip = UtilsLocal.getIpAddr(req);
		String url = this.getUrl(req);
		String method = req.getMethod();
        
        if( this.logFranchiseService != null ){
			LogFranchise log = new LogFranchise();
			log.setUser_id( 1 ); // ----------------------------
			log.setAction( this.action );
			log.setCreated( created );
			log.setCode( this.code );
			log.setMessage( this.message );
			log.setDescription( this.description );
			log.setClass_path( class_path );
			log.setIp( ip );
			log.setUrl( url );
			log.setMethod( method );
			log.setPayload( payload );
			
			//logTaskService.register( log );
			// Asincrona
            UtilsLocal.log("\n===> Err LOG:\n" + log.toString());
			LogFranchiseThread logFranchiseThread = new LogFranchiseThread( this.logFranchiseService, log );
			logFranchiseThread.start();
		}
	}

	private String getUrl(HttpServletRequest req){
		String queryString = ( req.getQueryString() != null )? "?" + req.getQueryString() : "";
		return req.getRequestURL().toString() + queryString;
	}


    private HttpStatus getHttpStatus( int code ){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        switch( code ) {
			case 200: httpStatus = HttpStatus.OK; 					break;
			case 400: httpStatus = HttpStatus.BAD_REQUEST; 			break;
       		case 401: httpStatus = HttpStatus.UNAUTHORIZED; 		break;
       		case 403: httpStatus = HttpStatus.FORBIDDEN; 			break;
       		case 404: httpStatus = HttpStatus.NOT_FOUND; 			break;
       		case 405: httpStatus = HttpStatus.METHOD_NOT_ALLOWED; 	break;
       		case 406: httpStatus = HttpStatus.NOT_ACCEPTABLE; 		break;
       		case 409: httpStatus = HttpStatus.CONFLICT; 			break;
       		case 408: httpStatus = HttpStatus.REQUEST_TIMEOUT; 		break;
       		case 501: httpStatus = HttpStatus.NOT_IMPLEMENTED; 		break;
       		case 502: httpStatus = HttpStatus.BAD_GATEWAY; 			break;
       		case 503: httpStatus = HttpStatus.SERVICE_UNAVAILABLE; 	break;
       		case 504: httpStatus = HttpStatus.GATEWAY_TIMEOUT; 		break;
		}
        return httpStatus;
    }

	}

