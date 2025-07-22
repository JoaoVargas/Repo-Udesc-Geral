	.file	"exemplo.c"
	.text
	.section	.rodata
.LC0:
	.string	"Alo"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$32, %rsp
	movl	%edi, -20(%rbp)
	movq	%rsi, -32(%rbp)
	movq	$0, -8(%rbp)
	jmp	.L2
.L3:
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	addq	$1, -8(%rbp)
.L2:
	cmpq	$9, -8(%rbp)
	jbe	.L3
	movl	$0, %eax
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (GNU) 14.1.1 20240701 (Red Hat 14.1.1-7)"
	.section	.note.GNU-stack,"",@progbits
