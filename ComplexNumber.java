/**
 * Class to hold a complex number, for doing complex arithmetic
 From Dr. Hochberg's DS2018 repository.
 */

public class ComplexNumber{
    double x;
    double y;

    public ComplexNumber(double x, double y){
	this.x = x;
	this.y = y;
    }

    public ComplexNumber add(ComplexNumber z){
	return new ComplexNumber(this.x + z.x, this.y + z.y);
    }
    
    public ComplexNumber multiply(ComplexNumber z){
	return new ComplexNumber(this.x * z.x - this.y * z.y,
				 this.x * z.y + this.y * z.x);
    }

    public double norm(){
	return Math.sqrt(this.x*this.x + this.y*this.y);
    }
}
    
    
