package graphic

import (
	"bytes"
	"errors"
	"log"
	"text/template"

	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/qlang/interpreter/ast"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/stylelang"
	"gopkg.in/qml.v1"
)

func (g *Gui) findPageForField(fieldName string) (*stylelang.Page, error) {
	lenIdx := len(g.questionIndex[fieldName]) - 1
	pages := g.pages["root"].Pages()
	var final *stylelang.Page
	foundPage := false
	for k, v := range g.questionIndex[fieldName] {
		if v == "root" {
			continue
		}
		if k < lenIdx {
			pages = pages[v].Pages()
			continue
		}
		final = pages[v]
		foundPage = true
	}
	var err error = nil
	if !foundPage {
		err = errors.New("layout page not found")
	}
	return final, err
}

func (g *Gui) addNewQuestion(newFieldType, newFieldName,
	newFieldCaption string, invisible bool) {

	container := g.root

	lenIdx := len(g.questionIndex[newFieldName]) - 1
	for k, v := range g.questionIndex[newFieldName] {
		if v == "root" {
			continue
		}
		container = container.ObjectByName(v)
		if k < lenIdx {
			container = container.ObjectByName("scroll")
			container = container.Object("contentItem")
			container = container.ObjectByName(v + "View")
		}
	}
	container = container.ObjectByName("scroll")
	container = container.Object("contentItem")
	g.targetContainer = container

	var question qml.Object
	switch newFieldType {
	default:
		question = g.renderNewStringQuestion(newFieldName, newFieldCaption, "")
	case ast.BoolQuestionType:
		question = g.renderNewBooleanQuestion(newFieldName, newFieldCaption, false)
	case ast.NumericQuestionType:
		question = g.renderNewNumericQuestion(newFieldName, newFieldCaption, 0)
	}

	if !invisible {
		question.Set("visible", true)
	}

	g.symbolTable[newFieldName] = question
}

func (g *Gui) updateQuestion(fieldName, fieldType string, content interface{}) {
	if question, ok := g.symbolTable[fieldName]; ok {
		question.Set("visible", true)

		fieldPtr := question.ObjectByName(fieldName)

		if fieldPtr.Bool("activeFocus") {
			// Don't let regular update loop to overwrite current
			// user edit in the focused field.
			return
		}

		g.updateCallbacks[fieldName](content.(string))
	}
}

func (g *Gui) hideQuestion(fieldName string) {
	g.symbolTable[fieldName].Set("visible", "false")
}

func startQMLengine(appName, tabContainer string) qml.Object {
	engine := qml.NewEngine()
	cradleQML := renderCradle(appName, tabContainer)
	cradle, err := engine.LoadString("cradle.qml", cradleQML)
	if err != nil {
		log.Fatal("Fatal error while parsing cradle.qml:", err)
	}
	return cradle
}

func renderTemplateQuestion(qml, fieldName, question, validator string) string {
	var b bytes.Buffer
	t := template.Must(template.New("newQuestion").Parse(qml))
	t.Execute(&b, struct {
		ObjectName   string
		QuestionName string
		Validator    string
	}{fieldName, question, validator})
	return b.String()
}

func renderAndInsertAt(newQuestionQML string, rows qml.Object) qml.Object {
	engine := qml.NewEngine()
	newQuestion, err := engine.LoadString("newQuestion.qml", newQuestionQML)
	if err != nil {
		log.Fatal("Fatal error while parsing newQuestion.qml:", err,
			"Got:", newQuestionQML)
	}

	question := newQuestion.Create(nil)
	question.Set("parent", rows)

	return question
}
