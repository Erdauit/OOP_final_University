package Classes;

import java.io.Serializable;
import java.util.*;

public class Mark implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
    private Vector<Double> firstAtt = new Vector<Double>();
    private Vector<Double> secondAtt = new Vector<Double>();
    private Double finalExam = 0.0;
    
    public Mark() {
    	
    }
    
	//GETTER and SETTERS
	public Vector<Double> getFirstAtt() {
		return firstAtt;
	}
	public void setFirstAtt(Vector<Double> firstAtt) {
		this.firstAtt = firstAtt;
	}
	public Vector<Double> getSecondAtt() {
		return secondAtt;
	}
	public void setSecondAtt(Vector<Double> secondAtt) {
		this.secondAtt = secondAtt;
	}
	public Double getFinalExam() {
		return finalExam;
	}
	public void setFinalExam(Double finalExam) {
		this.finalExam = finalExam;
	}
    
    //FUNCTIONS
	public Double sumOfFirstAtt() {
		Double sum1 = (double) 0;
		for(Double i: firstAtt) {
			sum1 += i;
		}
		return sum1;
    }
    public Double sumOfSecondAtt() {
		Double sum1 = (double) 0;
		for(Double i: secondAtt) {
			sum1 += i;
		}
		return sum1;
    }
    
    public String getJournal() {
		return  "firstAtt=" + this.sumOfFirstAtt() + "     |     "+
				"secondAtt=" + this.sumOfSecondAtt() + "     |     " +
				"finalExam=" + finalExam + "\n";
    }
    
    public String getAtt() {
    	return "firstAtt=" + this.sumOfFirstAtt() + "     |     "+
				"secondAtt=" + this.sumOfSecondAtt();
    }

	public void setFirstAtt(Double point) {
		if(sumOfFirstAtt() + point <= 30) this.firstAtt.add(point);
		else System.out.println("It's too much");
	}
	public void setSecondAtt(Double point) {
		if(sumOfSecondAtt() + point <= 30) this.secondAtt.add(point);
		else System.out.println("It's too much");
	}
	public Double sumOfAll() {
		return this.sumOfFirstAtt()+this.sumOfSecondAtt()+this.getFinalExam();
    }
	
}
