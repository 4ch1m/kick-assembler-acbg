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

\"([^\"\r\n])*\" { return KickAssemblerTypes.STRING; }
"<"([^>\r\n])*>  { return KickAssemblerTypes.STRING; }

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

".import"(({WHITE_SPACE}*("binary"|"c64"|"source"|"text")))? { return KickAssemblerTypes.DIRECTIVE; }

{LABEL}\:     { return KickAssemblerTypes.LABEL; }
{LOCAL_LABEL} { return KickAssemblerTypes.LOCAL_LABEL; }

// standard 6502 mnemonics ...
({LINE_BREAK}|{WHITE_SPACE})+adc { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+and { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+asl { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bcc { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bcs { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+beq { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bit { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bmi { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bne { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bpl { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+brk { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bvc { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bvs { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+clc { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+cld { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+cli { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+clv { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+cmp { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+cpx { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+cpy { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+dec { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+dex { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+dey { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+eor { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+inc { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+inx { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+iny { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+jmp { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+jsr { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+lda { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+ldx { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+ldy { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+lsr { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+nop { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+ora { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+pha { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+php { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+pla { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+plp { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+rol { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+ror { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+rti { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+rts { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sbc { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sec { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sed { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sei { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sta { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+stx { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sty { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+tax { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+tay { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+tsx { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+txa { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+txs { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+tya { return KickAssemblerTypes.MNEMONIC; }

// illegal 6502 mnemonics ...
({LINE_BREAK}|{WHITE_SPACE})+(ahx|sha)     { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(alr|asr)     { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+anc           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+anc2          { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+arr           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(axs|sbx)     { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(dcp|dcm)     { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(isc|ins|isb) { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(las|lae|lds) { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(lax|lxa)     { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+rla           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+rra           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sax           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sbc2          { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+shx           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+shy           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+slo           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sre           { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(tas|shs)     { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+(xaa|ane)     { return KickAssemblerTypes.MNEMONIC; }

// DTV mnemonics ...
({LINE_BREAK}|{WHITE_SPACE})+bra { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sac { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+sir { return KickAssemblerTypes.MNEMONIC; }

// 65c02 mnemonics ...
({LINE_BREAK}|{WHITE_SPACE})+bbr[0-7] { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+bbs[0-7] { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+phx      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+phy      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+plx      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+ply      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+rmb[0-7] { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+smb[0-7] { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+stp      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+stz      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+trb      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+tsb      { return KickAssemblerTypes.MNEMONIC; }
({LINE_BREAK}|{WHITE_SPACE})+wai      { return KickAssemblerTypes.MNEMONIC; }

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

"True"|"true"|"False"|"false" { return KickAssemblerTypes.BOOLEAN; }

"Null"|"null" { return KickAssemblerTypes.NULL; }

{LINE_COMMENT}  { return KickAssemblerTypes.COMMENT_LINE; }
{BLOCK_COMMENT} { return KickAssemblerTypes.COMMENT_BLOCK; }

({WHITE_SPACE}|{LINE_BREAK})+ { return TokenType.WHITE_SPACE; }

[^] { return KickAssemblerTypes.DUMMY; }
