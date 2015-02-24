package symboltable

import "strconv"

// BoolQuestion stores the answer of question which type is integer numeric
type BoolQuestion struct {
	value bool
}

// BoolQuestionType constant used for type comparison internally in interpreter
// and frontend
const BoolQuestionType = "bool"

// From takes the input from Frontend and stores locally - Boolean
func (s *BoolQuestion) From(str string) error {
	val, err := strconv.Atoi(str)
	if val == 1 || str == "Yes" || str == "yes" {
		s.value = true
	} else {
		s.value = false
	}
	return err
}

// String prints in human form the content of the question - Boolean
func (s BoolQuestion) String() string {
	if s.value {
		return "Yes"
	}
	return "No"
}

// Value converts underlying boolean into primitive boolean
func (s BoolQuestion) Value() interface{} {
	return s.value
}