package com.faash.sample_rest_service.exception;

import com.faash.sample_rest_service.http.ResponseBodyModel;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = Exception.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ResponseBodyModel.class),
                            examples = @ExampleObject(value = """
                                        {
                                            "success": false,
                                            "result": "null",
                                            "message": "Internal Server Error",
                                            "responseCode": "500",
                                            "trackingCode": "123e4567-e89b-12d3-a456-426614174000",
                                        }
                                    """)
                    ))})
    public ResponseEntity<ResponseBodyModel> handleExceptions(Exception ex) {
        ResponseBodyModel responseModel = new ResponseBodyModel();
        responseModel.setSuccess(false);
        responseModel.setMessage(ex.getMessage());
        responseModel.setResponseCode("500");
        return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
