
package p.o.o.preliminardesign;

public class User extends GlobalUser{
    double Balance;
    String Telefono;
    
    public User(int ID, String Email, String Name,double Balance , String FechaCreacion , String Ubicacion , String Telefono) {
        super(ID, Email, Name , Ubicacion);
        this.Balance = Balance;
        this.FechaCreacion = FechaCreacion;
        this.Telefono = Telefono;
    }
    
    public double getBalance() {
        return this.Balance;
    }

    public String getTelefono() {
        return this.Telefono;
    } 
}
