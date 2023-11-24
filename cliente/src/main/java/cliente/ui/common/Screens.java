package cliente.ui.common;

public enum Screens {

    CUSTOMERADD("/fxml/customerFXML/customerAdd.fxml"),
    LOGIN("/fxml/login.fxml"),

    CUSTOMERDELETE("/fxml/customerFXML/customerDelete.fxml"),

    CUSTOMERMAIN("/fxml/mainScreen.fxml"),

    CUSTOMERMODIFY("/fxml/customerFXML/customerModify.fxml"),

    CUSTOMERLIST("/fxml/customerFXML/customerList.fxml"),


    ORDERADD("/fxml/orderFXML/orderAdd.fxml"),
    ORDERDELETE("/fxml/orderFXML/orderDelete.fxml"),
    ORDERLIST("/fxml/orderFXML/orderList.fxml"),
    ORDERMODIFY("/fxml/orderFXML/orderModify.fxml");

    private String ruta;

    Screens(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
