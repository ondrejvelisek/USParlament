package es.upc.fib.prop.usParlament.domain;

import es.upc.fib.prop.shared13.Edge;
import java.util.*;


/**
 * Created by alexmiro9 on 8/4/15.
 */

public class Attribute  {

    private AttributeDefinition definition;
    private String value;

    /**
     * @pre True
     * @post An empty attribute is created
     * Creates an empty attribute
     */
    public Attribute() {}

    /**
     * @pre importance is an integer between 0 and 3, both included.
     * @post An empty graph is created
     * @param definition
     * @param value
     * Creates an attribute of name 'name' and importance 'importance'.
     */
    public Attribute(AttributeDefinition definition, String value)
    {
        this.definition = definition;
        this.value = value;
    }

    /**
     * @pre True
     * @post Returns the name of the attribute.
     * @return
     * Returns the name of the attribute.
     */
    public String getName() {
	    return definition.getName();
    }

    /**
     * @pre True
     * @post Returns true if the node is in the graph, false otherwise
     * @return
     */
    public int getImportance() {
	    return definition.getImportance();
    }

    /**
     * @pre True.
     * @post Returns the value of the attribute.
     * @return
     * Returns de value of the attribute.
     */
    public Object getValue() {
	    return this.value;
    }

    /**
     * @pre v is a correct value.
     * @post The value of the attribute is 'v'.
     * @param v
     * Sets the value of attribute.
     */
    public void setValue(String v) {
	    this.value = v;
    }

    /**
     * @pre a is the same type of attribute that the implicit one.
     * @post true iff a's value is equal to the attribute's value
     * @param a
     * @return
     * Compares two attributes.

    public Boolean equals(Attribute a) {
        if (a.getType()) {
            return a.getValue1() == this.value1;
        } else {
            return a.getValue2() == this.value2;
        }
    }*/
}
