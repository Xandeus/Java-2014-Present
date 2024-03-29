package machinelearning;

public class PVector {
	float x;
	float y;

	public PVector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(PVector v) {
		x = v.x;
		y = v.y;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public PVector get() {
		return new PVector(x, y);
	}

	public float mag() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public PVector add(PVector v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public void sub(PVector v) {
		x -= v.x;
		y -= v.y;
	}
	static public PVector sub(PVector v1, PVector v2) { 
	    return sub(v1, v2, null); 
	 } 
	 
	 
	  static public PVector sub(PVector v1, PVector v2, PVector target) { 
	    if (target == null) { 
	      target = new PVector(v1.x - v2.x, v1.y - v2.y); 
	    } else { 
	      target.set(v1.x - v2.x, v1.y - v2.y); 
	    } 
	    return target; 
	  } 

	public void mult(PVector v) {
		x *= v.x;
		y *= v.y;
	}
	public PVector mult(float v) {
		x *= v;
		y *= v;
		return get();
	}
	public void multX(float v) {
		x *= v;
	}
	public void multY(float v) {
		y *= v;
	}
	public void normalize() {
		float m = mag();
		if (m != 0 && m != 1) {
			div(m);
		}
	}

	public void div(float n) {
		x /= n;
		y /= n;
	}

	static public PVector div(PVector v, float n, PVector target) {
		if (target == null) {
			target = new PVector(v.x / n, v.y / n);
		} else {
			target.set(v.x / n, v.y / n);
		}
		return target;
	}

	static public PVector div(PVector v1, PVector v2) {
		return div(v1, v2, null);
	}

	static public PVector div(PVector v1, PVector v2, PVector target) {
		if (target == null) {
			target = new PVector(v1.x / v2.x, v1.y / v2.y);
		} else {
			target.set(v1.x / v2.x, v1.y / v2.y);
		}
		return target;
	}
	public void limit(float max) { 
	    if (mag() > max) { 
	      normalize(); 
	      mult(max); 
	    } 
	  } 
	public String toString(){
		return x + " " + y;
	}
}