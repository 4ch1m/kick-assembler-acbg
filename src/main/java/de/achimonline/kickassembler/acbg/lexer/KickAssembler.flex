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
%eof{  return;
%eof}

// borrowed from ...
//      https://github.com/JustSid/x86-64ASM-IntelliJ/blob/master/src/com/widerwille/x86/x86.flex

LINE_BREAK  = \n|\r|\r\n
WHITE_SPACE = [\ \t\f]

REGISTERS_64BIT = RAX|RBX|RCX|RDC|RSI|RDI|RBP|RSP|R8|R9|R10|R11|R12|R13|R14|R16|rax|rbx|rcx|rdx|rsi|rdi|rsp|r8|r9|r10|r11|r12|r13|r14|r15|r16
REGISTERS_32BIT = EAX|EBX|ECX|EDX|ESI|EDI|EBP|EIP|ESP|eax|ebx|ecx|edx|esi|edi|ebp|eip|esp|R8D|R9D|R10D|R11D|R12D|R13D|R14D|R15D|R16D|r8d|r9d|r10d|r11d|r12d|r13d|r14d|r15d|r16d
REGISTERS_16BIT = AX|BX|CX|DX|SI|DI|BP|SP|IP|ax|bx|cx|dx|si|di|bp|sp|ip|R8W|R9W|R10W|R11W|R12W|R13W|R14W|R15W|R16W|r8w|r9w|r10w|r11w|r12w|r13w|r14w|r15w|r16w
REGISTERS_8BIT  = AH|AL|BH|BL|CH|CL|DH|DL|ah|al|bh|bl|ch|cl|dh|dl|R8B|R9B|R10B|R11B|R12B|R13B|R14B|R15B|R16B|r8b|r9b|r10b|r11b|r12b|r13b|r14b|r15b|r16b

SEGMENT_REGISTERS = CS|DS|ES|FS|GS|SS|cs|ds|es|fs|gs|ss
SPECIAL_REGISTERS = EFLAGS|eflags|CR0|CR1|CR2|CR3|CR4|cr0|cr1|cr2|cr3|cr4

INSTRUCTION_PREFIXES = rep(n?[ez])?|[c-gs]s|(addr|data)(16|32)|lock|wait|rex(64(xyz)?)
BASE_INSTRUCTIONS = aa[adms]|adc[bwlq]?|x?add[bwlq]?|and[bwlq]?|arpl|bound[wl]?|bs[fr][wlq]?|bswap[lq]?|bt[crs]?[wlq]?|call[wlq]?|clc|cld|cli|cltd[dqw]?|clts|cmc|cmov(n?[abgl]e?|n?[ceosz]|np|p[eo]?)?[lqw]?|cmp[bwlq]?|cmps[bwdlq]?|cmpxchg[bwlq]?|cmpxchg(8|16)b|cpuid|c[lw]td[bwlq]?|daa|das|dec[bwlq]?|div[bwlq]?|enter[wl]?|esc|hlt|idiv[bwlq]?|imul[bwlq]?|in[bwlq]?|inc[bwlq]?|insd?[bwlq]?|int(\s*3|o)?|inv(d|pcid)?|invlpg|iret[dfwq]?|j(n?[abgl]e?|n?[ceosz]|np|p[eo]?)|jmp[lq]?|j[er]?cxz|[ls]ahf|lar|lcall[wlq]?|l[d-gs]s[wl]|lea[bwlq]?|leave[lwq]?|l[defgs]s|[ls][gil]dt[wl]?|ljmp[wl]?|lmsw|loadall|lods[bwdlq]?|loop(n?[ez][dw]?)?|loopw|lret|lsl|ltr|mov((s?(b[lwq]?|w[lq]?|lq?|q)?)|(z?(b[lwq]|w[lq]|l|q)))?|movd|movabs[bwlq]?A?|movs(x?d|w)|mov[sz]x[bwl]?|movzb|mul[bwlq]?|neg[bwlq]?|nop|not[bwlq]?|x?or[bwlq]?|out[bwlq]?|outs[bdwl]?|pop[bwlq]?|popal?|pop[af]d|popf[wlq]?|push[bwlq]?|pushal?|push[af]d|pushf[wlq]?|rc[lr][bwlq]?|(rd|wr)msr|rdtscp?|ret[fw]?[ql]?|ro[lr][bwlq]?|rsm|sa[lr][bwlq]?|sbb[bwlq]?|scas[bwdlq]?|set(n?[abgl]e?|n?[ceosz]|np|p[eo]?)b?|sh[lr]d?[bwlq]?|smsw|stc|std|sti|stos[bdqlw]?|str|sub[bwlq]?|swapgs|syscall|sysret|sysenter|sysexit|test[bwlq]?|ud1|ud2[ab]?|ver[rw]|fwait|wbinvd|xchg[bwlq]?A?|x[gs]etbv|xlatb?|xsave[cs]?(64)?|xrstors?(64)
CONVERSION_INSTRUCTIONS = cbw|cdqe?|cwde?|cdo|cqo|cbtw|cwt[ld]|clt[dq]|cqto
FPU_INSTRUCTIONS = f(2xm1|abs|add[psl]?|bld|b?stp|chs|n?clex|fcomp?l|u?comp{0,2}|decstp|n?disi|divr?[psl]?|n?eni|nsetpm|rstpm|free(p|\s*ST)?|iadd[sl]?|icomp?|idivr?[sl]?|ildl?|imul[sl]?|incstp|n?init|ist((pl?|l)|tp)?|isubr?[sl]?|ld[1slt]?|ldcw|ldenv[dw]?|ldl2[et]|ldl[gn]2|ldpi|ldz|mul[psl]?|nop|n?stenv[wd]?|n?stsw|pa?tan|prem1?|rndint|rstor[dw]?|n?savew?|scale|sqrt|st(p?[slt])|n?stcw|subr?[psl]?|tst|wait|xam|xch|xtract|yl2x(p1)?|setpm|cos|saved|sin(cos)?|cmovn?b?e?|cmovn?u|u?comip?|xrstor(64)?|xsave(64)?)

