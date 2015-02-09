/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.uva.student.calinwouter.ql.generated.analysis;

import java.util.*;
import org.uva.student.calinwouter.ql.generated.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAForm(AForm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASingleStmtlist(ASingleStmtlist node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultiStmtlist(AMultiStmtlist node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASingleStmt(ASingleStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAQuestionStmt(AQuestionStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAValueStmt(AValueStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIfelseStmt(AIfelseStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIfStmt(AIfStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABoolType(ABoolType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAStringType(AStringType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIntType(AIntType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAddExp(AAddExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATrueExp(ATrueExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFalseExp(AFalseExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOrExp(AOrExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAndExp(AAndExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASubExp(ASubExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEqExp(AEqExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANeqExp(ANeqExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALtExp(ALtExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGtExp(AGtExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALteExp(ALteExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGteExp(AGteExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMulExp(AMulExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivExp(ADivExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAModExp(AModExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParenExp(AParenExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANotExp(ANotExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANumberExp(ANumberExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIdentExp(AIdentExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComment(TComment node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTnot(TTnot node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNumber(TNumber node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTOr(TOr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAnd(TAnd node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAdd(TAdd node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSub(TSub node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMul(TMul node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMod(TMod node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLparen(TLparen node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRparen(TRparen node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTform(TTform node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLbrace(TLbrace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRbrace(TRbrace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTIf(TIf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTElse(TElse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTbool(TTbool node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTtrue(TTtrue node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTfalse(TTfalse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTstring(TTstring node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTint(TTint node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTString(TString node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTeq(TTeq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTneq(TTneq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTlt(TTlt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTgt(TTgt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTlte(TTlte node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTgte(TTgte node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTBlank(TBlank node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTIdent(TIdent node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
