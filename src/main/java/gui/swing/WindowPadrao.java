package gui.swing;

import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;

import java.awt.*;

public interface WindowPadrao {
    Window getWindowParent();
    ModuloSistema getModuloSistema();
    NivelAcessoModulo getNivelAcessoNecessario();

}
