package cliente.ui.common;

public enum Screens {

    ADD("/fxml/addScreen.fxml"),
    LOGIN("/fxml/loginScreen.fxml"),

    LIST("/fxml/listScreen.fxml"),

    CUSTOMERMAIN("/fxml/mainScreen.fxml");



    private String ruta;

    Screens(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
