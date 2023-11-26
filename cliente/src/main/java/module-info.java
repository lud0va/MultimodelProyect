module cliente {
    requires lombok;
    requires okhttp3;

    requires io.reactivex.rxjava3;
    requires retrofit2;
    requires io.vavr;
    requires com.google.gson;
    requires jakarta.inject;
    requires retrofit2.adapter.rxjava3;
    requires retrofit2.converter.gson;
    requires retrofit2.converter.scalars;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires jakarta.cdi;

    requires domain;
    exports cliente.ui.pantallas.login;
    exports cliente.ui.main;
    exports cliente.data;
    exports cliente.common;
    exports cliente.useCases;
    exports cliente.ui.pantallas.principal;


    opens cliente.ui.pantallas.login;
    opens cliente.ui.pantallas.principal;
    opens cliente.useCases;
    opens cliente.ui.main;
    opens cliente.data;
    opens cliente.common;
}