MMX_INSTRUCTIONS = emms|movdq|mov[dq]b|pack(ssdw|[us]swb)|padd([bdw]|u?s[bw])|pandn?|pcmp(eq|gt)[bdw]|pmaddwd|pmul[hl]w|psll[dqw]|psr(a[dw]|l[dqw])|psub([bdw]|u?s[bw])|punpck[hl](bw|dq|wd)|px?or|rdpmc
SSE_INSTRUCTIONS = maskmovq|movntps|movntq|prefetcht[012]|prefetchnta|sfence|add[sp]s|cmp[ps]s|u?comiss|cvt(p(i2ps|s2pi)|s(i2ss|s2sd)q?|t(ps2pi|s[sd]2siq?))|div[ps]s|ldmxcsr|(max|min)[ps]s|mov(a|hl?|lh?|msk|nt)ps|v?mov(s|up)s|mul[ps]s|rcp[ps]s|r?sqrt[ps]s|shufps|stmxcsr|sub[ps]s|unpck[hl]ps|andn?ps|x?orps|pavg[bw]|pextrw|pminsrw|p(max|min)(sw|ub)|pmovmskb|pmulhuw|psadbw|pshufw
SSE2_INSTRUCTIONS = clflush|[lm]fence|maskmovdqu|v?movnt(dq|i[lw]?|pd)|pause|v?add[ps][ds]|andn?pd|bzhi[wl]?|cmp[ps]d|u?comisd|cvtdq2p[ds]|cvtt?pd2(dq|pi)|cvtpd2ps|cvtp[is]2pd|cvtt?ps2dq|cvtt?sd2s[is]|cvts[is]2sd|div[ps][ds]|v?(max|min)[ps][ds]|v?mov[ahlu]pd|v?movmskpd|v?mul[ps]d|x?orpd|shufpd|sqrt[ps]d|sub[ps]d|unpck[hl]pd|unpcklpd|movdq(2q|[au])|movq2dq|paddq|psubq|pmuludq|pshuf([hl]w|d)|ps[lr]ldq|punpck[hl]qdq
SSE3_INSTRUCTIONS = lddqu|monitor|mwait
SSSE3_INSTRUCTIONS = addsubp[ds]|haddp[ds]|hsubp[ds]|v?mov(d|s[hl])dup|psign[bdw]|pshufb|pmulhrsw|pmaddubsw|phsub(s?w|d)|phadd(s?w|d)|palignr|pabs[bdw]
SSE4_INSTRUCTIONS = mpsadbw|phminposuw|pmul(ld|dq)|dpp[ds]|blendv?p[ds]|pblendvb|pblendw|pswapd|p(max|min)(s[bd]|u[wd])|roundp[ds]|rounds[ds]|insertps|pinsr[bwdq]|extractps|pextr[bdq]|pmov[sz]xb[dwq]|pmov[sz]xw[dq]|pmov[sz]xdq|pmov[sz]x|ptest|pcmpeqq|packusdw|v?movntdqa|lzcnt|popcnt|extrq|insertq|movnts[ds]|crc32|pcmp[ei]str[im]|pcmpgtq

AES_INSTRUCTIONS = v?aes(en|de)c(last)?|v?aeskeygenassist|aesimc
SHA_INSTRUCTIONS = sha((1|256)msg[12]|1nexte|1rnds4|256rnds2)

BMI1_INSTRUCTIONS = andn[lq]?|bextr[lq]?|blsi[lq]?|blsmsk[lq]?|blsr[dlq]?|tzcnt[wlq]?
BMI2_INSTRUCTIONS = mulx[lq]?|pdep[lq]?|pext[lq]?|rorx[lq]?|s(h[lr]|ar)x
ADX_INSTRUCTIONS = (ad[co]x)l?


