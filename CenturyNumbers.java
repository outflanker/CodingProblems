package CodingProblems;

/**
 * 
 * @author Siddharth
 * A classic math problem is to add addition and multiplication operators to the 
 * number sequence 1-9  to make an equation valid.  An example is show as follows:  
 * 1 2 3 4 5 6 7 8 9 = 100  
 * You can add binary operators to the blanks or you can leave some blanks to group 
 * certain digits to  one number. One possible solution to the above equation is: 
 * 12+34+5×6+7+8+9 = 100  It is treated as 12 + 34 + 5 * 6 + 7 + 8 + 9 = 100.  
 * 
 * Note: Only consider addition (+) and multiplication (*) WITHOUT parentheses.  
 * Given a number N, show how many possible ways there are to fill the blanks of 1 – 9 
 * to get the final  sum.
 *
 */
	
class Node{
	public int level;
	public String expression;
	
	Node left;
	Node middle;
	Node right;
	
	public Node(int level,String expression){
		this.level=level;
		this.expression=expression;
		this.left=null;
		this.middle=null;
		this.right=null;
	}
	public int evaluate(){
	    return (int)new Parser(expression).parse();
	}
}
class Parser {
    int pos = -1, c;
    String str;
    public Parser(String input){
    	this.str=input;
    }

    void eatChar() {
        c = (++pos < str.length()) ? str.charAt(pos) : -1;
    }
    double parse() {
        eatChar();
        double v = parseExpression();
        if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
        return v;
    }
    double parseExpression() {
        double v = parseTerm();
        while(true) {
            if (c == '+') { 
                eatChar();
                v += parseTerm();
            } else {
                return v;
            }
        }
    }
    double parseTerm() {
        double v = parseFactor();
        while(true) {
            if (c == '*') { 
                if (c == '*') eatChar();
                v *= parseFactor();
            } else {
                return v;
            }
        }
    }
    double parseFactor() {
        double v;	           
        String sb = "";
        while (c >= '0' && c <= '9') {
            sb=sb+(char)c;
            eatChar();
        }
        if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
        v = Double.parseDouble(sb.toString());
        return v;
    }
}
public class CenturyNumbers {
	static int finallevel=9;
	static int counter=0;
	static int N;
	public static void create(Node node){
		int level=node.level;
		String exp=node.expression;
		if(level==finallevel-1){
			node.left=new Node(level+1,exp+"+"+(level+1));
			if(node.left.evaluate()==N){
				System.out.println(node.left.expression);
				counter++;
			}
			node.middle=new Node(level+1,exp+"*"+(level+1));
			if(node.middle.evaluate()==N){
				System.out.println(node.middle.expression);
				counter++;
			}
			node.right=new Node(level+1,exp+(level+1));
			if(node.right.evaluate()==N){
				System.out.println(node.right.expression);
				counter++;
			}
			return;
		}
		Node l=new Node(level+1,exp+"+"+(level+1));
		create(l);
		Node m=new Node(level+1,exp+"*"+(level+1));
		create(m);
		Node r=new Node(level+1,exp+(level+1));
		create(r);
	}
	public static void main(String[] args) {
		N=100; //Change N value for other numbers.
		Node root=new Node(1, "1");
		create(root);
		System.out.println(counter);
	}
}
