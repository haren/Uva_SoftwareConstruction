import javafx.application.Application;
import javafx.stage.Stage;
import ql.ast.form.Form;
import ql.gen.QLLexer;
import ql.gen.QLParser;
import ql.ast.AstBuilder;
import ql.gui.Modeler;
import ql.gui.SimpleGui;
import ql.gui.SimpleModeler;
import ql.semantics.CondQuestionTable;
import ql.semantics.CondQuestionTableBuilder;
import ql.semantics.TypeChecker;
import ql.semantics.errors.Messages;
import qls.ast.Stylesheet;
import qls.gen.QLSLexer;
import qls.gen.QLSParser;
import qls.gui.StyledModeler;
import qls.semantics.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.IOException;
import java.util.List;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        String qlFile = getParameter(0);
        CharStream qlStream = getStream(qlFile);
        QLLexer qlLexer = new QLLexer(qlStream);
        QLParser qlParser = new QLParser(new CommonTokenStream(qlLexer));
        ParserRuleContext qlContext = qlParser.form();
        AstBuilder qlBuilder = new AstBuilder();
        Form form = (Form)qlBuilder.visit(qlContext);

        Messages ms = TypeChecker.check(form);
        if (ms.containsError())
        {
            System.err.print(ms.toString());
            System.exit(1);
        }

        CondQuestionTable condQuestionTable = CondQuestionTableBuilder.flatten(form);
        Modeler modeler = new SimpleModeler(condQuestionTable);

        if (this.isQlsFileSpecified())
        {
            String qlsFile = getParameter(1);
            CharStream qlsStream = getStream(qlsFile);
            QLSLexer qlsLexer = new QLSLexer(qlsStream);
            QLSParser qlsParser = new QLSParser(new CommonTokenStream(qlsLexer));
            ParserRuleContext qlsContext = qlsParser.stylesheet();
            qls.ast.AstBuilder qlsBuilder = new qls.ast.AstBuilder();
            Stylesheet stylesheet = (Stylesheet)qlsBuilder.visit(qlsContext);

            Messages qlsMs =  qls.semantics.TypeChecker.check(stylesheet, form);
// TODO: fix the ql and qls files and enable type checking
//            if (qlsMs.containsError())
//            {
//                System.err.print(qlsMs.toString());
//                System.exit(1);
//            }

            QuestionStyles questionStyles = StyleMerger.getStyles(stylesheet, form);
            modeler = new StyledModeler(condQuestionTable, stylesheet, questionStyles);
        }

        SimpleGui.run(form, modeler, primaryStage);
    }

    private CharStream getStream(String file)
    {
        try
        {
            return new ANTLRFileStream(file);
        }
        catch (IOException ex)
        {
            System.err.print(ex.toString());
            System.exit(1);
        }

        return null;
    }

    private String getParameter(int n)
    {
        List<String> parameters = getParameters().getRaw();
        return parameters.get(n);
    }

    private boolean isQlsFileSpecified()
    {
        return getParameters().getRaw().size() > 1;
    }
}
