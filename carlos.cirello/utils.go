package main

import (
	"io"
	"log"

	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/cli"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/cli/iostream"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/frontend"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/frontend/csvinput"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/frontend/csvoutput"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/frontend/graphic"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/interpreter"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/parser"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/plumbing"
)

func readInput(pipes *plumbing.Pipes, inReader io.Reader) {
	if inReader == nil {
		return
	}
	csvinput.Read(pipes, inReader)
	// Replace the line above with this line below to change the input
	// format. Remember to run goimports to update the import list.
	// jsoninput.Read(pipes, inReader)
}

func writeOutput(pipes *plumbing.Pipes, outWriter io.Writer) {
	csvoutput.Write(pipes, outWriter)

	// Replace the line above with this line below to change the output
	// format. Remember to run goimports to update the import list.
	// jsonoutput.Write(pipes, outWriter)
}

func errorHandler() {
	if r := recover(); r != nil {
		log.Println("error:", r)
	}
}

func startInterpreter(srcReader io.Reader, srcFn string) (pipes *plumbing.Pipes,
	guiAppName string,
) {
	aQuestionaire := parser.ReadQL(srcReader, srcFn)
	pipes = interpreter.New(aQuestionaire)
	guiAppName = aQuestionaire.Label()
	return pipes, guiAppName
}

func launchGUI(pipes *plumbing.Pipes, guiAppName string) {
	driver := graphic.GUI(guiAppName)
	frontend.New(pipes.FromInterpreter(), pipes.ToInterpreter(), driver)
	driver.Loop()
}

func openIoStreams() (srcFn string, srcReader, inReader io.Reader,
	outWriter io.Writer) {
	srcFn, inFn, outFn := cli.Args()
	srcReader, inReader, outWriter = iostream.Open(srcFn, inFn, outFn)
	return srcFn, srcReader, inReader, outWriter
}