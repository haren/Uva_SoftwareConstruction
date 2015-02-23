# Grammar of the basic and question types

from pyparsing import *
from AST.factory import *


class BasicTypes:

    # end_sign :: . | ? | !
    end_sign = oneOf(". ? !")

    # end_sign_esc :: \ end_sign
    end_sign_esc = Suppress("\\") + end_sign

    # characters :: [0-9a-zA-Z()[]{},@#$%^&*-+=/\'\"`~_]
    characters = Word("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()[]{},@#$%^&*-+=/\'\"`~_")

    # word :: end_sign_esc | characters
    word = end_sign_esc | characters

    # sentence :: word+ end_sign
    sentence = (OneOrMore(word) + end_sign).setParseAction(FormFactory.make_sentence)

    # sentences :: sentence+
    sentences = OneOrMore(sentence)

    # comment :: // ....\n  | /* .... */
    comment = Literal("//") + restOfLine | cStyleComment

    # bool_name :: "bool"
    bool_name = "bool"

    # number_name :: "number"
    number_name = "number"

    # text_name :: "text"
    text_name = "text"