package com.Spring_Security_Back.shared.constants;

public class ExceptionMessages {

    private ExceptionMessages() {
        throw new IllegalStateException("Clase de utilidad de excepciones");
    }

    public static final String CLIENTS_NOT_FOUND = "No se encontraron clientes registrados";
    public static final String CLIENT_WITH_ID_NOT_FOUND = "El cliente con el id %s no se encuentra registrado";
    public static final String CLIENT_NOT_NULL = "El cliente no puede ser nulo";
    public static final String CLIENT_DELETED = "El cliente con el id %s ha sido eliminado";

    public static final String PRODUCTS_NOT_FOUND = "No se encontraron productos registrados";
    public static final String PRODUCT_WITH_ID_NOT_FOUND = "El producto con el id %s no se encuentra registrado";
    public static final String PRODUCT_NOT_NULL = "El producto no puede ser nulo";
    public static final String PRODUCT_DELETED = "El producto con el id %s ha sido eliminado";

    public static final String ORDERS_NOT_FOUND = "No se encontraron ordenes registradas";
    public static final String ORDER_WITH_ID_NOT_FOUND = "La orden con el id %s no se encuentra registrada";
    public static final String ORDER_NOT_NULL = "La orden no puede ser nula";
    public static final String ORDER_DELETED = "La orden con el id %s ha sido eliminada";




    public static final String EMAIL_ALREADY_REGISTERED = "El email %s ya está registrado por otro usuario";

}