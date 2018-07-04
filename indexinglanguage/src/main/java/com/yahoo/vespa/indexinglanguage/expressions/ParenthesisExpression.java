// Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.indexinglanguage.expressions;

import com.yahoo.document.DataType;
import com.yahoo.document.DocumentType;
import com.yahoo.vespa.objects.ObjectOperation;
import com.yahoo.vespa.objects.ObjectPredicate;

/**
 * @author Simon Thoresen Hult
 */
public class ParenthesisExpression extends CompositeExpression {

    private final Expression innerExp;

    public ParenthesisExpression(Expression innerExp) {
        this.innerExp = innerExp;
    }

    public Expression getInnerExpression() {
        return innerExp;
    }

    @Override
    protected void doExecute(ExecutionContext ctx) {
        innerExp.execute(ctx);
    }

    @Override
    protected void doVerify(VerificationContext context) {
        innerExp.verify(context);
    }

    @Override
    public DataType requiredInputType() {
        return innerExp.requiredInputType();
    }

    @Override
    public DataType createdOutputType() {
        return innerExp.createdOutputType();
    }

    @Override
    public String toString() {
        return "(" + innerExp + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ParenthesisExpression)) {
            return false;
        }
        ParenthesisExpression rhs = (ParenthesisExpression)obj;
        if (!innerExp.equals(rhs.innerExp)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() + innerExp.hashCode();
    }

    @Override
    public void selectMembers(ObjectPredicate predicate, ObjectOperation operation) {
        select(innerExp, predicate, operation);
    }

}
