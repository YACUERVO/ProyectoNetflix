package modelo;
// Clase para representar un Perfil
public class Perfil {
    private  String nombre;
    private  String pin;



    public Perfil(String nombre, String pin) {
        this.nombre = nombre;
        this.pin = pin;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
