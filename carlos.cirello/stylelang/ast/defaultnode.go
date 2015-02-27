package ast

type DefaultNode struct {
	questionType string
	widget       string
}

func NewDefaultNode(questionType, widget string) *DefaultNode {
	return &DefaultNode{questionType, widget}
}