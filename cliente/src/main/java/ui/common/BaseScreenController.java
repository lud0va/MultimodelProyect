package ui.common;


import ui.principal.PrincipalController;

public abstract class BaseScreenController {

    private PrincipalController principalController;


    public PrincipalController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado() {

    }

}
