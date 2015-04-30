package es.upc.fib.prop.usParlament.domain;

import es.upc.fib.prop.shared13.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by miquel on 7/04/15.
 * contributions of alex, ondrej.
 */
public class MP extends Node {
	private Long id;
    private String fullname;
    private int district;
    private State state;
	private Map<AttrDefinition, Attribute> attributes;

    public MP(String fullname,State state,int district)
    {
        this.fullname = fullname;
        this.district = district;
        this.state = state;
	    this.attributes = new HashMap<>();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setId(Integer id) {
		setId((long) id);
	}

	public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public int getDistrict()
    {
        return district;
    }

    public void setDistrict(int district)
    {
        this.district = district;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

	public Set<AttrDefinition> getAttrDefs() { return attributes.keySet(); }

	public Set<Attribute> getAttributes() {
		return (Set<Attribute>) attributes.values();
	}

	public void addAttribute(Attribute attr) {
		attributes.put(attr.getDefinition(), attr);
	}

	public boolean hasAttribute(AttrDefinition ad) { return attributes.containsKey(ad); }

	public void removeAttribute(AttrDefinition def) {
		attributes.remove(def);
	}
	public void removeAttribute(Attribute attr) {
		removeAttribute(attr.getDefinition());
	}

	@Override
	//TODO needs revision
	public boolean equals(Object o) {
		if (!(o instanceof MP)) {
			return false;
		}
		if (this == o) {
			return true;
		}
		MP mp = (MP) o;
		return (mp.id == this.id);
	}

	// TODO
	@Override
	public int hashCode() {
		int hash = this.id == null ? 0 : this.id.hashCode();
		return hash;
	}

	@Override
    public String toString()
    {
	    String r = "id: "+this.id+"\nFullname: "+this.fullname+"\nState: "+this.state+"\nDistrict: "+this.district;

			r += "\nattributes: [ ";
			for (Attribute attr : attributes.values()) {
				r += attr + ", ";
			}
			r += "]";

        return r;
    }

	@Override
	public int compareTo(Node n) {
		if (this.equals(n)) {
			return 0;
		}
		if (!(n instanceof MP)) {
			throw new ClassCastException("compareTo method is defined only for MP class.");
		}
		MP mp = (MP) n;
		return this.fullname.compareTo(mp.fullname);
	}
}
