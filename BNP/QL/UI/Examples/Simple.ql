﻿form ExampleBlock {

	question MyQuestion1 (text) "What is your name?";
	question MyQuestion2 (yesno) "Are you enrolled in the UvA Master Software Engineering?";
	question MyQuestion3 (number) "How old are you?";

	statement MyStatement99 (text, "-> Welcome to the dawn.") "319 Please?";
	statement ReferencingStatement (text, MyQuestion1) "And the name was?";
	statement Calculation1 (number, (6+1)) "The lucky number is";
}
