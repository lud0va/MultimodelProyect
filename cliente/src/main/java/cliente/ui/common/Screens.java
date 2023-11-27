package cliente.ui.common;

import common.Constant;

public enum Screens {

    ADD(Constant.FXML_ADD_SCREEN_FXML_ROOT),
    LOGIN(Constant.FXML_LOGIN_SCREEN_FXML_ROOT),

    LIST(Constant.FXML_LIST_SCREEN_FXML_ROOT);


    private String ruta;

    Screens(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
