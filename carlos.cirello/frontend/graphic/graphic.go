// Package graphic is the GUI interface for Frontend. It does not interact directly with VM. It is the package gopkg.in/qml.v1. All compilations constraints apply.
package graphic

//go:generate go get -u gopkg.in/qml.v1
import (
	"log"
	"sync"

	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/ast"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/frontend"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/interpreter"
	"gopkg.in/qml.v1"
)

type renderAction int

const (
	drawQuestion renderAction = iota
	updateQuestion
	nukeQuestion
)

type render struct {
	action     renderAction
	identifier string
	label      string
	fieldType  string
	content    interface{}
	invisible  bool
}

// Gui holds the driver which is used by Frontend to execute the application
type Gui struct {
	renderEvent chan render
	appName     string

	mu          sync.Mutex
	drawStack   []render
	renderStack []render
	answerStack map[string]string
	sweepStack  map[string]bool
	symbolTable map[string]qml.Object
}

// GUI creates the driver for Frontend process.
func GUI(appName string) frontend.Inputer {
	driver := &Gui{
		appName: appName,

		renderEvent: make(chan render),
		answerStack: make(map[string]string),
		sweepStack:  make(map[string]bool),
		symbolTable: make(map[string]qml.Object),
	}
	return driver
}

// InputQuestion adds a new question into the GUI form stack
func (g *Gui) DrawQuestion(q *ast.QuestionNode, visible interpreter.Visibility) {
	g.mu.Lock()
	defer g.mu.Unlock()

	invisible := false
	if visible == interpreter.Hidden {
		invisible = true
	}
	m := &render{
		drawQuestion,
		q.Identifier(),
		q.Label(),
		q.Type(),
		q.Content(),
		invisible,
	}
	g.drawStack = append(g.drawStack, *m)
	g.sweepStack[q.Identifier()] = true
}

func (g *Gui) UpdateQuestion(q *ast.QuestionNode) {
	g.mu.Lock()
	defer g.mu.Unlock()

	if _, ok := g.sweepStack[q.Identifier()]; !ok {
		m := &render{
			action:     updateQuestion,
			identifier: q.Identifier(),
			label:      q.Label(),
			fieldType:  q.Type(),
			content:    q.Content(),
		}
		g.renderStack = append(g.renderStack, *m)
	}
	g.sweepStack[q.Identifier()] = true
}

// Flush transfers form stack into the screen.
func (g *Gui) Flush() {
	g.mu.Lock()
	defer g.mu.Unlock()

	for _, v := range g.drawStack {
		g.renderEvent <- v
	}
	g.drawStack = []render{}

	for _, v := range g.renderStack {
		g.renderEvent <- v
	}
	g.renderStack = []render{}

	for k, v := range g.sweepStack {
		if !v {
			nukeEvent := &render{
				action:     nukeQuestion,
				identifier: k,
			}
			g.renderEvent <- *nukeEvent
			delete(g.sweepStack, k)
		} else {
			g.sweepStack[k] = false
		}
	}
}

// FetchAnswers unloads the current captured answers from user to Frontend
// process and VM
func (g *Gui) FetchAnswers() map[string]string {
	g.mu.Lock()
	defer g.mu.Unlock()

	answerStack := g.answerStack
	g.answerStack = make(map[string]string)
	return answerStack
}

// Loop executes GUI main loop, which actually delegates the interface to the
// underlying library (go-qml).
func (g *Gui) Loop() {
	qml.Run(g.loop)
}

func (g *Gui) loop() error {
	win := startQMLengine(g.appName).CreateWindow(nil)
	rows := win.Root().ObjectByName("questions")
	win.Show()
	go g.addQuestionLoop(rows)
	win.Wait()
	return nil
}

func (g *Gui) addQuestionLoop(rows qml.Object) {
	for {
		select {
		case event := <-g.renderEvent:
			switch event.action {
			case drawQuestion:
				qml.Lock()
				g.addNewQuestion(
					rows,
					event.fieldType,
					event.identifier,
					event.label,
					event.content,
					event.invisible,
				)
				qml.Unlock()
			case updateQuestion:
				qml.Lock()
				g.updateQuestion(event.identifier)
				qml.Unlock()
			case nukeQuestion:
				qml.Lock()
				g.hideQuestion(rows, event.identifier)
				qml.Unlock()
			}
		}
	}
}

func (g *Gui) addNewQuestion(rows qml.Object, newFieldType, newFieldName,
	newFieldCaption string, content interface{}, invisible bool) {

	engine := qml.NewEngine()
	newQuestionQML := renderNewQuestion(newFieldType, newFieldName,
		newFieldCaption)
	newQuestion, err := engine.LoadString("newQuestion.qml", newQuestionQML)
	if err != nil {
		log.Fatal("Fatal error while parsing newQuestion.qml:", err,
			"Got:", newQuestionQML)
	}

	question := newQuestion.Create(nil)
	question.Set("parent", rows)

	if !invisible {
		question.Set("visible", true)
	}

	g.symbolTable[newFieldName] = question

	newFieldPtr := question.ObjectByName(newFieldName)
	// todo(carlos) improve readability
	switch newFieldType {
	case ast.BoolQuestionType:
		if content.(*ast.BoolQuestion).String() == "Yes" {
			newFieldPtr.Set("checked", true)
		}
		newFieldPtr.On("clicked", func() {
			g.mu.Lock()
			defer g.mu.Unlock()

			objectName := newFieldPtr.String("objectName")
			content := newFieldPtr.Bool("checked")

			g.answerStack[objectName] = "0"
			if content {
				g.answerStack[objectName] = "1"
			}
		})
	default:
		newFieldPtr.Set("text", content.(ast.Parser).String())
		newFieldPtr.On("editingFinished", func() {
			g.mu.Lock()
			defer g.mu.Unlock()

			objectName := newFieldPtr.String("objectName")
			content := newFieldPtr.String("text")
			g.answerStack[objectName] = content
		})
	}
}

func (g *Gui) updateQuestion(newFieldName string) {
	if question, ok := g.symbolTable[newFieldName]; ok {
		question.Set("visible", true)
	}
}

func (g *Gui) hideQuestion(rows qml.Object, fieldName string) {
	g.symbolTable[fieldName].Set("visible", "false")
}

func startQMLengine(appName string) qml.Object {
	engine := qml.NewEngine()
	craddleQML := renderCraddle(appName)
	craddle, err := engine.LoadString("craddle.qml", craddleQML)
	if err != nil {
		log.Fatal("Fatal error while parsing craddle.qml:", err)
	}
	return craddle
}
