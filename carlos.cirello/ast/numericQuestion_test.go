package ast

import "testing"

func TestNumericQuestion(t *testing.T) {
	input := "42"

	aIntQuestion := new(NumericQuestion)
	aIntQuestion.From(input)

	if got := aIntQuestion.String(); input != got {
		t.Errorf("Internal error in IntQuestion. Got %s, Expected %s",
			got, input)
	}
}
