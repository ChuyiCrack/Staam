
package p.o.o.preliminardesign;
public class Publisher extends GlobalUser{
    String Descripcion;
    
    public Publisher(int ID, String Email, String Name,String Descripcion) {
        super(ID, Email, Name , Descripcion);
        this.Descripcion = Descripcion;
    }
    
    public String getDescripcion(){
        return this.Descripcion;
    }
}
