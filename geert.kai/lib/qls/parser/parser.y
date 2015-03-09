class QLS::Parser

token STRING VARIABLE_NAME INTEGER COLOR
rule
  stylesheet
    : 'stylesheet' variable_name '{' stylesheet_rules '}' { result = Stylesheet.new(val[1], val[3]) }
    ;

  stylesheet_rules
    : stylesheet_rules stylesheet_rule  { result = val[0].push(val[1]) }
    | stylesheet_rule                   { result = [ val[0] ] }
    ;

  stylesheet_rule
    : default
    | page
    | question
    ;

  page
    : 'page' variable_name '{' page_rules '}' { result = Page.new(val[1], val[3]) }
    ;

    
  page_rules
    : page_rules page_rule  { result = val[0].push(val[1]) }
    | page_rule             { result = [ val[0] ] }
    ;

  page_rule
    : default
    | section
    | question
    ;

  section
    : 'section' variable_name '{' section_rules '}' { result = Section.new(val[1], val[3]) }
    ;

    
  section_rules
    : section_rules section_rule  { result = val[0].push(val[1]) }
    | section_rule                { result = [ val[0] ] }
    ;

  section_rule
    : default
    | question
    | section
    ;

  question
    : 'question' variable_name '{' declarations '}' { result = Question.new(val[1], val[3]) }
    ;

  default
    : 'default' type '{' declarations '}' { result = Default.new(val[1], val[3]) }
    ;

  declarations
    : declarations declaration  { result = val[0].push(val[1]) }
    | declaration               { result = [ val[0] ] }
    ;

 
  declaration
    : property ':' value { PropertyDeclaration.new(val[0], val[1]) }
    ;

  property
    : variable_name

  value
    : integer
    | string
    | color
    | widget
    ;

  color
    : COLOR
    ;

  widget
    : 'checkbox' { result = Checkbox.new }
    | 'spinbox'  { result = Spinbox.new }
    | 'radio'    { result = YesNoRadio.new }
    | 'dropdown' { result = YesNoDropdown.new }
    | 'text'     { result = Text.new }
    ;

  variable_name
    : VARIABLE_NAME 
    ;

  type
    : 'boolean' { result = BooleanType.new }
    | 'integer' { result = IntegerType.new }
    | 'string'  { result = StringType.new }
    ;

  integer
    : INTEGER { result = IntegerLiteral.new(val[0].to_i) }
    ;

  string
    : STRING { result = StringLiteral.new(val[0][1..-2]) }
    ;
end

---- inner

  require_relative '../ast/ast.rb'
  
  include AST


  def initialize(tokenizer)
    @tokenizer = tokenizer
    super()
  end

  def next_token
    @tokenizer.next_token
  end

  def parse
    do_parse
  end
