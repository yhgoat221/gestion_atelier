package com.ism.entities;

public class Unite extends AbstractEntity {
    

    public Unite(){
        super();
    }
    
    public Unite(int id, String libelle){
        super(id,libelle);
    }
    @Override
    public String toString() {
        return "Unite" + super.toString();
    }
    
}
