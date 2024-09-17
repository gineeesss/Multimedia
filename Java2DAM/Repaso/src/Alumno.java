public class Alumno {
    private int id;
    private String nombre;
    private String clase;
    private int edad;
    public Alumno( String nombre, String clase, int edad) {
        this.nombre = nombre;
        this.clase = clase;
        this.edad = edad;
    }
    public Alumno(String nombre){
        this(nombre,"sin clase",0);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", clase='" + clase + '\'' +
                ", edad=" + edad +
                '}';
    }
}
