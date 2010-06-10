package org.openiam.base.id;
// Generated Dec 2, 2007 5:41:37 PM by Hibernate Tools 3.2.0.b11



/**
 * DTO for the SequenceGen class.
 */
public class SequenceGen  implements java.io.Serializable {


     private String attribute;
     private Integer nextId = new Integer(0);

    public SequenceGen() {
    }

	
    public SequenceGen(String attribute) {
        this.attribute = attribute;
    }
    public SequenceGen(String attribute, Integer nextId) {
       this.attribute = attribute;
       this.nextId = nextId;
    }
   
    public String getAttribute() {
        return this.attribute;
    }
    
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    public Integer getNextId() {
        return this.nextId;
    }
    
    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }
    
    public void incrementId () {
        nextId++;
    }




}


