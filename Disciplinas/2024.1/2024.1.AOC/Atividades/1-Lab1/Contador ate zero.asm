	.data
nl: 	.asciiz 	"\n"
	.text
	.globl main
main:
	li $v0, 5 # ativa ler input do usuario
    	syscall
	move $s0, $v0 # move o input pro $s0
	
	blt $s0, 0, end # se $0 < 0 vai para end
while:
	li $v0, 1 # ativa printar inteiro
	move $a0, $s0
	syscall # printa
	
	li $v0, 4 # ativa printar string
	la $a0, nl
	syscall # printa
	
	addi $s0, $s0, -1 # $s0 = $s0 - 1
	
	bge $s0, 0, while # se $s0 >= 0 então vá p/ while
end:
	li $v0, 10 # Código para encerrar o programa
	syscall # encerra o programa
