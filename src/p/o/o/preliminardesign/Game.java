package p.o.o.preliminardesign;
public class Game {
    int ID;
    String Name;
    
    public Game(int ID , String Name){
        this.ID = ID;
        this.Name = Name;
    }
            
    @Override
    public String toString() {
        return this.ID + "-" + this.Name; 
    }
}
