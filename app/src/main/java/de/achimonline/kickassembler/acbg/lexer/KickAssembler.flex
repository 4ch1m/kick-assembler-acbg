package de.achimonline.kickassembler.acbg.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import de.achimonline.kickassembler.acbg.psi.KickAssemblerTypes;
import com.intellij.psi.TokenType;

%%

%class KickAssemblerLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    return;
%eof}

LINE_BREAK  = \n|\r|\r\n
WHITE_SPACE = [\ \t\f]

DEC_LITERAL = [0-9]+(_+[0-9]+)*
FLT_LITERAL = {DEC_LITERAL}("."{DEC_LITERAL})?
HEX_LITERAL = "$"[0-9a-fA-F]+(_+[0-9a-fA-F]+)*
BIN_LITERAL = "%"[0-1]+(_+[0-1]+)*

LABEL = ([a-zA-Z_][a-zA-Z0-9_]*)
LOCAL_LABEL = "!"{LABEL}?[+-]

LINE_COMMENT  = "//"[^\r\n]*
BLOCK_COMMENT = "/*"([^"*"]|("*"+[^"*""/"]))*("*"+"/")?

%%

<YYINITIAL> {
    ({WHITE_SPACE}|{LINE_BREAK})+ { return TokenType.WHITE_SPACE; }

    \"([^\"\r\n])*\" { return KickAssemblerTypes.STRING; }

    "BasicUpstart2" { return KickAssemblerTypes.BASIC_UPSTART; }

    "#define"     { return KickAssemblerTypes.PREPROCESSOR; }
    "#elif"       { return KickAssemblerTypes.PREPROCESSOR; }
    "#else"       { return KickAssemblerTypes.PREPROCESSOR; }
    "#endif"      { return KickAssemblerTypes.PREPROCESSOR; }
    "#if"         { return KickAssemblerTypes.PREPROCESSOR; }
    "#import"     { return KickAssemblerTypes.PREPROCESSOR; }
    "#importif"   { return KickAssemblerTypes.PREPROCESSOR; }
    "#importonce" { return KickAssemblerTypes.PREPROCESSOR; }
    "#undef"      { return KickAssemblerTypes.PREPROCESSOR; }

    ".align"         { return KickAssemblerTypes.DIRECTIVE; }
    ".assert"        { return KickAssemblerTypes.DIRECTIVE; }
    ".asserterror"   { return KickAssemblerTypes.DIRECTIVE; }
    ".break"         { return KickAssemblerTypes.DIRECTIVE; }
    ".by"            { return KickAssemblerTypes.DIRECTIVE; }
    ".byte"          { return KickAssemblerTypes.DIRECTIVE; }
    ".const"         { return KickAssemblerTypes.DIRECTIVE; }
    ".cpu"           { return KickAssemblerTypes.DIRECTIVE; }
    ".define"        { return KickAssemblerTypes.DIRECTIVE; }
    ".disk"          { return KickAssemblerTypes.DIRECTIVE; }
    ".dw"            { return KickAssemblerTypes.DIRECTIVE; }
    ".dword"         { return KickAssemblerTypes.DIRECTIVE; }
    ".encoding"      { return KickAssemblerTypes.DIRECTIVE; }
    ".enum"          { return KickAssemblerTypes.DIRECTIVE; }
    ".error"         { return KickAssemblerTypes.DIRECTIVE; }
    ".errorif"       { return KickAssemblerTypes.DIRECTIVE; }
    ".eval"          { return KickAssemblerTypes.DIRECTIVE; }
    ".file"          { return KickAssemblerTypes.DIRECTIVE; }
    ".filemodify"    { return KickAssemblerTypes.DIRECTIVE; }
    ".filenamespace" { return KickAssemblerTypes.DIRECTIVE; }
    ".fill"          { return KickAssemblerTypes.DIRECTIVE; }
    ".fillword"      { return KickAssemblerTypes.DIRECTIVE; }
    ".for"           { return KickAssemblerTypes.DIRECTIVE; }
    ".function"      { return KickAssemblerTypes.DIRECTIVE; }
    ".if"            { return KickAssemblerTypes.DIRECTIVE; }
    ".import"        { return KickAssemblerTypes.DIRECTIVE; }
    ".importonce"    { return KickAssemblerTypes.DIRECTIVE; }
    ".label"         { return KickAssemblerTypes.DIRECTIVE; }
    ".lohifill"      { return KickAssemblerTypes.DIRECTIVE; }
    ".macro"         { return KickAssemblerTypes.DIRECTIVE; }
    ".memblock"      { return KickAssemblerTypes.DIRECTIVE; }
    ".modify"        { return KickAssemblerTypes.DIRECTIVE; }
    ".namespace"     { return KickAssemblerTypes.DIRECTIVE; }
    ".pc"            { return KickAssemblerTypes.DIRECTIVE; }
    ".plugin"        { return KickAssemblerTypes.DIRECTIVE; }
    ".print"         { return KickAssemblerTypes.DIRECTIVE; }
    ".printnow"      { return KickAssemblerTypes.DIRECTIVE; }
    ".pseudocommand" { return KickAssemblerTypes.DIRECTIVE; }
    ".pseudopc"      { return KickAssemblerTypes.DIRECTIVE; }
    ".return"        { return KickAssemblerTypes.DIRECTIVE; }
    ".segment"       { return KickAssemblerTypes.DIRECTIVE; }
    ".segmentdef"    { return KickAssemblerTypes.DIRECTIVE; }
    ".segmentout"    { return KickAssemblerTypes.DIRECTIVE; }
    ".struct"        { return KickAssemblerTypes.DIRECTIVE; }
    ".te"            { return KickAssemblerTypes.DIRECTIVE; }
    ".text"          { return KickAssemblerTypes.DIRECTIVE; }
    ".var"           { return KickAssemblerTypes.DIRECTIVE; }
    ".while"         { return KickAssemblerTypes.DIRECTIVE; }
    ".wo"            { return KickAssemblerTypes.DIRECTIVE; }
    ".word"          { return KickAssemblerTypes.DIRECTIVE; }
    ".zp"            { return KickAssemblerTypes.DIRECTIVE; }

    {LABEL}\:     { return KickAssemblerTypes.LABEL; }
    {LOCAL_LABEL} { return KickAssemblerTypes.LOCAL_LABEL; }

    "adc"|"ADC" { return KickAssemblerTypes.MNEMONIC; }
    "ahx"|"AHX" { return KickAssemblerTypes.MNEMONIC; }
    "alr"|"ALR" { return KickAssemblerTypes.MNEMONIC; }
    "anc"|"ANC" { return KickAssemblerTypes.MNEMONIC; }
    "and"|"AND" { return KickAssemblerTypes.MNEMONIC; }
    "arr"|"ARR" { return KickAssemblerTypes.MNEMONIC; }
    "asl"|"ASL" { return KickAssemblerTypes.MNEMONIC; }
    "axs"|"AXS" { return KickAssemblerTypes.MNEMONIC; }
    "bcc"|"BCC" { return KickAssemblerTypes.MNEMONIC; }
    "bcs"|"BCS" { return KickAssemblerTypes.MNEMONIC; }
    "beq"|"BEQ" { return KickAssemblerTypes.MNEMONIC; }
    "bit"|"BIT" { return KickAssemblerTypes.MNEMONIC; }
    "bmi"|"BMI" { return KickAssemblerTypes.MNEMONIC; }
    "bne"|"BNE" { return KickAssemblerTypes.MNEMONIC; }
    "bpl"|"BPL" { return KickAssemblerTypes.MNEMONIC; }
    "brk"|"BRK" { return KickAssemblerTypes.MNEMONIC; }
    "bvc"|"BVC" { return KickAssemblerTypes.MNEMONIC; }
    "bvs"|"BVS" { return KickAssemblerTypes.MNEMONIC; }
    "clc"|"CLC" { return KickAssemblerTypes.MNEMONIC; }
    "cld"|"CLD" { return KickAssemblerTypes.MNEMONIC; }
    "cli"|"CLI" { return KickAssemblerTypes.MNEMONIC; }
    "clv"|"CLV" { return KickAssemblerTypes.MNEMONIC; }
    "cmp"|"CMP" { return KickAssemblerTypes.MNEMONIC; }
    "cpx"|"CPX" { return KickAssemblerTypes.MNEMONIC; }
    "cpy"|"CPY" { return KickAssemblerTypes.MNEMONIC; }
    "dcp"|"DCP" { return KickAssemblerTypes.MNEMONIC; }
    "dec"|"DEC" { return KickAssemblerTypes.MNEMONIC; }
    "dex"|"DEX" { return KickAssemblerTypes.MNEMONIC; }
    "dey"|"DEY" { return KickAssemblerTypes.MNEMONIC; }
    "eor"|"EOR" { return KickAssemblerTypes.MNEMONIC; }
    "inc"|"INC" { return KickAssemblerTypes.MNEMONIC; }
    "inx"|"INX" { return KickAssemblerTypes.MNEMONIC; }
    "iny"|"INY" { return KickAssemblerTypes.MNEMONIC; }
    "isc"|"ISC" { return KickAssemblerTypes.MNEMONIC; }
    "jmp"|"JMP" { return KickAssemblerTypes.MNEMONIC; }
    "jsr"|"JSR" { return KickAssemblerTypes.MNEMONIC; }
    "las"|"LAS" { return KickAssemblerTypes.MNEMONIC; }
    "lax"|"LAX" { return KickAssemblerTypes.MNEMONIC; }
    "lda"|"LDA" { return KickAssemblerTypes.MNEMONIC; }
    "ldx"|"LDX" { return KickAssemblerTypes.MNEMONIC; }
    "ldy"|"LDY" { return KickAssemblerTypes.MNEMONIC; }
    "lsr"|"LSR" { return KickAssemblerTypes.MNEMONIC; }
    "nop"|"NOP" { return KickAssemblerTypes.MNEMONIC; }
    "ora"|"ORA" { return KickAssemblerTypes.MNEMONIC; }
    "pha"|"PHA" { return KickAssemblerTypes.MNEMONIC; }
    "php"|"PHP" { return KickAssemblerTypes.MNEMONIC; }
    "pla"|"PLA" { return KickAssemblerTypes.MNEMONIC; }
    "plp"|"PLP" { return KickAssemblerTypes.MNEMONIC; }
    "rla"|"RLA" { return KickAssemblerTypes.MNEMONIC; }
    "rol"|"ROL" { return KickAssemblerTypes.MNEMONIC; }
    "ror"|"ROR" { return KickAssemblerTypes.MNEMONIC; }
    "rra"|"RRA" { return KickAssemblerTypes.MNEMONIC; }
    "rti"|"RTI" { return KickAssemblerTypes.MNEMONIC; }
    "rts"|"RTS" { return KickAssemblerTypes.MNEMONIC; }
    "sax"|"SAX" { return KickAssemblerTypes.MNEMONIC; }
    "sbc"|"SBC" { return KickAssemblerTypes.MNEMONIC; }
    "sec"|"SEC" { return KickAssemblerTypes.MNEMONIC; }
    "sed"|"SED" { return KickAssemblerTypes.MNEMONIC; }
    "sei"|"SEI" { return KickAssemblerTypes.MNEMONIC; }
    "shx"|"SHX" { return KickAssemblerTypes.MNEMONIC; }
    "shy"|"SHY" { return KickAssemblerTypes.MNEMONIC; }
    "slo"|"SLO" { return KickAssemblerTypes.MNEMONIC; }
    "sre"|"SRE" { return KickAssemblerTypes.MNEMONIC; }
    "sta"|"STA" { return KickAssemblerTypes.MNEMONIC; }
    "stx"|"STX" { return KickAssemblerTypes.MNEMONIC; }
    "sty"|"STY" { return KickAssemblerTypes.MNEMONIC; }
    "tas"|"TAS" { return KickAssemblerTypes.MNEMONIC; }
    "tax"|"TAX" { return KickAssemblerTypes.MNEMONIC; }
    "tay"|"TAY" { return KickAssemblerTypes.MNEMONIC; }
    "tsx"|"TSX" { return KickAssemblerTypes.MNEMONIC; }
    "txa"|"TXA" { return KickAssemblerTypes.MNEMONIC; }
    "txs"|"TXS" { return KickAssemblerTypes.MNEMONIC; }
    "tya"|"TYA" { return KickAssemblerTypes.MNEMONIC; }
    "xaa"|"XAA" { return KickAssemblerTypes.MNEMONIC; }
    "bra"|"BRA" { return KickAssemblerTypes.MNEMONIC; }
    "sac"|"SAC" { return KickAssemblerTypes.MNEMONIC; }
    "sir"|"SIR" { return KickAssemblerTypes.MNEMONIC; }

    "sbc2"|"SBC2" { return KickAssemblerTypes.MNEMONIC; }
    "anc2"|"ANC2" { return KickAssemblerTypes.MNEMONIC; }

    "("  { return KickAssemblerTypes.LEFT_PARENTHESES; }
    ")"  { return KickAssemblerTypes.RIGHT_PARENTHESES; }
    "{"  { return KickAssemblerTypes.LEFT_BRACE; }
    "}"  { return KickAssemblerTypes.RIGHT_BRACE; }
    "["  { return KickAssemblerTypes.LEFT_BRACKET; }
    "]"  { return KickAssemblerTypes.RIGHT_BRACKET; }

    "#"	 { return KickAssemblerTypes.HASH; }
    "="	 { return KickAssemblerTypes.ASSIGN; }
    ","	 { return KickAssemblerTypes.COMMA; }
    ";"	 { return KickAssemblerTypes.SEMICOLON; }
    "<=" { return KickAssemblerTypes.LESS_EQUALS; }
    ">=" { return KickAssemblerTypes.GREATER_EQUALS; }
    "<"	 { return KickAssemblerTypes.LESS; }
    ">"	 { return KickAssemblerTypes.GREATER; }
    "&"	 { return KickAssemblerTypes.BIT_AND; }
    "|"	 { return KickAssemblerTypes.BIT_OR; }
    "^"	 { return KickAssemblerTypes.BIT_XOR; }
    "~"	 { return KickAssemblerTypes.BIT_NOT; }
    "<<" { return KickAssemblerTypes.SHIFT_LEFT; }
    ">>" { return KickAssemblerTypes.SHIFT_RIGHT; }
    "+"	 { return KickAssemblerTypes.PLUS; }
    "-"	 { return KickAssemblerTypes.MINUS; }
    "*"	 { return KickAssemblerTypes.TIMES; }
    "/"	 { return KickAssemblerTypes.DIVIDE; }
    "!=" { return KickAssemblerTypes.NOT_EQUAL; }
    "==" { return KickAssemblerTypes.EQUAL; }
    "&&" { return KickAssemblerTypes.AND; }
    "||" { return KickAssemblerTypes.OR; }
    "!"	 { return KickAssemblerTypes.NOT; }
    "++" { return KickAssemblerTypes.PLUS_PLUS; }
    "--" { return KickAssemblerTypes.MINUS_MINUS; }
    "+=" { return KickAssemblerTypes.PLUS_EQUAL; }
    "-=" { return KickAssemblerTypes.MINUS_EQUAL; }
    "*=" { return KickAssemblerTypes.TIMES_EQUAL; }
    "/=" { return KickAssemblerTypes.DIVIDE_EQUAL; }
    "."	 { return KickAssemblerTypes.DOT; }
    ":"	 { return KickAssemblerTypes.COLON; }
    "?"	 { return KickAssemblerTypes.QUESTION_MARK; }

    {DEC_LITERAL}|{FLT_LITERAL}|{HEX_LITERAL}|{BIN_LITERAL} { return KickAssemblerTypes.NUMBER; }
    "true"|"false" 	                                        { return KickAssemblerTypes.NUMBER; }
    "null"                                                  { return KickAssemblerTypes.NUMBER; }

    {LINE_COMMENT}  { return KickAssemblerTypes.COMMENT_LINE; }
    {BLOCK_COMMENT} { return KickAssemblerTypes.COMMENT_BLOCK; }

    [^] { return KickAssemblerTypes.DUMMY; }
}
