/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p.o.o.preliminardesign;
public abstract class GlobalUser{
        int ID;
        String Email;
        String Name;
        String Ubicacion;
        String FechaCreacion;
        String Type;
                
         public GlobalUser(int ID , String Email , String Name , String Ubicacion,String FechaCreacion){
             this.ID = ID;
             this.Email= Email;
             this.Name = Name;
             this.Ubicacion = Ubicacion;
             this.FechaCreacion = FechaCreacion;
         }
         
         public int getID(){
             return this.ID;
         }
         
         public String getEmail(){
             return this.Email;
         }
         
          public String getUbicacion() {
            return this.Ubicacion;
        }
         
         public String getName(){
             return this.Name;
         }
         public String getFechaCreacion() {
            return this.FechaCreacion;
    }
}
