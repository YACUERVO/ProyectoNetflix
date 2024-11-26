package modelo;

import java.util.ArrayList;
import java.util.Random;

// Clase para representar un Usuario
public class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String password;
    private ArrayList<Perfil> perfiles;

    public Usuario(String nombre, String apellido, String email, String celular){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.perfiles = new ArrayList<>();
        this.password = generarPassword();

    }

    //Méodo para generar contraseña aleatoriamente
    private String generarPassword() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            password.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return password.toString();
    }
    //Metodo para agregar un perfil

    public boolean agregarPerfil(String nombre,String pin) {
        if (perfiles.size() >= 5) {
            return false;
        }
        for (Perfil perfil : perfiles) {
            if (perfil.getNombre().equals(nombre)) {
                return false;
            }
        }
        perfiles.add(new Perfil(nombre, pin));
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(ArrayList<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
}
