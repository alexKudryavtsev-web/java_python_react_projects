package object;

import com.fathzer.soft.javaluator.*;

import java.util.*;

public class TreeBooleanEvaluator extends AbstractEvaluator<String> {
    private final static Operator NOT = new Operator("!=", 2, Operator.Associativity.LEFT, 1);
    private final static Operator XOR = new Operator("^", 2, Operator.Associativity.LEFT, 4);
    private final static Operator AND = new Operator("∧", 2, Operator.Associativity.LEFT, 3);
    private final static Operator OR = new Operator("∨", 2, Operator.Associativity.LEFT, 2);
    private final static Operator IMPLIC = new Operator("⇒", 2, Operator.Associativity.LEFT, 5);
    private final static Operator EQUIV = new Operator("≡", 2, Operator.Associativity.LEFT, 6);

    private static final Parameters PARAMETERS;

    static {
        // Create the evaluator's parameters
        PARAMETERS = new Parameters();
        // Add the supported operators
        PARAMETERS.add(NOT);
        PARAMETERS.add(AND);
        PARAMETERS.add(OR);
        PARAMETERS.add(XOR);
        PARAMETERS.add(IMPLIC);
        PARAMETERS.add(EQUIV);

        // Add the parentheses
        PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
    }

    public TreeBooleanEvaluator() {
        super(PARAMETERS);
    }

    private boolean getValue(String literal) {
        if ("T".equals(literal) || literal.endsWith("=true")) return true;
        else if ("F".equals(literal) || literal.endsWith("=false")) return false;
        throw new IllegalArgumentException("Unknown literal : "+literal);
    }

    @Override
    protected String toValue(String s, Object o) {
        return s;
    }

    @Override
    protected String evaluate(Operator operator, Iterator<String> operands, Object evaluationContext) {
        List<String> tree = (List<String>) evaluationContext;
        String o1 = operands.next();
        String o2 = operands.next();
        Boolean result;
        boolean f = getValue(o1);
        boolean s = getValue(o2);
        if(operator == NOT) {
            result = !s;
        } else if (operator == OR) {
            result = f || s;
        } else if(operator == XOR) {
            result = f ^ s;
        } else if (operator == AND) {
            result = f && s;
        } else if(operator == IMPLIC) {
            result = !f || s;
        }else if(operator == EQUIV) {
            result = f == s;
        } else {
            throw new IllegalArgumentException();
        }
        String eval = "("+o1+" "+operator.getSymbol()+" "+o2+")="+result;
        tree.add(eval);
        return eval;
    }

    public static String calc(String expression) {
        List<String> sequence = new ArrayList<>();
        new TreeBooleanEvaluator().evaluate(expression, sequence);
        if(sequence.get(sequence.size() - 1).endsWith("true"))
            return "1";
        else
            return "0";
    }
}
