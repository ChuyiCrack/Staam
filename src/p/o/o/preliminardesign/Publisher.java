
package p.o.o.preliminardesign;
public class Publisher extends GlobalUser{
    String Descripcion;
    public Publisher(int ID, String Email, String Name, String Ubicacion,String FechaCreacion,String Descripcion) {
        super(ID, Email, Name ,Ubicacion,FechaCreacion);
        this.Type = "Publisher";
        this.Descripcion = Descripcion;
    }
    
    public String getDescripcion(){
        return this.Descripcion;
    }
}