NUMBER = (\-)?(0x)?[0-9A-Fa-f]*?
IDENTIFIER = [A-Za-z_]([A-Za-z0-9_])*?
LABEL = ([A-Za-z0-9])+?

IMMEDIATE_VALUE = \$({NUMBER}|{IDENTIFIER})

LINE_COMMENT  = "//"[^\r\n]*
BLOCK_COMMENT = "/*"([^"*"]|("*"+[^"*""/"]))*("*"+"/")?

%state IN_PREPROCESSOR

%%

"#" { yybegin(IN_PREPROCESSOR); return KickAssemblerTypes.PREPROCESSOR; }

<IN_PREPROCESSOR>
{
	{WHITE_SPACE}+                 { return TokenType.WHITE_SPACE; }
	\\({WHITE_SPACE}?){LINE_BREAK} { return KickAssemblerTypes.PREPROCESSOR; }
	{LINE_BREAK}                   { yybegin(YYINITIAL); return KickAssemblerTypes.PREPROCESSOR; }
	include                        { return KickAssemblerTypes.PREPROCESSOR; }
	define                         { return KickAssemblerTypes.PREPROCESSOR; }
	defined                        { return KickAssemblerTypes.PREPROCESSOR; }
	undef                          { return KickAssemblerTypes.PREPROCESSOR; }
	if                             { return KickAssemblerTypes.PREPROCESSOR; }
	ifdef                          { return KickAssemblerTypes.PREPROCESSOR; }
	ifndef                         { return KickAssemblerTypes.PREPROCESSOR; }
	else                           { return KickAssemblerTypes.PREPROCESSOR; }
	elif                           { return KickAssemblerTypes.PREPROCESSOR; }
	endif                          { return KickAssemblerTypes.PREPROCESSOR; }
	error                          { return KickAssemblerTypes.PREPROCESSOR; }
	pragma                         { return KickAssemblerTypes.PREPROCESSOR; }
	line                           { return KickAssemblerTypes.PREPROCESSOR; }
	##                             { return KickAssemblerTypes.PREPROCESSOR; }
}

<YYINITIAL>
{
	({WHITE_SPACE}|{LINE_BREAK})+ { return TokenType.WHITE_SPACE; }
}

\"([^\"\r\n])*\" { return KickAssemblerTypes.STRING; }
"<"([^>\r\n])*>  { return KickAssemblerTypes.STRING; }

\%({REGISTERS_64BIT}|{REGISTERS_32BIT}|{REGISTERS_16BIT}|{REGISTERS_8BIT}) { return KickAssemblerTypes.REGISTER_GENERAL; }
\%{SEGMENT_REGISTERS} { return KickAssemblerTypes.REGISTER_SEGMENT; }
\%{SPECIAL_REGISTERS} { return KickAssemblerTypes.REGISTER_SPECIAL; }

{INSTRUCTION_PREFIXES} { return KickAssemblerTypes.INSTRUCTION_PREFIX; }
{BASE_INSTRUCTIONS}|{CONVERSION_INSTRUCTIONS}|{FPU_INSTRUCTIONS} { return KickAssemblerTypes.INSTRUCTION; }
{MMX_INSTRUCTIONS}|{SSE_INSTRUCTIONS}|{SSE2_INSTRUCTIONS}|{SSE3_INSTRUCTIONS}|{SSSE3_INSTRUCTIONS}|{SSE_INSTRUCTIONS}|{SSE4_INSTRUCTIONS} { return KickAssemblerTypes.INSTRUCTION; }
{BMI1_INSTRUCTIONS}|{BMI2_INSTRUCTIONS}|{ADX_INSTRUCTIONS} { return KickAssemblerTypes.INSTRUCTION; }
{AES_INSTRUCTIONS}|{SHA_INSTRUCTIONS} { return KickAssemblerTypes.INSTRUCTION; }

"("  { return KickAssemblerTypes.LEFT_PAREN; }
")"  { return KickAssemblerTypes.RIGHT_PAREN; }
"/"  { return KickAssemblerTypes.SLASH; }
"\\" { return KickAssemblerTypes.BACKSLASH; }

"." { return KickAssemblerTypes.DOT; }
"*" { return KickAssemblerTypes.STAR; }
":" { return KickAssemblerTypes.COLON; }
";" { return KickAssemblerTypes.SEMICOLON; }
"," { return KickAssemblerTypes.COMMA; }
"<" { return KickAssemblerTypes.LEFT_ANGLE; }
">" { return KickAssemblerTypes.RIGHT_ANGLE; }

(\$)?{IDENTIFIER} { return KickAssemblerTypes.IDENTIFIER; }
(\$)?{NUMBER}     { return KickAssemblerTypes.VALUE; }
{LABEL}\:         { return KickAssemblerTypes.LABEL; }

{LINE_COMMENT}  { return KickAssemblerTypes.COMMENT_LINE; }
{BLOCK_COMMENT} { return KickAssemblerTypes.COMMENT_BLOCK; }

. { return TokenType.BAD_CHARACTER; }
