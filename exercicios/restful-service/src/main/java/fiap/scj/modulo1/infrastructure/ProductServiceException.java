package fiap.scj.modulo1.infrastructure;

import lombok.Data;

@Data
public class ProductServiceException extends Exception {

    public static final String SEARCH_OPERATION_ERROR = "SEARCH_ERROR";
    public static final String INVALID_PARAMETER_ERROR = "INVALID_PARAMETER_ERROR";
    public static final String PRODUCT_NOT_FOUND_ERROR = "PRODUCT_NOT_FOUND_ERROR";
    public static final String CREATE_OPERATION_ERROR = "CREATE_ERROR";
    public static final String RETRIEVE_OPERATION_ERROR = "RETRIEVE_ERROR";
    public static final String UPDATE_OPERATION_ERROR = "UPDATE_ERROR";
    public static final String DELETE_OPERATION_ERROR = "DELETE_ERROR";
    private final String operation;

    public ProductServiceException(String operation, Throwable cause) {
        super(cause);
        this.operation = operation;
    }
}
