package com.bemychef.bemychefusercomponent.enums;

import com.bemychef.bemychefusercomponent.fileReader.ErrorPropertiesFileReader;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorMessageEnum implements Serializable{

	CONTACT_ADMIN("U60000", "");
	  private final String errorCode;
	  private final String errorMessage;
	
	  ErrorMessageEnum(final String  errorCode,final String errorMessage) {
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return ErrorPropertiesFileReader.getDataByKey(this.name());
	}
	  
	 @JsonCreator
	 static ErrorMessageEnum findValue(@JsonProperty("errorCode") String errorCode, @JsonProperty("errorMessage") String errorMessage){
		 return Arrays.stream(ErrorMessageEnum.values()).filter(v -> v.errorCode == errorCode && v.errorMessage.equals(errorMessage)).findFirst().get();
	 }
}
