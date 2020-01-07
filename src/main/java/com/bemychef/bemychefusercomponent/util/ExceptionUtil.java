package com.bemychef.bemychefusercomponent.util;

import com.bemychef.bemychefusercomponent.enums.ErrorMessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtil {

    private ExceptionUtil(){
        //empty private constructor
    }

    public static ResponseEntity<Object> commonErrorResponse() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessageEnum.CONTACT_ADMIN);
    }
}
